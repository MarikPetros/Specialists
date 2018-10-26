package com.example.user.specialists.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity(tableName = "employees")
public class Employee implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_id")
    public int mId;

    @SerializedName("f_name")
    private String firstName;

    @SerializedName("l_name")
    private String lastName;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("avatr_url")
    private String avatarUrl;

    @SerializedName("specialty")
    private List<Specialty> specialtyList;

    public Employee() {
    }

    protected Employee(Parcel in) {
        mId = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readString();
        avatarUrl = in.readString();
        specialtyList = in.createTypedArrayList(Specialty.CREATOR);
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Random random = new Random();
        mId = random.nextInt(2000);
        dest.writeInt(mId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(birthday);
        dest.writeString(avatarUrl);
        dest.writeTypedList(specialtyList);
    }

    public int getmId() {
        return mId;
    }

    public void setmId(@NonNull int mId) {
        this.mId = mId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Specialty> getSpecialtyList() {
        return specialtyList;
    }

    public void setSpecialtyList(List<Specialty> specialtyList) {
        this.specialtyList = specialtyList;
    }
}
