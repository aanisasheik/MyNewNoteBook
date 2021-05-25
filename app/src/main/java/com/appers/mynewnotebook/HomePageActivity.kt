package com.appers.mynewnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home_page.*
import model.Notes
import view.AddNoteFragment
import view.NoteAdapter
import view.ViewNoteFragment

class HomePageActivity : AppCompatActivity(){
val adapter : NoteAdapter = NoteAdapter()
    val notesReference=FirebaseDatabase.getInstance().reference.child("notes")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
               feed_recyclerView.adapter=adapter
               val noteList = mutableListOf<Notes>()
               notesReference.addValueEventListener(object : ValueEventListener {
                   override fun onCancelled(error: DatabaseError) {
                      //mistake
                   }

                   override fun onDataChange(snapshot: DataSnapshot) {
                       snapshot.children.forEach {
                           it.getValue(Notes::class.java)?.let {note->
                               noteList.add(note)
                               //note_text.text="${note_text.text}/n${note.content}"

                           }


                       }
                       adapter.noteList=noteList
                   }


               })
        note_postimageview.setOnClickListener{
            val fragment = AddNoteFragment()
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                            android.R.anim.fade_in,
                            android.R.anim.fade_out,
                            android.R.anim.fade_in,
                            android.R.anim.fade_out
                    )
                    .replace(R.id.add_note_frame, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()
        }
    }


}