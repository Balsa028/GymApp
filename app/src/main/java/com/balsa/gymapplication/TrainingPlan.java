package com.balsa.gymapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainingPlan implements Parcelable {
    private Training training;
    private int minutes;
    private String day;
    private boolean isAccomplished;

    public TrainingPlan(Training training, int minutes, String day, boolean isAccomplished) {
        this.training = training;
        this.minutes = minutes;
        this.day = day;
        this.isAccomplished = isAccomplished;
    }

    protected TrainingPlan(Parcel in) {
        training = in.readParcelable(Training.class.getClassLoader());
        minutes = in.readInt();
        day = in.readString();
        isAccomplished = in.readByte() != 0;
    }

    public static final Creator<TrainingPlan> CREATOR = new Creator<TrainingPlan>() {
        @Override
        public TrainingPlan createFromParcel(Parcel in) {
            return new TrainingPlan(in);
        }

        @Override
        public TrainingPlan[] newArray(int size) {
            return new TrainingPlan[size];
        }
    };

    @Override
    public String toString() {
        return "TrainingPlan{" +
                "training=" + training +
                ", minutes=" + minutes +
                ", day='" + day + '\'' +
                ", isAccomplished=" + isAccomplished +
                '}';
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getDay() {
        return day;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(training, flags);
        dest.writeInt(minutes);
        dest.writeString(day);
        dest.writeByte((byte) (isAccomplished ? 1 : 0));
    }
}
