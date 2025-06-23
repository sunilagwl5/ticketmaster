package com.snorkell.ticketmaster.model;

import lombok.Data;

@Data
public class VirtualSeat {
    private final int id;
    private final int seatNo;
    private final Status status;

}



