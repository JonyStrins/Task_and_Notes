package com.example.taskandnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskandnotes.data.model.Nota

class NotaListAdapter: ListAdapter<Nota, NotaListAdapter.NotaViewHolder>(NotaComparator()) {
    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val notaItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(nota: Nota) {
            notaItemView.text = nota.titulo
        }

        companion object {
            fun create(parent: ViewGroup): NotaViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return NotaViewHolder(view)
            }
        }
    }

    class NotaComparator : DiffUtil.ItemCallback<Nota>() {
        override fun areItemsTheSame(oldItem: Nota, newItem: Nota): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Nota, newItem: Nota): Boolean {
            return oldItem.titulo==newItem.titulo
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        return NotaViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = getItem(position)
        holder.bind(nota)
    }
}