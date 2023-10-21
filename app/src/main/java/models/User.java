package models;

import androidx.annotation.NonNull;

public class User {
    private long userId;
    private String userName;
    private String password;
    private Profile profile;
    public User() {
    }

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public User(long userId, String userName, String password){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "userId = " + userId +
                ", userName = " + userName + '\'' +
                '}';
    }
}
