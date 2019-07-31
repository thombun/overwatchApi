package com.challenge.overwatchapi.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeroList {

    private List<Hero> data;

    public List<Hero> getData() {
        return data;
    }

    public void setData(List<Hero> data) {
        this.data = data;
    }
}
