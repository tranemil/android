package com.myvanier.strawhats.myvanier.library_recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Book;
import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryViewHolder>
{

    private List<Book> bookList;

    /**
     * Instantiates the LibraryAdapter
     * @param bookList
     */
    public LibraryAdapter(List<Book> bookList)
    {
        this.bookList = bookList;
    }

    /**
     * Called when a new row is created to represent an item in the list
     * @param viewGroup
     * @param i
     * @return the view holder
     */
    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        Context context = viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.book_item_layout, viewGroup, false);

        LibraryViewHolder libraryViewHolder = new LibraryViewHolder(view, this, bookList);

        return libraryViewHolder;
    }

    /**
     * Called to bind the view holder with the required data
     * @param libraryViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder libraryViewHolder, int position)
    {
        Book book = bookList.get(position);

        libraryViewHolder.setBook(book);
    }

    /**
     * Gets how many items are in the list
     * @return the size of the list
     */
    @Override
    public int getItemCount()
    {
        return bookList.size();
    }
}
