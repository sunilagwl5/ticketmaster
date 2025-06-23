package com.snorkell.ticketmaster.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Show {
    private final Integer id;
    private final Movie movie;
    private final Theater theater;
    private final Screen screen;
    private final List<VirtualSeat> virtualSeats;
    private final String startTime;
    private final String endTime;
}
