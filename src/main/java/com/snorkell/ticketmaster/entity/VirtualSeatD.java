package com.snorkell.ticketmaster.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.snorkell.ticketmaster.model.SeatType;
import com.snorkell.ticketmaster.model.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "virtual_seat")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VirtualSeatD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "seat_no")
    @NonNull
    private Integer seatNo;

    @Column(name = "seat_type")
    @NonNull
    private SeatType seatType;

    @NonNull
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", referencedColumnName = "id")
    @NonNull
    private ShowD show;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private BookingD booking;

    //concurrency handling needed for this field
    private Status status = Status.AVAILABLE;

}



