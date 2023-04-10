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
package org.dmitrykochikiyan.gymtrack.domain.repository

import com.google.firebase.auth.FirebaseUser
import org.dmitrykochikiyan.gymtrack.domain.model.Response

typealias UserResponse = Response<FirebaseUser?>
interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun signIn(email: String, password: String): UserResponse
    suspend fun signInAnonymously(): UserResponse
    suspend fun signUp(email: String, password: String): UserResponse
    suspend fun signOut(): Response<Unit>
}