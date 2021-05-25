package view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.appers.mynewnotebook.R
import kotlinx.android.synthetic.main.feed_item_layout.view.*
import model.Notes

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var noteList: List<Notes> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class NoteViewHolder( view : View) : RecyclerView.ViewHolder(view) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
val itemView=LayoutInflater.from(parent.context).inflate(R.layout.activity_home_page,parent,false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
return noteList.size
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
val note=noteList[position]
        holder.itemView.apply {
            this.topic_textView.text=note.topic
            this.notes_textview.text=note.content
        }


    }
}