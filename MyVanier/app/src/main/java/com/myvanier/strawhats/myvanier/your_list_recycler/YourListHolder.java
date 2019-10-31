package com.myvanier.strawhats.myvanier.your_list_recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.R;
import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;
import com.myvanier.strawhats.myvanier.dbController.TeacherListController;

import java.util.List;

public class YourListHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView txtName;
    private TextView txtDepartment;
    private ImageView addBtn;
    private Teacher teacher;
    TeacherListController tlc;
    private List<Teacher> personalList;
    YourListAdapter yourListAdapter;


    /**
     * Instantiates the yourlist holder
     * @param itemView
     * @param iYourListAdapter
     * @param iPersonalList
     */
    public YourListHolder(@NonNull View itemView, YourListAdapter iYourListAdapter, List<Teacher> iPersonalList)
    {
        super(itemView);

        this.addBtn = (ImageView) itemView.findViewById(R.id.addTeacherImage);
        this.addBtn.setOnClickListener(this);

        this.addBtn.setImageResource(R.drawable.remove_button);
        this.addBtn.setId(R.id.removeBtn);

        this.personalList = iPersonalList;
        this.yourListAdapter = iYourListAdapter;

        this.txtName = (TextView) itemView.findViewById(R.id.txtName);
        this.txtDepartment = (TextView) itemView.findViewById(R.id.txtDepartment);

        tlc = new TeacherListController(itemView.getContext());
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
        Teacher teacherToRemove = personalList.get(position);

        this.personalList.remove(position);
        this.yourListAdapter.notifyItemRemoved(position);
        this.yourListAdapter.notifyItemRangeChanged(position, this.personalList.size());

        tlc.removeTeacher(teacherToRemove);
    }
}
