package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.User;

public class NewCreatorDto {
    String creatorName;

    User user;

    public NewCreatorDto() {
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
