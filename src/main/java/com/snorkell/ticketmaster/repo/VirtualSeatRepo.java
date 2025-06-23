package com.snorkell.ticketmaster.repo;

import com.snorkell.ticketmaster.entity.VirtualSeatD;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VirtualSeatRepo extends JpaRepository<VirtualSeatD, Integer> {

    @Query(value = "select * from virtual_seat", nativeQuery = true)
    List<VirtualSeatD> findAllVirtualSeats(Pageable pageable);
}
