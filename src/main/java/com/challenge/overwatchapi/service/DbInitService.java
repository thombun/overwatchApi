package com.challenge.overwatchapi.service;

import com.challenge.overwatchapi.model.AbilityEntity;
import com.challenge.overwatchapi.model.AbilityRepository;
import com.challenge.overwatchapi.model.HeroEntity;
import com.challenge.overwatchapi.model.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class DbInitService {

    @Autowired
    ExternalApiProxy externalApiProxy;

    @Autowired
    AbilityRepository abilityRepository;

    @Autowired
    HeroRepository heroRepository;

    @PostConstruct
    public void initDB() {
        List<HeroEntity> heroEntities = externalApiProxy.getHeroList();
        Map<Long, List<AbilityEntity>> abilityMap = externalApiProxy.getAbilityMap();

        //persist all heroes
        heroEntities.forEach(hero -> heroRepository.save(hero));

        //persist all abilities
        for (HeroEntity heroEntity : heroEntities) {
            for (AbilityEntity abilityEntity : abilityMap.get(heroEntity.getId())) {
                abilityRepository.save(abilityEntity);
            }

            heroEntity.setAbilities(abilityMap.get(heroEntity.getId()));
            heroRepository.save(heroEntity);
        }
    }
}
