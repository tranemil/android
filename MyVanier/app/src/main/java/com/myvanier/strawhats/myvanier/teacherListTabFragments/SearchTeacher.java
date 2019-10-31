package com.myvanier.strawhats.myvanier.teacherListTabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;
import com.myvanier.strawhats.myvanier.dbController.TeacherController;
import com.myvanier.strawhats.myvanier.teacherListRecycler.TeacherListAdapter;
import com.myvanier.strawhats.myvanier.teacherListRecycler.TeacherListHolder;

import java.util.List;

public class SearchTeacher extends Fragment
{
    TeacherController teacherController = new TeacherController(getContext());

    private RecyclerView recyclerView;

    private RecyclerView.Adapter<TeacherListHolder> adapter;

    private RecyclerView.LayoutManager layoutManager;

    List<Teacher> teacherList;

    /**
     * Instantiates a default searchTeacher fragment
     */
    public SearchTeacher()
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
        final View rootView = inflater.inflate(R.layout.fragment_search_teacher, container, false);

        SearchView searchBar = (SearchView) rootView.findViewById(R.id.searchBar);
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
                recyclerView = (RecyclerView) rootView.findViewById(R.id.teacherRecyclerView);
                layoutManager = new LinearLayoutManager(getContext());
                if (newText.isEmpty())
                {
                    final int size = teacherList.size();
                    teacherList.clear();
                    adapter.notifyItemRangeRemoved(0, size);
                }
                else {
                    teacherList = teacherController.readAllFiltered(newText);

                    adapter = new TeacherListAdapter(teacherList);

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
