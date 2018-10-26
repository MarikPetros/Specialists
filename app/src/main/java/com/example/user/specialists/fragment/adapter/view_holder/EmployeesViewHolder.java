package com.example.user.specialists.fragment.adapter.view_holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.specialists.R;

public class EmployeesViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView surName;
    private TextView age;

    public EmployeesViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.employee_name_inList);
        surName = itemView.findViewById(R.id.employee_surname_inList);
        age = itemView.findViewById(R.id.employee_age_inList);
    }

    public TextView getName() {
        return name;
    }

    public TextView getSurName() {
        return surName;
    }

    public TextView getAge() {
        return age;
    }
}
