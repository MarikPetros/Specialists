package com.example.user.specialists.repozitory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.user.specialists.data_provider.RemoteDataProvider;
import com.example.user.specialists.model.Employee;
import com.example.user.specialists.model.EmployeeDao;
import com.example.user.specialists.model.SpecialistRoomDB;
import com.example.user.specialists.model.Specialty;
import com.example.user.specialists.model.SpecialtyDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SpecialistRepository {
    private Context context;
    String url;
    private RemoteDataProvider remoteDataProvider;
    private final EmployeeDao employeeDao;
    private final SpecialtyDao specialtyDao;
    private List<Employee> employees;


    @Inject
    public SpecialistRepository(Context context, RemoteDataProvider remoteDataProvider, String url) {
        this.context = context;
        this.url = url;
        SpecialistRoomDB db = SpecialistRoomDB.getDataBase(context);
        employeeDao = db.mEmpDao();
        specialtyDao = db.mSpecDao();
        this.remoteDataProvider = remoteDataProvider;
    }

    public LiveData<List<Employee>> getAllEmployeeList() {
        refreshList(url);
        // Returns a LiveData object directly from the database.
        return employeeDao.getEmployees();
    }

    private void refreshList(final String url) {
        // Runs in a background thread.

            // Check if user data was fetched recently.
            if (!haveConnection()) {
                // Refreshes the data.
                employees =  getEmployeeList(url);

                // Updates the database. The LiveData object automatically
                // refreshes, so we don't need to do anything else here.
                employeeDao.insertAll(employees);
            }
    }

    private List<Employee> getEmployeeList(String url) {
        return remoteDataProvider.loadRemoteData(url);
    }

    //retrieving the list of employees by specialty
    public  LiveData<List<Employee>> getSpecialists(String specialty){
        final MutableLiveData<List<Employee>> data = new MutableLiveData<>();

        List<Employee> specialists = new ArrayList<>();
        for(Employee e : employees){
            List<Specialty> specialties = e.getSpecialtyList();
            for (int i = 0; i < specialties.size(); i++) {
                if (specialties.get(i).getSpecName().equals(specialty)){
                    specialists.add(e);
                }
            }
        }

        data.setValue(specialists);
        return data;
    }

    public LiveData<Employee> getEmployeeByID(int id) {
        // Returns a LiveData object directly from the database.
        return employeeDao.getEmployeeById(id);
    }

    public LiveData<List<Specialty>> getSpecialties() {
        // Returns a LiveData object directly from the database.
        return specialtyDao.getSpecialties();
    }

   private boolean haveConnection(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
       return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

   /* public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }*/

}
