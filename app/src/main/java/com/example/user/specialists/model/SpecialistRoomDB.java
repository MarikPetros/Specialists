package com.example.user.specialists.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {Employee.class, Specialty.class}, version=1)
@TypeConverters(Converter.class)
public abstract class SpecialistRoomDB extends RoomDatabase {

    private static String DB_NAME="specialist_db";

    public abstract EmployeeDao mEmpDao();
    public abstract SpecialtyDao mSpecDao();

    private static volatile SpecialistRoomDB sInstance;

    public static SpecialistRoomDB getDataBase(Context context){
        if (sInstance == null) {
            synchronized (SpecialistRoomDB.class) {
                if (sInstance == null && context != null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            SpecialistRoomDB.class, DB_NAME).fallbackToDestructiveMigration()
                            .build();
                }else {
                    Log.e("db", "context is null, DB is not available!");}
            }
        }
        return sInstance;
    }
}
