package orlo.karagulov.xml_mvvm.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import orlo.karagulov.xml_mvvm.databinding.RowNoteBinding
import orlo.karagulov.xml_mvvm.model.Note

class MainAdapter: RecyclerView.Adapter<MainAdapter.NotesViewHolder>() {

    private var notesList = emptyList<Note>()

    class NotesViewHolder(val binding: RowNoteBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(RowNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onViewAttachedToWindow(holder: NotesViewHolder) {
        holder.itemView.setOnClickListener { 
            MainFragment.click(notesList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NotesViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.tvNoteName.text = notesList[position].title
        holder.binding.tvNoteText.text = notesList[position].text
    }

    fun setData(notes:List<Note>) {
        this.notesList = notes
        notifyDataSetChanged()
    }

}