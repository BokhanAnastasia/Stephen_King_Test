package com.example.dnstest.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dnstest.R
import com.example.dnstest.databinding.BookItemBinding
import com.example.dnstest.domain.Book

class BooksAdapter: ListAdapter<Book, BooksAdapter.BooksHolder>(BookDiffCallback()) {

    class BooksHolder(book: View) : RecyclerView.ViewHolder(book){
        val binding = BookItemBinding.bind(book)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BooksHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BooksHolder(view)
    }


    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        val book = getItem(position)
        holder.binding.textTitle.text = book.Title
        holder.binding.textYear.text = book.Year.toString()
    }
}