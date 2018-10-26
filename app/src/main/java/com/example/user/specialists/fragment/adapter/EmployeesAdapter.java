package com.example.user.specialists.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.specialists.R;
import com.example.user.specialists.fragment.adapter.view_holder.EmployeesViewHolder;
import com.example.user.specialists.model.Employee;
import com.example.user.specialists.utils.DateUtil;

import java.util.Date;
import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesViewHolder> {
    private List<Employee> employees;
    private onItemSelectedListener itemSelectedListener;


    public EmployeesAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_in_list, viewGroup, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder employeesViewHolder, int i) {
        final Employee employee = employees.get(i);

        //calculate age
        Date date = new Date();
        long ageMs = date.getTime() - DateUtil.getDate(employee.getBirthday()).getTime();
        int age = (int) (ageMs / 365 * 24 * 60 * 60 * 1000);

        employeesViewHolder.getName().setText(employee.getFirstName().toUpperCase());
        employeesViewHolder.getSurName().setText(employee.getLastName().toUpperCase());
        employeesViewHolder.getAge().setText(String.format("возраст %d лет",age));

        employeesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.onItemSelected(employee);
            }
        });
    }


    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setOnItemSelectedListener(onItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }


    public interface onItemSelectedListener {
        void onItemSelected(Employee employee);
    }
}
