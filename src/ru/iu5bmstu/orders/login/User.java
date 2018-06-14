package ru.iu5bmstu.orders.login;

public class User {
    private String username;
    private String passwordHash;

    private UserType userType;

    public User(String username, String passwordHash, UserType userType) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
