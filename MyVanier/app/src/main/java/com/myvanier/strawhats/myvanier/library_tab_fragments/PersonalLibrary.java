package com.myvanier.strawhats.myvanier.library_tab_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Book;
import com.myvanier.strawhats.myvanier.dbController.PersonalLibraryController;
import com.myvanier.strawhats.myvanier.library_recycler.LibraryAdapter;
import com.myvanier.strawhats.myvanier.personal_library_recycler.PersonalLibraryAdapter;
import com.myvanier.strawhats.myvanier.personal_library_recycler.PersonalLibraryViewHolder;

import java.util.List;

public class PersonalLibrary extends Fragment
{
    PersonalLibraryController personalLibraryController = new PersonalLibraryController(getContext());

    private RecyclerView recyclerView;

    private RecyclerView.Adapter<PersonalLibraryViewHolder> adapter;

    private RecyclerView.LayoutManager layoutManager;

    List<Book> bookList;

    private View rootView;

    private TabLayout tabLayout;


    /**
     * Instantiates a default yourList fragment
     */
    public PersonalLibrary()
    {

    }

    /**
     * Called when the fragment is created
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_personal_library, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.personalLibraryRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());

        bookList = personalLibraryController.readAll();

        adapter = new PersonalLibraryAdapter(bookList);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        RecyclerView cardName = (RecyclerView) rootView.findViewById(R.id.personalLibraryRecyclerView);


        return rootView;
    }

    /**
     * Called when the fragment is visible to the user
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser)
        {
            recyclerView = (RecyclerView) rootView.findViewById(R.id.personalLibraryRecyclerView);
            layoutManager = new LinearLayoutManager(getContext());

            bookList = personalLibraryController.readAll();

            adapter = new PersonalLibraryAdapter(bookList);

            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(adapter);
        }


    }
}
