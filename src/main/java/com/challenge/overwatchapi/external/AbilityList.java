package com.challenge.overwatchapi.external;

import java.util.List;

public class AbilityList {

    private List<Ability> data;
    private String next;

    public List<Ability> getData() {
        return data;
    }

    public void setData(List<Ability> data) {
        this.data = data;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }
}
