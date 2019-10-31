package com.myvanier.strawhats.myvanier.library_recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.LibraryController;
import com.myvanier.strawhats.myvanier.dbController.Model.Book;
import com.myvanier.strawhats.myvanier.dbController.PersonalLibraryController;

import java.util.List;

public class LibraryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView txtTitle;
    private TextView txtAuthor;
    private ImageView addBtn;
    private List<Book> bookList;
    private Book book;
    LibraryAdapter libraryAdapter;
    PersonalLibraryController personalLibraryController;

    /**
     * Instantiates the book list holder
     * @param itemView
     * @param iLibraryAdapter
     * @param iLibrary
     */
    public LibraryViewHolder(@NonNull View itemView, LibraryAdapter iLibraryAdapter, List<Book> iLibrary)
    {
        super(itemView);

        this.addBtn = (ImageView) itemView.findViewById(R.id.addBookImage);
        this.addBtn.setOnClickListener(this);

        this.bookList = iLibrary;
        this.libraryAdapter = iLibraryAdapter;

        this.txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        this.txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
    }

    /**
     * Sets the book object in the view holder
     * @param book
     */
    public void setBook(Book book)
    {
        this.book = book;
        this.txtTitle.setText(book.getTitle());
        this.txtAuthor.setText(book.getAuthor());
    }

    /**
     * Gets the text name inside the view holder
     * @return the text name
     */
    public TextView getTxtTitle()
    {
        return txtTitle;
    }

    /**
     * Event done when clicking the remove button
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.addBookImage)
        {
            personalLibraryController = new PersonalLibraryController(v.getContext());
            personalLibraryController.createBook(new Book(book.getTitle(), book.getAuthor()));
            Toast.makeText(v.getContext(), book.getTitle() + " successfully added", Toast.LENGTH_SHORT).show();
        }
    }
}

