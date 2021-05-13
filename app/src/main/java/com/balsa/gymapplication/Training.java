package com.balsa.gymapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Training implements Parcelable {
    private int id;
    private String name;
    private String shortDiscription;
    private String longDiscription;
    private String imageUrl;

    public Training(int id, String name, String shortDiscription, String longDiscription, String imageUrl) {
        this.id = id;
        this.name = name;
        this.shortDiscription = shortDiscription;
        this.longDiscription = longDiscription;
        this.imageUrl = imageUrl;
    }

    protected Training(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortDiscription = in.readString();
        longDiscription = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDiscription() {
        return shortDiscription;
    }

    public String getLongDiscription() {
        return longDiscription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDiscription='" + shortDiscription + '\'' +
                ", longDiscription='" + longDiscription + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(shortDiscription);
        dest.writeString(longDiscription);
        dest.writeString(imageUrl);
    }
}
