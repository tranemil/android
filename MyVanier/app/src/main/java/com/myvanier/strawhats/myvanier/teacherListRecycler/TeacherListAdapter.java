package com.myvanier.strawhats.myvanier.teacherListRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;

import java.util.List;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListHolder>
{
    private List<Teacher> teacherList;

    /**
     * Instantiates the teacherListAdapter
     * @param teacherList
     */
    public TeacherListAdapter(List<Teacher> teacherList)
    {
        this.teacherList = teacherList;
    }


    /**
     * Called when a new row is created to represent an item in the list
     * @param viewGroup
     * @param i
     * @return the view holder
     */
    @NonNull
    @Override
    public TeacherListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        Context context = viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.teacher_item_layout, viewGroup, false);

        TeacherListHolder teacherListHolder = new TeacherListHolder(view, this, teacherList);

        return teacherListHolder;
    }

    /**
     * Called to bind the view holder with the required data
     * @param teacherListHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TeacherListHolder teacherListHolder, int position)
    {
        Teacher teacher = teacherList.get(position);

        teacherListHolder.setTeacher(teacher);
    }

    /**
     * Gets how many items are in the list
     * @return the size of the list
     */
    @Override
    public int getItemCount()
    {
        return teacherList.size();
    }
}
