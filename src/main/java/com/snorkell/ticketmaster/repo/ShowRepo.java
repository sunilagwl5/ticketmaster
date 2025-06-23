package com.snorkell.ticketmaster.repo;

import com.snorkell.ticketmaster.entity.ShowD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepo extends JpaRepository<ShowD, Integer> {
}
