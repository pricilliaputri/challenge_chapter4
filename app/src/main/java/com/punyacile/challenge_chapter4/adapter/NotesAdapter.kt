package com.punyacile.challenge_chapter4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.punyacile.challenge_chapter4.data.NoteEntity
import com.punyacile.challenge_chapter4.databinding.NotesListBinding


class NotesAdapter(
    private val list: List<NoteEntity>,
    private val iClickListener: ItemClickListener
) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: NotesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteEntity: NoteEntity) {
            binding.apply {
                itemNote = noteEntity
                itemNoteClick = iClickListener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(NotesListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    interface ItemClickListener {
        fun edit(noteEntity: NoteEntity)
        fun delete(noteEntity: NoteEntity)
    }
}