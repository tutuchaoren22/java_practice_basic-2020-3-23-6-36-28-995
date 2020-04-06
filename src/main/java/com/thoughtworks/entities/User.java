package com.thoughtworks.entities;

public class User {
    int id;
    String name;
    String telephoneNumber;
    String email;
    String password;
    int errorNumber;
    boolean isLocked;

    public User() {
    }

    public User( String name, String telephoneNumber, String email, String password) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
    }
    public User(int id, String name, String telephoneNumber, String email, String password, int errorNumber, boolean isLocked) {
        this.id = id;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.errorNumber = errorNumber;
        this.isLocked = isLocked;
    }

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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", errorNumber=" + errorNumber +
                ", isLocked=" + isLocked +
                '}';
    }
}