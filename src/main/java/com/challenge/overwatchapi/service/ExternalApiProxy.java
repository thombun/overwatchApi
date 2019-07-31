package com.challenge.overwatchapi.service;

import com.challenge.overwatchapi.dto.AbilityDto;
import com.challenge.overwatchapi.dto.HeroDto;
import com.challenge.overwatchapi.external.Ability;
import com.challenge.overwatchapi.external.AbilityList;
import com.challenge.overwatchapi.external.Hero;
import com.challenge.overwatchapi.external.HeroList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalApiProxy {

    private RestTemplate restTemplate = new RestTemplate();

    public List<HeroDto> getHeroList() {
        HeroList heroList = restTemplate.getForObject("https://overwatch-api.net/api/v1/hero/", HeroList.class);

        return heroList.getData().stream().map(Hero::toDto).collect(Collectors.toList());
    }

    public HeroDto getHeroById(long heroId) {
        return restTemplate.getForObject("https://overwatch-api.net/api/v1/hero/" + heroId, Hero.class).toDto();
    }

    public List<AbilityDto> getAbilityList() {
        return getAbilities().stream().map(Ability::toDto).collect(Collectors.toList());
    }

    public List<AbilityDto> getAbilityListForHero(long heroId) {
        return getAbilities().stream().filter(ability -> ability.getHero().getId() == heroId).map(Ability::toDto).collect(Collectors.toList());
    }

    public AbilityDto getAbilityById(long abilityId) {
        return restTemplate.getForObject("https://overwatch-api.net/api/v1/ability/" + abilityId, Ability.class).toDto();
    }


    private List<Ability> getAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();
        AbilityList abilityList  = new AbilityList();

        abilityList = restTemplate.getForObject("https://overwatch-api.net/api/v1/ability/", AbilityList.class);
        abilities.addAll(abilityList.getData());

        while(abilityList.hasNext()) {
            //seems there is a bug in the api, the link in the next property is not https
            abilityList = restTemplate.getForObject(abilityList.getNext().replace("http", "https"), AbilityList.class);
            abilities.addAll(abilityList.getData());
        }

        return abilities;
    }
}
