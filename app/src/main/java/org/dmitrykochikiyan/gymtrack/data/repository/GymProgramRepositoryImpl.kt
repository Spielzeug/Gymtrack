package org.dmitrykochikiyan.gymtrack.data.repository

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.dmitrykochikiyan.gymtrack.domain.model.GymProgram
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramRepository
import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramsResponse
import javax.inject.Inject

class GymProgramRepositoryImpl @Inject constructor(private val gymProgramsRef: CollectionReference) :
    GymProgramRepository {
    override fun getGymPrograms(): Flow<GymProgramsResponse> = callbackFlow {
        val snapshotListener = gymProgramsRef.addSnapshotListener { snapshot, error ->
            val gymProgramResult =
                when {
                    error != null -> Response.Failure(error)
                    snapshot != null -> {
                        val gymPrograms = snapshot.documents.mapNotNull {
                            it.toObject(GymProgram::class.java)
                        }
                        Response.Success(gymPrograms)
                    }

                    else -> Response.Failure(IllegalStateException("Snapshot and error are null"))
                }

            trySend(gymProgramResult)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}