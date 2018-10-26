package com.example.user.specialists.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.specialists.R;
import com.example.user.specialists.fragment.adapter.EmployeesAdapter;
import com.example.user.specialists.model.Employee;
import com.example.user.specialists.ui.main.SpecialistsViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeesFragment extends Fragment {
    public static final String SPECIALTY = "specialty";
    private List<Employee> employeeList = new ArrayList<>();

    private EmployeesAdapter.onItemSelectedListener mAdapterListener = new EmployeesAdapter.onItemSelectedListener() {
        @Override
        public void onItemSelected(Employee employee) {
            BottomSheetDialogFragment bottomSheetDialogFragment = EmployeeFragment.newInstance(employee.getmId());
            bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getTag());
        }
    };

    public static EmployeesFragment newInstance(String specialty){
        final EmployeesFragment fragment = new EmployeesFragment();
        final Bundle args = new Bundle();
        args.putString(SPECIALTY, specialty);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String specialtyName = "";
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(SPECIALTY)) {
               specialtyName = args.getString(SPECIALTY);
            } else {
                Toast.makeText(getContext(),"Specialty not found",Toast.LENGTH_LONG).show();
            }
        }

        SpecialistsViewModel mViewModel = ViewModelProviders.of(this).get(SpecialistsViewModel.class);
        mViewModel.getEmployeesBySpec(specialtyName).observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                employeeList = employees;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view) {
        RecyclerView mRecyclerView = view.findViewById(R.id.employee_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        EmployeesAdapter mEmployeesAdapter = new EmployeesAdapter(employeeList);
        mEmployeesAdapter.setOnItemSelectedListener(mAdapterListener);
        mRecyclerView.setAdapter(mEmployeesAdapter);
    }
}
