package com.myvanier.strawhats.myvanier.dbController.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Account implements Parcelable {
    private String username;
    private String password;
    private String profileName;

    public Account() {

    }

    public Account(String username, String password, String profileName) {
        this.username = username;
        this.password = password;
        this.profileName = profileName;
    }

    protected Account(Parcel in) {
        username = in.readString();
        password = in.readString();
        profileName = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(profileName);
    }
}
