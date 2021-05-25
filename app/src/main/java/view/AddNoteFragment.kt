package view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appers.mynewnotebook.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.addnote_fragment_layout.*
import model.Notes

class AddNoteFragment : Fragment() {
    private val notesReference = FirebaseDatabase.getInstance().reference.child("AddedNotes")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.addnote_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        note_add_btn.setOnClickListener {
            Toast.makeText(activity!!,"Notes added to database",Toast.LENGTH_SHORT).show()
            val topic = note_topic_input.text.toString().trim()
            val noteBody = note_body_input.text.toString().trim()
            val key1: String = notesReference.push().key ?: ""
            if (topic.isNotEmpty() && noteBody.isNotEmpty()) {
                val notes = Notes(topic, noteBody)
                Log.d("TAG_X", " pushed-> empty space->$key1")
                notesReference.child(key1).setValue(notes)
                Log.d("TAG_X", "THE NOTE IS POSTED in $key1")
                val count = requireActivity().supportFragmentManager.backStackEntryCount
                for (i in 0..count) requireActivity().supportFragmentManager.popBackStack()

            }
        }
    }
}