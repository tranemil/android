package com.myvanier.strawhats.myvanier.personal_library_recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Book;
import com.myvanier.strawhats.myvanier.dbController.PersonalLibraryController;
import com.myvanier.strawhats.myvanier.library_recycler.LibraryAdapter;
import com.myvanier.strawhats.myvanier.library_recycler.LibraryViewHolder;

import java.util.List;

public class PersonalLibraryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView txtTitle;
    private TextView txtAuthor;
    private ImageView addBtn;
    private Book book;
    PersonalLibraryController personalLibraryController;
    private List<Book> personalList;
    private PersonalLibraryAdapter personalLibraryAdapter;

    /**
     * Instantiates the personal library list holder
     *
     * @param itemView
     * @param iPersonalLibraryAdapter
     * @param iPersonalLibraryList
     */
    public PersonalLibraryViewHolder(@NonNull View itemView, PersonalLibraryAdapter iPersonalLibraryAdapter, List<Book> iPersonalLibraryList) {
        super(itemView);

        this.addBtn = (ImageView) itemView.findViewById(R.id.addBookImage);
        this.addBtn.setOnClickListener(this);

        this.addBtn.setImageResource(R.drawable.remove_button);
        this.addBtn.setId(R.id.removeBtn);

        this.personalList = iPersonalLibraryList;
        this.personalLibraryAdapter = iPersonalLibraryAdapter;

        this.txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        this.txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);

        personalLibraryController = new PersonalLibraryController(itemView.getContext());
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
        Context context = v.getContext();
        int position = getAdapterPosition();

        if(v.getId() == R.id.removeBtn)
        {
            deleteItem(position);
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Deletes the item from the recycler view and the database at the specified position
     * @param position
     */
    private void deleteItem(int position)
    {
        Book bookToRemove = personalList.get(position);

        this.personalList.remove(position);
        this.personalLibraryAdapter.notifyItemRemoved(position);
        this.personalLibraryAdapter.notifyItemRangeChanged(position, this.personalList.size());

        personalLibraryController.removeBook(bookToRemove);
    }

}
