package com.challenge.overwatchapi.model;

import com.challenge.overwatchapi.dto.AbilityDto;
import com.challenge.overwatchapi.dto.HeroDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class HeroEntity {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String realName;
    private int health;
    private int armour;
    private int shield;

    @OneToMany
    private List<AbilityEntity> abilities = new ArrayList<>();

    public HeroEntity() {
    }

    public HeroEntity(String name, String realName, int health, int armour, int shield) {
        this.name = name;
        this.realName = realName;
        this.health = health;
        this.armour = armour;
        this.shield = shield;
    }

    public HeroDto toDto() {
        List<AbilityDto> abilityDtos = abilities.stream().map(AbilityEntity::toDto).collect(Collectors.toList());
        return new HeroDto(id, name, realName, health, armour, shield, abilityDtos);
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

    public List<AbilityEntity> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityEntity> abilities) {
        this.abilities = new ArrayList<>(abilities);
    }
}
