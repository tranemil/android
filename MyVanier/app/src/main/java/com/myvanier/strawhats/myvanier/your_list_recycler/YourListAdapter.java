package com.myvanier.strawhats.myvanier.your_list_recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;
import com.myvanier.strawhats.myvanier.teacherListRecycler.TeacherListHolder;

import java.util.List;

public class YourListAdapter extends RecyclerView.Adapter<YourListHolder>
{
    private List<Teacher> teacherList;

    public YourListAdapter(List<Teacher> teacherList)
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
    public YourListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        Context context = viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.teacher_item_layout, viewGroup, false);

        YourListHolder yourListHolder = new YourListHolder(view, this, teacherList);

        return yourListHolder;
    }


    /**
     * Called to bind the view holder with the required data
     * @param yourListHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull YourListHolder yourListHolder, int position)
    {
        Teacher teacher = teacherList.get(position);

        yourListHolder.setTeacher(teacher);
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
