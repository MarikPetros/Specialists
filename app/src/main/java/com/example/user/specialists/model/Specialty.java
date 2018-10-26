package com.example.user.specialists.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "specialty")
public class Specialty implements Parcelable {
    @PrimaryKey
    @SerializedName("specialty_id")
    private int specId;

    @SerializedName("name")
    private String specName;

    public Specialty(int specId, String specName) {
        this.specId = specId;
        this.specName = specName;
    }

    private Specialty(Parcel in) {
        specId = in.readInt();
        specName = in.readString();
    }

    public static final Creator<Specialty> CREATOR = new Creator<Specialty>() {
        @Override
        public Specialty createFromParcel(Parcel in) {
            return new Specialty(in);
        }

        @Override
        public Specialty[] newArray(int size) {
            return new Specialty[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(specId);
        dest.writeString(specName);
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public int getSpecId() {
        return specId;
    }

    public String getSpecName() {
        return specName;
    }
}
