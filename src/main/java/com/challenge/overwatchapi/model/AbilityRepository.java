package com.challenge.overwatchapi.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends CrudRepository<AbilityEntity, Long> {
}
