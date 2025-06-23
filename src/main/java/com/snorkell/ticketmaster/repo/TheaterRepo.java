package com.snorkell.ticketmaster.repo;

import com.snorkell.ticketmaster.entity.TheaterD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepo extends JpaRepository<TheaterD, Integer> {
}
