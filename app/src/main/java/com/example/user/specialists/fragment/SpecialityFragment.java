package com.example.user.specialists.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.specialists.R;
import com.example.user.specialists.fragment.adapter.EmployeesAdapter;
import com.example.user.specialists.fragment.adapter.SpeciatliesAdapter;
import com.example.user.specialists.ui.main.SpecialistsViewModel;
import com.example.user.specialists.model.Specialty;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.specialists.fragment.EmployeesFragment.SPECIALTY;

public class SpecialityFragment extends Fragment {

    private List<Specialty> specialtyList = new ArrayList<>();
    private OnItemTapListener onItemTapListener;

    public void setOnItemTapListener(OnItemTapListener onItemTapListener) {
        this.onItemTapListener = onItemTapListener;
    }

    private SpeciatliesAdapter.onItemSelectedListener mAdapterListener = new SpeciatliesAdapter.onItemSelectedListener(){
        @Override
        public void onItemSelected(Specialty specialty) {
            onItemTapListener.getSpecialtyName(specialty.getSpecName());
        }
    };

    public static SpecialityFragment newInstance() {
        return new SpecialityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.speciality_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SpecialistsViewModel mViewModel = ViewModelProviders.of(this).get(SpecialistsViewModel.class);
        mViewModel.getListSpecialtyLiveData().observe(this, new Observer<List<Specialty>>() {
            @Override
            public void onChanged(@Nullable List<Specialty> specialties) {
                if (specialties != null) {
                    specialtyList.addAll(specialties);
                }
                else {
                    Toast.makeText(getContext(),"There is no Specialties in the list!",Toast.LENGTH_LONG).show();
                }
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
        SpeciatliesAdapter mSpecialtyAdapter = new SpeciatliesAdapter(specialtyList);
        mSpecialtyAdapter.setOnItemSelectedListener(mAdapterListener);
        mRecyclerView.setAdapter(mSpecialtyAdapter);
    }

    public interface OnItemTapListener{
        void getSpecialtyName(String specName);
    }
}
