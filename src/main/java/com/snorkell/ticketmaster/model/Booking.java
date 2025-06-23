package com.snorkell.ticketmaster.model;

import lombok.Data;

import java.util.List;

@Data
public class Booking {

    private final int id;
    private final Show show;
    private final List<VirtualSeat> seats;
    private final Status status;
    private final String paymentId;
    private final int userId;
    private final double amount;

}
