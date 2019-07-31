package com.challenge.overwatchapi.model;

import com.challenge.overwatchapi.dto.AbilityDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AbilityEntity {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Column(length = 511) //FixMe: thanks to Junkrats ult description default length of 255 is not enough.
    private String description;
    private boolean isUltimate;

    public AbilityEntity() {
    }

    public AbilityEntity(String name, String description, boolean isUltimate) {
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
}
