package com.appers.mynewnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class StartUpChooser : AppCompatActivity() {
    lateinit var openIntent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(FirebaseAuth.getInstance().currentUser!=null && FirebaseAuth.getInstance().currentUser?.isEmailVerified==true) {
            //if user logged in ..nav to home page
            openIntent=Intent(this,HomePageActivity::class.java)
        }else{
//if user not logged in ,navigate to signin Activity
openIntent=Intent(this,SignInActivity::class.java)
        }
        startActivity(openIntent.also{
            it.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}