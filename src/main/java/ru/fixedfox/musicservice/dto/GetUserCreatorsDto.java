package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Creator;

import java.util.Set;

public class GetUserCreatorsDto {
    private String name;
    private Set<Creator> creators;

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
}
