package com.challenge.overwatchapi.dto;

import com.challenge.overwatchapi.controller.ApiRestController;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AbilityDto {

    private long id;
    private String name;
    private String description;
    @JsonProperty("is_ultimate")
    private boolean isUltimate;

    public AbilityDto(long id, String name, String description, boolean isUltimate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isUltimate = isUltimate;
    }

    public Resource<AbilityDto> toResource() {
        Link selfLink = linkTo((methodOn(ApiRestController.class).getAbilityById(this.id))).withSelfRel();
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
