package com.snorkell.ticketmaster.service;

import com.snorkell.ticketmaster.entity.*;
import com.snorkell.ticketmaster.model.SeatType;
import com.snorkell.ticketmaster.repo.*;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("BMSAdminDService")
//@ConditionalOnProperty(prefix = "lld", name = "design", havingValue = "bookmyshow", matchIfMissing = false)
public class AdminDService {

    private final GuestRepo guestRepo;
    private final MovieRepo movieRepo;
    private final TheaterRepo theaterRepo;
    private final ScreenRepo screenRepo;
    private final ShowRepo showRepo;
    private final VirtualSeatRepo virtualSeatRepo;


    @Autowired
    public AdminDService(GuestRepo guestRepo, MovieRepo movieRepo, TheaterRepo theaterRepo, ScreenRepo screenRepo,
                         ShowRepo showRepo, VirtualSeatRepo virtualSeatRepo) {
        this.guestRepo = guestRepo;
        this.movieRepo = movieRepo;
        this.theaterRepo = theaterRepo;
        this.screenRepo = screenRepo;
        this.showRepo = showRepo;

        this.virtualSeatRepo = virtualSeatRepo;

    }

//    @PostConstruct
    public void init() {
        createUser();
        addMovie();
        log.trace("movies {}", movieRepo.findAll());
        addTheater();
        addShow();
//        this.bmsBookingService.book();
    }

    public void createUser(){
        GuestUser guestUser = new GuestUser("guest", "guest@gmail.com");
        guestRepo.save(guestUser);
    }

    //    @Transactional
    public void addTheater() {

        String city = "qwerty";

        TheaterD theater1 = new TheaterD("AA", "A Description", city);
        theaterRepo.save(theater1);
        List<ScreenD> screens = List.of(
                new ScreenD(null, "AASA", 30, theater1),
                new ScreenD(null, "AASB", 60, theater1),
                new ScreenD(null, "AASC", 90, theater1)
        );
        screenRepo.saveAll(screens);

        TheaterD theater2 = new TheaterD("BB", "A Description", city);
        theaterRepo.save(theater2);
        List<ScreenD> screens2 = List.of(
                new ScreenD(null, "BBSA", 90, theater2),
                new ScreenD(null, "BBSB", 60, theater2),
                new ScreenD(null, "BBSC", 30, theater2)
        );
        screenRepo.saveAll(screens2);
    }

    public void addMovie() {
        movieRepo.save(new MovieD("A", "abc"));
        movieRepo.save(new MovieD("B", "def"));
    }


    private void addVirtualSeat() {
        // Dummy data for VirtualSeatD
        if (showRepo.count() == 0) return; // Ensure at least one show exists
        ShowD show = showRepo.findAll().get(0);
        List<VirtualSeatD> virtualSeats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            virtualSeats.add(new VirtualSeatD(i, SeatType.SILVER, 100 + i * 10, show));
        }
        virtualSeatRepo.saveAll(virtualSeats);
        log.info("Dummy virtual seats added: {}", virtualSeats);
    }

    public void addShow() {
        log.trace("movieRepo {}", movieRepo.findAll());

        TheaterD theaterD = theaterRepo.findById(1).get();
        List<ScreenD> screenDS = screenRepo.findAllByTheater(theaterD);
        log.trace("screenDS {}", screenDS);

//        screenRepo.findBy
//        VirtualSeatD virtualSeatD = new VirtualSeatD()

        ShowD show1 = new ShowD(movieRepo.findById(1).get(), theaterD, screenDS.get(0), "10:00", "12:00");
        showRepo.save(show1);

        List<VirtualSeatD> virtualSeats = new ArrayList<>();
        for (int i = 0; i < show1.getScreen().getTotalSeats(); i++) {
            virtualSeats.add(new VirtualSeatD(i, SeatType.SILVER, 100, show1));
        }
        virtualSeatRepo.saveAll(virtualSeats);

//        log.trace("shows {}", showRepo.findById(1).get());
//        log.trace("shows virtualSeatDList{}", showRepo.findById(1).get().getVirtualSeatDList());

//        Show show4 = new Show(movies.get(1), theaters.get(1), theaters.get(1).getScreens().get(0), "10:00", "12:00");
//        show4.init();
//        theaterShows.computeIfAbsent(theaters.get(1), k -> new ArrayList<>()).add(show4);
//        bmsBookingService.goLive(show4);

    }
}
