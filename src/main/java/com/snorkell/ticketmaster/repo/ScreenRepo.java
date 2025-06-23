package com.snorkell.ticketmaster.repo;

import com.snorkell.ticketmaster.entity.ScreenD;
import com.snorkell.ticketmaster.entity.TheaterD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepo extends JpaRepository<ScreenD, Integer> {
    //
//    @Query("SELECT t FROM TheaterD t LEFT JOIN FETCH t.screenDS WHERE t.id = :id")
    List<ScreenD> findAllByTheater(TheaterD theater);


//    @EntityGraph(value = "screen.theater", type = EntityGraph.EntityGraphType.FETCH)
//    Optional<ScreenD> findByTheater(Integer id);

//    default ScreenD findByNameAndTheater(String name, TheaterD theater) {
//
//    }

}
