package com.snorkell.ticketmaster.model;

import lombok.Data;

@Data
public class Movie {
    private final int id;
    private final String name;
    private final String description;
}
