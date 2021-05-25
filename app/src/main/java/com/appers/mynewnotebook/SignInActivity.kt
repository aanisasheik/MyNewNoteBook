package com.appers.mynewnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.signin_activity_layout.*
import model.SignUpUser
import view.SignupFragment

class SignInActivity : AppCompatActivity() {

    //private val usersReference = FirebaseDatabase.getInstance().reference.child("AddedUsers")
    val signupFragment = SignupFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity_layout)
        signup_textview.setOnClickListener {
            supportFragmentManager.beginTransaction().setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
            ).addToBackStack(signupFragment.tag).replace(R.id.signup_frame, signupFragment).commit()
        }

        signin_button.setOnClickListener {
            val email = email_edittext.text.toString().trim()
            val password = password_edittext.text.toString().trim()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                        openhomePage()
                    } else {
                        showEmailVerification(email)
                    }
                } else {
                    showError(task)
                }
            }
        }


        //val key2 : String = usersReference.push().key?: ""
        //val user1 = Users("aanisharifa",22)
        //val user2 =Users("RiyazAhamed",222)
        //usersReference.child(key2).setValue(user1)
        //usersReference.child(key2).setValue(user2)
        //writing to database 10times




    }

    private fun showError(task: Task<AuthResult>) {

        Log.d("TAG_X", task.exception.toString())
        Snackbar.make(root, "Error: ${task.exception?.localizedMessage}", Snackbar.LENGTH_SHORT).show()
    }

    private fun showEmailVerification(email: String) {

        AlertDialog.Builder(ContextThemeWrapper(this, R.style.Theme_AppCompat_Light_Dialog)).setTitle("Email Verification Sent")
                .setMessage("Please check your email : $email.Condirmation email has been sent").setPositiveButton("okay") { dialog, _ ->
                    dialog.dismiss()

                }.create().show()
    }

    private fun openhomePage() {
        startActivity(Intent(this, HomePageActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    fun signupUser(signupUser: SignUpUser) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(signupUser.email, signupUser.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                            openhomePage()
                        } else {
                            FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                            showEmailVerification(signupUser.email)
                        }
                    } else {
                        showError(task)
                    }

                }
    }


}