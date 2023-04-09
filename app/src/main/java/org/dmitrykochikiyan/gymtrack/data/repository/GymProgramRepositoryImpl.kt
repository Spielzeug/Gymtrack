package org.dmitrykochikiyan.gymtrack.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.dmitrykochikiyan.gymtrack.domain.model.GymProgram
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.model.Workout
import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramRepository
import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramsResponse
import org.dmitrykochikiyan.gymtrack.domain.repository.WorkoutsResponse
import javax.inject.Inject

class GymProgramRepositoryImpl @Inject constructor(private val gymProgramsRef: CollectionReference) :
    GymProgramRepository {
    override fun getGymPrograms(): Flow<GymProgramsResponse> = getResponseFlow(gymProgramsRef) { snapshot ->
        when {
            snapshot != null -> {
                val gymPrograms = snapshot.documents.mapNotNull {
                    //Do not load workouts for now
                    GymProgram(
                        id = it.id,
                        name = it["name"] as String,
                        description = it["description"] as String
                    )
                }
                Response.Success(gymPrograms)
            }

            else -> Response.Failure(IllegalStateException("Snapshot is null"))
        }
    }

    override fun getWorkouts(gymProgramId: String): Flow<WorkoutsResponse> =
        getResponseFlow(gymProgramsRef.document(gymProgramId).collection("workouts")) { snapshot ->
            when {
                snapshot != null -> {
                    val workouts = snapshot.documents.mapNotNull {
                        it.toObject(Workout::class.java)
                    }
                    Response.Success(workouts)
                }

                else -> Response.Failure(IllegalStateException("Snapshot is null"))
            }
        }

    private fun <T> getResponseFlow(
        collectionRef: CollectionReference,
        responseCreator: (QuerySnapshot?) -> Response<T>
    ): Flow<Response<T>> = callbackFlow {
        val snapshotListener = collectionRef.addSnapshotListener { snapshot, error ->
            val result = if (error != null) {
                Response.Failure(error)
            } else {
                responseCreator(snapshot)
            }
            trySend(result)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}