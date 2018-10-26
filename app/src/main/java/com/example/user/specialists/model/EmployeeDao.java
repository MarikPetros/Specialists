package com.example.user.specialists.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employees ORDER BY _id")
    LiveData<List<Employee>> getEmployees();

    @Query("SELECT * FROM employees WHERE _id = :id")
    LiveData<Employee> getEmployeeById(int id);

    @Insert(onConflict = REPLACE)
    void insertAll(List<Employee> employees);

}
