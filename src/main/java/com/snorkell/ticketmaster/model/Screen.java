package com.snorkell.ticketmaster.model;

import lombok.Data;

import java.util.Map;

@Data
public class Screen {
    private final int id;
    private final String name;
    private final Map<SeatType, Integer> seats;
}
