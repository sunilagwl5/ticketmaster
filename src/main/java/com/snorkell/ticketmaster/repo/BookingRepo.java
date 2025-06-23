package com.snorkell.ticketmaster.repo;

import com.snorkell.ticketmaster.entity.BookingD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepo extends JpaRepository<BookingD, Integer> {

    @Query(value = "select * from booking where user_id=:userId", nativeQuery = true)
    List<BookingD> findBookingsOfUser(@Param("userId") int userId);
}
