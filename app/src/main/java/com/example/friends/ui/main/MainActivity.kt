package com.example.friends.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.friends.R
import com.example.friends.databinding.ActivityMainBinding
import com.example.friends.ui.messenger.Messenger
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Firebase.messaging.isAutoInitEnabled = true

        navController = findNavController(R.id.container)
        binding.bottomMenu.setItemSelected(R.id.home)
        binding.bottomMenu.setOnItemSelectedListener { id ->
            when (id) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                }
                R.id.add_post -> {
                    startActivity(Intent(this, PublishActivity::class.java))
                    binding.bottomMenu.setItemSelected(R.id.home)
                    navController.navigate(R.id.homeFragment)
                }
                R.id.messenger -> {
                    startActivity(Intent(this, Messenger::class.java))
                    binding.bottomMenu.setItemSelected(R.id.home)
                    navController.navigate(R.id.homeFragment)
                }
                R.id.videos -> {
                    navController.navigate(R.id.videosFragment)
                }
            }
        }
    }


}
