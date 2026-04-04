package com.vibhorpatil.composeapplication.ChatRoom.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> = try {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val user = User(firstName, lastName, email)
        firestore.collection("users").document(result.user!!.uid).set(user).await()
        Result.Success(true)
    } catch (e: Exception) {
        Result.Error(e)
    }

    suspend fun login(email: String, password: String): Result<Boolean> = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Result.Success(true)
    } catch (e: Exception) {
        Result.Error(e)
    }

    suspend fun getCurrentUser(): Result<User> = try {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val document = firestore.collection("users").document(userId).get().await()
            val user = document.toObject(User::class.java)
            if (user != null) {
                Result.Success(user)
            } else {
                Result.Error(Exception("User not found"))
            }
        } else {
            Result.Error(Exception("User not logged in"))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}
