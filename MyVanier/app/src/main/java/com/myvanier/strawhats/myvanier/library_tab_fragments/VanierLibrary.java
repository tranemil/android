package com.myvanier.strawhats.myvanier.library_tab_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.LibraryController;
import com.myvanier.strawhats.myvanier.dbController.Model.Book;
import com.myvanier.strawhats.myvanier.library_recycler.LibraryAdapter;
import com.myvanier.strawhats.myvanier.library_recycler.LibraryViewHolder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class VanierLibrary extends Fragment
{
    InputStream inputStream;
/*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_vanier_library, container, false);



        try {
            inputStream = rootView.getContext().openFileInput("myFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader((inputStream)));
            Toast.makeText(rootView.getContext(), "" + reader.readLine(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;
    }*/

    LibraryController libraryController = new LibraryController(getContext());

    private RecyclerView recyclerView;

    private RecyclerView.Adapter<LibraryViewHolder> adapter;

    private RecyclerView.LayoutManager layoutManager;

    List<Book> bookList;

    /**
     * Instantiates a default searchTeacher fragment
     */
    public VanierLibrary()
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
        final View rootView = inflater.inflate(R.layout.fragment_vanier_library, container, false);

        SearchView searchBar = (SearchView) rootView.findViewById(R.id.searchLibraryBar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            /**
             * Does specified action when input in search view is submitted
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query)
            {
//                recyclerView = (RecyclerView) rootView.findViewById(R.id.teacherRecyclerView);
//                layoutManager = new LinearLayoutManager(getContext());
//                teacherList = teacherController.readAllFiltered(query);
//
//                adapter = new TeacherListAdapter(teacherList);
//
//                recyclerView.setLayoutManager(layoutManager);
//
//                recyclerView.setAdapter(adapter);
//
//                RecyclerView cardName = (RecyclerView) rootView.findViewById(R.id.teacherRecyclerView);
                return false;
            }

            /**
             * Does specified action when something is inputted into the search view
             * @param newText
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText)
            {
                recyclerView = (RecyclerView) rootView.findViewById(R.id.libraryRecyclerView);
                layoutManager = new LinearLayoutManager(getContext());
                if (newText.isEmpty())
                {
                    final int size = bookList.size();
                    bookList.clear();
                    adapter.notifyItemRangeRemoved(0, size);
                }
                else {
                    bookList = libraryController.readAllFiltered(newText);

                    adapter = new LibraryAdapter(bookList);

                    recyclerView.setLayoutManager(layoutManager);

                    recyclerView.setAdapter(adapter);

                    //RecyclerView cardName = (RecyclerView) rootView.findViewById(R.id.teacherRecyclerView);
                }
                return false;
            }
        });
        return rootView;
    }
}
