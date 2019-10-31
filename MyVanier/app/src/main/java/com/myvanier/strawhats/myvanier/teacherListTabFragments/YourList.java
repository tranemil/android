package com.myvanier.strawhats.myvanier.teacherListTabFragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;
import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.TeacherListController;
import com.myvanier.strawhats.myvanier.teacherListRecycler.TeacherListAdapter;
import com.myvanier.strawhats.myvanier.teacherListRecycler.TeacherListHolder;
import com.myvanier.strawhats.myvanier.your_list_recycler.YourListAdapter;
import com.myvanier.strawhats.myvanier.your_list_recycler.YourListHolder;

import java.util.List;

public class YourList extends Fragment {

    TeacherListController teacherListController = new TeacherListController(getContext());

    private RecyclerView recyclerView;

    private RecyclerView.Adapter<YourListHolder> adapter;

    private RecyclerView.LayoutManager layoutManager;

    List<Teacher> teacherList;

    private View rootView;

    private TabLayout tabLayout;


    /**
     * Instantiates a default yourList fragment
     */
    public YourList()
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
        rootView = inflater.inflate(R.layout.fragment_your_list, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.teacherListRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());

        teacherList = teacherListController.readAll();

        adapter = new YourListAdapter(teacherList);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        RecyclerView cardName = (RecyclerView) rootView.findViewById(R.id.teacherRecyclerView);


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
            recyclerView = (RecyclerView) rootView.findViewById(R.id.teacherListRecyclerView);
            layoutManager = new LinearLayoutManager(getContext());

            teacherList = teacherListController.readAll();

            adapter = new YourListAdapter(teacherList);

            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(adapter);
        }


    }
}
