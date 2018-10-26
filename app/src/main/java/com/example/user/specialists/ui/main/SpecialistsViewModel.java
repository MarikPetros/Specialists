package com.example.user.specialists.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.user.specialists.model.Employee;
import com.example.user.specialists.model.Specialty;
import com.example.user.specialists.repozitory.SpecialistRepository;

import java.util.List;

import javax.inject.Inject;

public class SpecialistsViewModel extends ViewModel {
    private SpecialistRepository repository;
    private LiveData<List<Specialty>> listSpecialtyLiveData;
    private LiveData<Employee> employeeLiveData;
    private LiveData<List<Employee>> listEmployeeLiveData;

    @Inject
    public SpecialistsViewModel(SpecialistRepository specialistRepository) {
        repository = specialistRepository;
    }


    public LiveData<List<Specialty>> getListSpecialtyLiveData()
    {
        listSpecialtyLiveData = repository.getSpecialties();
        return listSpecialtyLiveData;
    }

    public LiveData<Employee> getEmployeeLiveData(int id) {
        employeeLiveData = repository.getEmployeeByID(id);
        return employeeLiveData;
    }

    public LiveData<List<Employee>> getListEmployeeLiveData() {
        return listEmployeeLiveData;
    }

    public LiveData<List<Employee>> getEmployeesBySpec(String specialty) {
        return repository.getSpecialists(specialty);
    }
}
