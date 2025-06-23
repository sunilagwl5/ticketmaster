package com.snorkell.ticketmaster.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "show")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ShowD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Many to one unidirectional relationship with Movie
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @NonNull
    private MovieD movie;

    //Many to one bidirectional relationship with Theater
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    @NonNull
    private TheaterD theater;

    @OneToMany(mappedBy = "show")
    private List<SeatTypeRateD> seatTypeRateD;

    //Many to one unidirectional relationship with Screen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", referencedColumnName = "id")
    @NonNull
    private ScreenD screen;

    @OneToMany(mappedBy = "show")
    private List<VirtualSeatD> virtualSeatDList;

    @Column(name = "start_time")
    @NonNull
    private String startTime;

    @Column(name = "end_time")
    @NonNull
    private String endTime;
}
