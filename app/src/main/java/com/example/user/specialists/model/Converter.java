package com.example.user.specialists.model;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;


public class Converter {
    @TypeConverter
    public String SpecListToString(List<Specialty> specialties){
        StringBuilder strSpecialties = new StringBuilder();
        for (Specialty e : specialties){
            strSpecialties.append(e.getSpecId()).append(',').append(e.getSpecName()).append('\n');
        }
        return strSpecialties.toString();
    }

    @TypeConverter
    public List<Specialty> StringToSpecialtyList(String specialties){
        List<Specialty> specialtyList = new ArrayList<>();
        String[] stringList = specialties.split("\n");
        for (String s : stringList){
            String[] idName = s.split(",");
            Specialty specialty = new Specialty(Integer.parseInt(idName[0]),idName[1]);
            specialtyList.add(specialty);
        }
        return specialtyList;
    }
}
