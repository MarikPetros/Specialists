package com.example.user.specialists.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.specialists.R;
import com.example.user.specialists.model.Employee;
import com.example.user.specialists.model.Specialty;
import com.example.user.specialists.ui.main.SpecialistsViewModel;
import com.example.user.specialists.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EmployeeFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_ID = "item_id";

    // TODO: Customize parameters
    public static EmployeeFragment newInstance(int id) {
        final EmployeeFragment fragment = new EmployeeFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(final View root) {
        final TextView name = root.findViewById(R.id.employee_name);
        final TextView surName = root.findViewById(R.id.employee_surname);
        final TextView birthDate = root.findViewById(R.id.employee_birth_date);
        final TextView age = root.findViewById(R.id.employee_age);
        final TextView specialty = root.findViewById(R.id.employee_speciality);

        int _id = 0;
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(ARG_ITEM_ID)) {
                _id = args.getInt(ARG_ITEM_ID);
            } else {
                Toast.makeText(getContext(), "Specialty not found", Toast.LENGTH_LONG).show();
            }
        }

        SpecialistsViewModel mViewModel = ViewModelProviders.of(this).get(SpecialistsViewModel.class);
        mViewModel.getEmployeeLiveData(_id).observe(this, new Observer<Employee>() {
            @Override
            public void onChanged(@Nullable Employee employee) {
                name.setText(employee.getFirstName());
                surName.setText(employee.getLastName());
                birthDate.setText(DateUtil.formatDateStile(employee.getBirthday()));

                //calculate age
                Date date = new Date();
                long ageMs = date.getTime() - DateUtil.getDate(employee.getBirthday()).getTime();
                int yearsCount = (int) (ageMs / 365 * 24 * 60 * 60 * 1000);

                age.setText(String.format("возраст %d лет",age));
                List<Specialty> specialties = employee.getSpecialtyList();
                StringBuilder stringSpecialties = new StringBuilder();
                for(Specialty specialty1 : specialties){
                    stringSpecialties.append(specialty);
                }
                specialty.setText(stringSpecialties.toString());
            }
        });
    }
}
