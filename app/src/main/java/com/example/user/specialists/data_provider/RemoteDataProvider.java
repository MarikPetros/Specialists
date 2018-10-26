package com.example.user.specialists.data_provider;

import android.os.AsyncTask;

import com.example.user.specialists.model.Employee;
import com.example.user.specialists.model.SpecResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RemoteDataProvider  {

    //Method that will parse the JSON file and will return a JSONObject
    public  List<Employee> loadRemoteData(String url) {
        List<Employee> employeeList = new ArrayList<>();

        try {
           employeeList =  new FetchAsync().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    //Class to fetch remote data asynchronously
    private static class FetchAsync extends AsyncTask<String,Void,List<Employee>> {
        @Override
        protected List<Employee> doInBackground(String... urls) {
            SpecResponse response;
            HttpURLConnection connection = null;
            try {
                URL url = new java.net.URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuilder json = new StringBuilder(1024);
                String tmp;
                while((tmp = reader.readLine()) != null) {
                    json.append(tmp).append("\n");
                }
                reader.close();

                response = new Gson().fromJson(json.toString(), SpecResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            if (response != null) {
                return response.employeeList;
            } else {
                return new ArrayList<>();
            }
        }
    }
}
