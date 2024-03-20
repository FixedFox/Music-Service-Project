package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Creator;

import java.util.Set;

public class GetUserCreatorsDto {
    private String name;
    private Set<Creator> creators;
    private String telegramName;
    private Boolean TelegramConnected;

    public GetUserCreatorsDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Creator> getCreators() {
        return creators;
    }

    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    public String getTelegramName() {
        return telegramName;
    }

    public void setTelegramName(String telegramName) {
        this.telegramName = telegramName;
    }

    public Boolean isTelegramConnect() {
        return TelegramConnected;
    }

    public void setTelegramConnect(Boolean telegramConnect) {
        TelegramConnected = telegramConnect;
    }
}
