package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Creator;

public class EditCreatorInItemDto {
    Long creatorId;

    Creator creator;
    Long itemId;

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public EditCreatorInItemDto() {
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
