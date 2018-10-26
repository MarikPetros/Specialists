package com.example.user.specialists.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SpecialtyDao {
    @Query("SELECT * FROM specialty ORDER BY specId")
    LiveData<List<Specialty>> getSpecialties();
}
