package ru.fixedfox.musicservice.dto;

import java.util.Objects;

public class TelegramNameTelgramId {
    String telegramName;
    Long telegramId;

    public TelegramNameTelgramId() {
    }

    public String getTelegramName() {
        return telegramName;
    }

    public void setTelegramName(String telegramName) {
        this.telegramName = telegramName;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramNameTelgramId user = (TelegramNameTelgramId) o;
        return Objects.equals(getTelegramName(), user.getTelegramName()) && Objects.equals(getTelegramId(), user.getTelegramId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTelegramName(), getTelegramId());
    }
}
