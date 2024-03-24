package com.example.friends.firebase

import com.google.firebase.auth.FirebaseAuth

object MyFirebase {

    val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

}