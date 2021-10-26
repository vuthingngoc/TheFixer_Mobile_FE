package com.example.thefixer;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountDTO implements Parcelable {
    private String username, password, role;

    public AccountDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public AccountDTO(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<AccountDTO> CREATOR = new Parcelable.Creator<AccountDTO>() {
        public AccountDTO createFromParcel(Parcel in) {
            return new AccountDTO(in);
        }

        public AccountDTO[] newArray(int size) {

            return new AccountDTO[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(role);
    }

    public void readFromParcel(Parcel parcel) {
        username = parcel.readString();
        password = parcel.readString();
        role = parcel.readString();

    }
}
