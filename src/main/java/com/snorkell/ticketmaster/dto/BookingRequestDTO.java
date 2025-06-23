package com.snorkell.ticketmaster.dto;


import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDTO {
    private final List<Integer> seatIds;
}
