/*
Copyright 2022 Google LLC
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    https://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.dmitrykochikiyan.gymtrack.hilt

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.dmitrykochikiyan.gymtrack.BuildConfig
import org.dmitrykochikiyan.gymtrack.data.repository.AuthRepositoryImpl
import org.dmitrykochikiyan.gymtrack.data.repository.GymProgramRepositoryImpl
import org.dmitrykochikiyan.gymtrack.domain.repository.AuthRepository
import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramRepository
import org.dmitrykochikiyan.gymtrack.domain.usecase.*
import org.dmitrykochikiyan.gymtrack.domain.usecase.auth.*
import org.dmitrykochikiyan.gymtrack.domain.usecase.program.GetGymPrograms
import org.dmitrykochikiyan.gymtrack.domain.usecase.program.GetProgramWorkouts
import org.dmitrykochikiyan.gymtrack.domain.usecase.program.UseCases
import javax.inject.Singleton

@Module
//TODO: Specify appropriate scope
@InstallIn(SingletonComponent::class)
//TODO: Refactor to use @Binds where appropriate
//TODO: Refactor into multiple modules (e.g. AuthModule, FirestoreModule, etc.)
object AppModule {
    private val FIRESTORE_EMULATOR_HOST = "10.0.2.2"
    private val FIRESTORE_EMULATOR_PORT = 8081

    private val AUTH_EMULATOR_HOST = "10.0.2.2"
    private val AUTH_EMULATOR_PORT = 9099

    @Provides
    @Singleton
    fun providesFirestore(): FirebaseFirestore {
        val firestore = Firebase.firestore
        // Use emulators only in debug builds
        if (BuildConfig.DEBUG) {
            firestore.useEmulator(FIRESTORE_EMULATOR_HOST, FIRESTORE_EMULATOR_PORT)
        }

        return firestore
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        val firebaseAuth = Firebase.auth
        // Use emulators only in debug builds
        if (BuildConfig.DEBUG) {
            firebaseAuth.useEmulator(AUTH_EMULATOR_HOST, AUTH_EMULATOR_PORT)
        }
        return firebaseAuth
    }

    @Provides
    @Singleton
    fun providesProgramRef(firestore: FirebaseFirestore): CollectionReference = firestore.collection("programs")

    @Provides
    @Singleton
    fun providesGymProgramRepository(gymProgramRef: CollectionReference): GymProgramRepository = GymProgramRepositoryImpl(gymProgramRef)

    @Provides
    @Singleton
    fun providesAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(firebaseAuth)

    @Provides
    fun providesAuthUseCases(authRepository: AuthRepository) = AuthUseCases(
        signIn = SignIn(authRepository),
        signInAnonymously = SignInAnonymously(authRepository),
        signUp = SignUp(authRepository),
        signOut = SignOut(authRepository),
        getCurrentUser = GetCurrentUser(authRepository)
    )

    @Provides
    fun providesUseCases(gymProgramRepository: GymProgramRepository) = UseCases(
        getGymPrograms = GetGymPrograms(gymProgramRepository),
        getProgramWorkouts = GetProgramWorkouts(gymProgramRepository)
    )
}