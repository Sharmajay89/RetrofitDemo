package com.service.retrofitdemo.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ajaya on 19/9/17.
 */

public class UserInfo implements Parcelable{
    String name;
    String email;
    String contactDetails;

    protected UserInfo(Parcel in) {
        name = in.readString();
        email = in.readString();
        contactDetails = in.readString();
    }

    public UserInfo() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(contactDetails);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
