package com.challenge.overwatchapi.external;

import com.challenge.overwatchapi.dto.AbilityDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ability {

    private long id;
    private String name;
    private String description;
    @JsonProperty("is_ultimate")
    private boolean isUltimate;
    private Hero hero;

    public Ability() {
    }

    public Ability(long id, String name, String description, boolean isUltimate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isUltimate = isUltimate;
    }

    public AbilityDto toDto() {
        return new AbilityDto(id, name, description, isUltimate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUltimate() {
        return isUltimate;
    }

    public void setUltimate(boolean ultimate) {
        isUltimate = ultimate;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
