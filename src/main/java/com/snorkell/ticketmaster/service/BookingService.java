package com.snorkell.ticketmaster.service;


import com.snorkell.ticketmaster.entity.ShowD;

public interface BookingService {
    void book();

    void goLive(ShowD show);
}
