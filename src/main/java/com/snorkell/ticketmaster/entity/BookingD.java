package com.snorkell.ticketmaster.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.snorkell.ticketmaster.model.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BookingD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", referencedColumnName = "id")
    @NonNull
    private ShowD show;

    @OneToMany(mappedBy = "booking")
    private List<VirtualSeatD> seats;

    @Setter
    private Status status = Status.PENDING;

    @Column(name = "payment_id")
    @Setter
    private String paymentId;

    //one to many directional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NonNull
    private GuestUser userId;

//    private final String bookingTime;
//    private final String paymentTime;
    @Setter
    private double amount;

}
