package com.service.retrofitdemo.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ajaya on 20/9/17.
 */

public class Image implements Parcelable {
    String name;
    String timestamp;
    String small;
    String medium;
    String large;

    protected Image(Parcel in) {
        name = in.readString();
        timestamp = in.readString();
        small = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    public Image() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(timestamp);
        dest.writeString(small);
        dest.writeString(medium);
        dest.writeString(large);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
