package com.example.dnstest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.dnstest.domain.Book

class BookDiffCallback: DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}