package com.challenge.overwatchapi.controller;

import com.challenge.overwatchapi.dto.AbilityDto;
import com.challenge.overwatchapi.dto.HeroDto;
import com.challenge.overwatchapi.service.ExternalApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiRestController {

    @Autowired
    ExternalApiProxy externalApiProxy;

    @GetMapping(value = "/api/heros")
    public ResponseEntity<?> getHeroList() {
        List<HeroDto> list = externalApiProxy.getHeroList();

        List<Resource<HeroDto>> resourceList = list.stream().map(HeroDto::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(new Resources<>(resourceList));
    }

    @GetMapping(value = "/api/heros/{heroId}")
    public ResponseEntity<?> getHeroById(@PathVariable("heroId") long heroId) {
        HeroDto hero = externalApiProxy.getHeroById(heroId);
        return ResponseEntity.ok(hero.toResource());
    }

    @GetMapping(value = "/api/heros/{heroId}/abilities")
    public ResponseEntity<?> getAbilitiesForHero(@PathVariable("heroId") long heroId) {
        List<AbilityDto> list = externalApiProxy.getAbilityListForHero(heroId);

        List<Resource<AbilityDto>> resourceList = list.stream().map(AbilityDto::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(new Resources<>(resourceList));
    }

    @GetMapping(value = "/api/abilities")
    public ResponseEntity<?> getAbilityList() {
        List<AbilityDto> list = externalApiProxy.getAbilityList();

        List<Resource<AbilityDto>> resourceList = list.stream().map(AbilityDto::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(new Resources<>(resourceList));
    }

    @GetMapping(value = "/api/abilities/{abilityId}")
    public ResponseEntity<?> getAbilityById(@PathVariable("abilityId") long abilityId) {
        AbilityDto ability = externalApiProxy.getAbilityById(abilityId);
        return ResponseEntity.ok(ability.toResource());
    }
}
