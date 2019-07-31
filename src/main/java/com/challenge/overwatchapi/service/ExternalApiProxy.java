package com.challenge.overwatchapi.service;

import com.challenge.overwatchapi.external.Ability;
import com.challenge.overwatchapi.external.AbilityList;
import com.challenge.overwatchapi.external.Hero;
import com.challenge.overwatchapi.external.HeroList;
import com.challenge.overwatchapi.model.AbilityEntity;
import com.challenge.overwatchapi.model.HeroEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExternalApiProxy {

    private RestTemplate restTemplate = new RestTemplate();

    public List<HeroEntity> getHeroList() {
        HeroList heroList = restTemplate.getForObject("https://overwatch-api.net/api/v1/hero/", HeroList.class);

        return heroList.getData().stream().map(Hero::toEntity).collect(Collectors.toList());
    }

    /**
     * Maps lists of abilities to hero id
     * @return
     */
    public Map<Long, List<AbilityEntity>> getAbilityMap() {
        List<Ability> abilityEntities = getAbilities();

        HashMap<Long, List<AbilityEntity>> map = new HashMap<>();

        for(Ability ability : abilityEntities) {
            long heroId = ability.getHero().getId();

            if(!map.containsKey(heroId)) {
                map.put(heroId, new ArrayList<>());
            }

            map.get(heroId).add(ability.toEntity());
        }

        return map;
    }

    /**
     * Get all abilities provided by the external api
     * ignores paging
     */
    private List<Ability> getAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();
        AbilityList abilityList;

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
