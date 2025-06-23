package com.snorkell.ticketmaster.entity;

import com.snorkell.ticketmaster.model.SeatType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "seat_type_rate")
@NoArgsConstructor
public class SeatTypeRateD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", referencedColumnName = "id")
    private ShowD show;

    @Column(name = "seat_type")
    private SeatType seatType;

    @Column(name = "seat_numbers")
    private List<Integer> seatNumbers;
    private double rate;
}