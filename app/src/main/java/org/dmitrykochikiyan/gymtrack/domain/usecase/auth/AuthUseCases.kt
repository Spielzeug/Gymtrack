package org.dmitrykochikiyan.gymtrack.domain.usecase.auth

data class AuthUseCases(
    val signIn: SignIn,
    val signInAnonymously: SignInAnonymously,
    val signUp: SignUp,
    val signOut: SignOut,
    val getCurrentUser: GetCurrentUser
)