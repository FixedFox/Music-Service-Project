package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Authority;

import java.util.Set;

public class GetUserMainPageDto {
    private String name;
    private Set<String> authorities;

    public GetUserMainPageDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isAuthority(String authorityName){
        return authorities.contains(authorityName);
    }
}
