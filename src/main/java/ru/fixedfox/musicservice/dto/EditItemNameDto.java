package ru.fixedfox.musicservice.dto;

public class EditItemNameDto {
    private String itemName;
    private Long itemId;

    public EditItemNameDto() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
