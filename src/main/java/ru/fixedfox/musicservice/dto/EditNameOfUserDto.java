package ru.fixedfox.musicservice.dto;

public class EditNameOfUserDto {
    String userName;
    String name;

    public EditNameOfUserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
