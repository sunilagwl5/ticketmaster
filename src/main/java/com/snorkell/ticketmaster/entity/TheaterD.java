package com.snorkell.ticketmaster.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theater")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TheaterD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String name;
    private  String description;

    //Many to one bidirectional relationship with Theater
    @OneToMany(mappedBy = "theater")
    private List<ShowD> showDS;

    @OneToMany(mappedBy = "theater")
    private List<ScreenD> screenDS;

    private  String city;

    public TheaterD(String name, String description, String city) {
        this.name = name;
        this.description = description;
        this.city = city;
    }
}
