package com.snorkell.ticketmaster.model;

import lombok.Data;

import java.util.List;

@Data
public class Theater {
    private final int id;
    private final String name;
    private final String description;
    //one theater can have multiple screens
    private final List<Screen> screens;

    private final String city;
}
