package com.myvanier.strawhats.myvanier.teacherListRecycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.TeacherInformation;
import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;

import com.myvanier.strawhats.myvanier.dbController.TeacherListController;
import com.myvanier.strawhats.myvanier.teacherListTabFragments.YourList;


import java.util.List;

public class TeacherListHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView txtName;
    private TextView txtDepartment;
    private ImageView addBtn;
    private List<Teacher> teacherList;
    private Teacher teacher;
    TeacherListAdapter teacherListAdapter;
    TeacherListController tlc;
    private List<Teacher> personalList;

    /**
     * Instantiates the teacher list holder
     * @param itemView
     * @param iTeacherListAdapter
     * @param iTeacherList
     */
    public TeacherListHolder(@NonNull View itemView, TeacherListAdapter iTeacherListAdapter, List<Teacher> iTeacherList)
    {
        super(itemView);

        this.addBtn = (ImageView) itemView.findViewById(R.id.addTeacherImage);
        this.addBtn.setOnClickListener(this);

        this.teacherList = iTeacherList;
        this.teacherListAdapter = iTeacherListAdapter;

        this.txtName = (TextView) itemView.findViewById(R.id.txtName);
        this.txtDepartment = (TextView) itemView.findViewById(R.id.txtDepartment);
    }

    /**
     * Sets the teacher object in the view holder
     * @param teacher
     */
    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
        this.txtName.setText(teacher.getName());
        this.txtDepartment.setText(teacher.getDepartment());
    }

    /**
     * Gets the text name inside the view holder
     * @return the text name
     */
    public TextView getTxtName()
    {
        return txtName;
    }

    /**
     * Event done when clicking the remove button
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.addTeacherImage)
        {
            tlc = new TeacherListController(v.getContext());
            tlc.createTeacher(new Teacher(teacher.getName(), teacher.getDepartment(), teacher.getPhone(), teacher.getEmail(), teacher.getExtension(), teacher.getOffice()));
            Toast.makeText(v.getContext(), teacher.getName() + " successfully added", Toast.LENGTH_SHORT).show();
        }
    }
}
