package com.challenge.overwatchapi.external;

import com.challenge.overwatchapi.dto.HeroDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero {

    private long id;
    private String name;
    @JsonProperty("real_name")
    private String realName;
    private int health;
    private int armour;
    private int shield;

    public Hero() {
    }

    public Hero(long id, String name, String realName, int health, int armour, int shield) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.health = health;
        this.armour = armour;
        this.shield = shield;
    }

    public HeroDto toDto() {
        return new HeroDto(id, name, realName, health, armour, shield);
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
}
