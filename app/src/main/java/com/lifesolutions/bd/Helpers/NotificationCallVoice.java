package com.lifesolutions.bd.Helpers;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class NotificationCallVoice implements Parcelable {

    private HashMap data;

    public NotificationCallVoice() {
    }

    private NotificationCallVoice(Parcel in) {
        data = (HashMap) in.readValue(HashMap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NotificationCallVoice> CREATOR = new Parcelable.Creator<NotificationCallVoice>() {
        @Override
        public NotificationCallVoice createFromParcel(Parcel in) {
            return new NotificationCallVoice(in);
        }

        @Override
        public NotificationCallVoice[] newArray(int size) {
            return new NotificationCallVoice[size];
        }
    };

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
