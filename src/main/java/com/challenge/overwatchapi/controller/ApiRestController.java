package com.challenge.overwatchapi.controller;

import com.challenge.overwatchapi.dto.AbilityDto;
import com.challenge.overwatchapi.dto.HeroDto;
import com.challenge.overwatchapi.service.ExternalApiProxy;
import com.challenge.overwatchapi.service.BLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiRestController {

    @Autowired
    BLService bLService;

    @GetMapping(value = "/api/heros", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHeroList() {
        List<HeroDto> list = bLService.getHeroList();

        List<Resource<HeroDto>> resourceList = list.stream().map(HeroDto::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(new Resources<>(resourceList));
    }

    @GetMapping(value = "/api/heros/{heroId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHeroById(@PathVariable("heroId") long heroId) {
        HeroDto hero = bLService.getHeroById(heroId);
        return ResponseEntity.ok(hero.toResource());
    }

    @GetMapping(value = "/api/heros/{heroId}/abilities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAbilitiesForHero(@PathVariable("heroId") long heroId) {
        HeroDto hero = bLService.getHeroById(heroId);
        return ResponseEntity.ok(new Resources<>(hero.getAbilities()));
    }

    @GetMapping(value = "/api/abilities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAbilityList() {
        List<AbilityDto> list = bLService.getAbilityList();

        List<Resource<AbilityDto>> resourceList = list.stream().map(AbilityDto::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(new Resources<>(resourceList));
    }

    @GetMapping(value = "/api/abilities/{abilityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAbilityById(@PathVariable("abilityId") long abilityId) {
        AbilityDto ability = bLService.getAbilityById(abilityId);
        return ResponseEntity.ok(ability.toResource());
    }
}
