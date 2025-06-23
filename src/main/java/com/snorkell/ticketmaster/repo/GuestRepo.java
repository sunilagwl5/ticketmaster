package com.snorkell.ticketmaster.repo;

import com.snorkell.ticketmaster.entity.GuestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepo extends JpaRepository<GuestUser, Integer> {
}
