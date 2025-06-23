package com.snorkell.ticketmaster.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "screen")
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Getter
@NamedEntityGraph(
        name = "screen.theater",
        attributeNodes = @NamedAttributeNode("theater")
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ScreenD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "total_seats")
    private Integer totalSeats;

    //Many to one unidirectional relationship with Theater
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    private TheaterD theater;
}
