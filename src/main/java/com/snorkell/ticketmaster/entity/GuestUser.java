package com.snorkell.ticketmaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "guest_user")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class GuestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;
    @NonNull
    private String email;


    //one to many directional mapping
    @OneToMany(mappedBy = "userId")
    private List<BookingD> bookings;
}