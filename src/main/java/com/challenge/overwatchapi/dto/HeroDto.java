package com.challenge.overwatchapi.dto;

import com.challenge.overwatchapi.controller.ApiRestController;
import com.challenge.overwatchapi.external.Ability;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class HeroDto {

    private long id;
    private String name;
    @JsonProperty("real_name")
    private String realName;
    private int health;
    private int armour;
    private int shield;

    private Resources<Resource<AbilityDto>>  abilities;

    public HeroDto(long id, String name, String realName, int health, int armour, int shield, List<AbilityDto> abilityDtos) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.health = health;
        this.armour = armour;
        this.shield = shield;
        List<Resource<AbilityDto>> resourceList = abilityDtos.stream().map(AbilityDto::toResource).collect(Collectors.toList());
        this.abilities = new Resources<>(resourceList);
    }

    public Resource<HeroDto> toResource() {
        Link selfLink = linkTo((methodOn(ApiRestController.class).getHeroById(this.id))).withSelfRel();
        return new Resource<>(this, selfLink);
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

    public Resources<Resource<AbilityDto>> getAbilities() {
        return abilities;
    }

    public void setAbilities(Resources<Resource<AbilityDto>> abilities) {
        this.abilities = abilities;
    }

    public void setAbilities(List<AbilityDto> list) {
        List<Resource<AbilityDto>> resourceList = list.stream().map(AbilityDto::toResource).collect(Collectors.toList());
        this.abilities = new Resources<>(resourceList);
    }
}
