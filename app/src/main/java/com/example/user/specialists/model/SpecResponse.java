package com.example.user.specialists.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecResponse implements Parcelable {
    @SerializedName("response")
    public List<Employee> employeeList;

    protected SpecResponse(Parcel in) {
        employeeList = in.createTypedArrayList(Employee.CREATOR);
    }

    public static final Creator<SpecResponse> CREATOR = new Creator<SpecResponse>() {
        @Override
        public SpecResponse createFromParcel(Parcel in) {
            return new SpecResponse(in);
        }

        @Override
        public SpecResponse[] newArray(int size) {
            return new SpecResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(employeeList);
    }
}
