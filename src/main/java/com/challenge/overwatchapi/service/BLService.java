package com.challenge.overwatchapi.service;

import com.challenge.overwatchapi.dto.AbilityDto;
import com.challenge.overwatchapi.dto.HeroDto;
import com.challenge.overwatchapi.exceptions.NotFoundException;
import com.challenge.overwatchapi.model.AbilityEntity;
import com.challenge.overwatchapi.model.AbilityRepository;
import com.challenge.overwatchapi.model.HeroEntity;
import com.challenge.overwatchapi.model.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BLService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private AbilityRepository abilityRepository;

    public List<HeroDto> getHeroList() {
        Iterable<HeroEntity> heroList = heroRepository.findAll();

        ArrayList<HeroDto> dtoList = new ArrayList<>();
        heroList.forEach(s -> dtoList.add(s.toDto()));

        return dtoList;
    }

    public HeroDto getHeroById(long heroId) {
        Optional<HeroEntity> hero = heroRepository.findById(heroId);

        if(hero.isPresent()) {
            return hero.get().toDto();
        } else {
            throw new NotFoundException("User with Id " + heroId + " does not exist");
        }
    }

    public List<AbilityDto> getAbilityList() {
        Iterable<AbilityEntity> abilityList = abilityRepository.findAll();

        ArrayList<AbilityDto> dtoList = new ArrayList<>();
        abilityList.forEach(a -> dtoList.add(a.toDto()));

        return dtoList;
    }

    public AbilityDto getAbilityById(long abilityId) {
        Optional<AbilityEntity> ability = abilityRepository.findById(abilityId);

        if(ability.isPresent()) {
            return ability.get().toDto();
        } else {
            throw new NotFoundException("User with Id " + abilityId + " does not exist");
        }
    }
}
