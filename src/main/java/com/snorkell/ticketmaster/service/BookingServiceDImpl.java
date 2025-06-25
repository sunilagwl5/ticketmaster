package com.snorkell.ticketmaster.service;

import com.snorkell.ticketmaster.dto.BookingRequestDTO;
import com.snorkell.ticketmaster.entity.BookingD;
import com.snorkell.ticketmaster.entity.GuestUser;
import com.snorkell.ticketmaster.entity.ShowD;
import com.snorkell.ticketmaster.entity.VirtualSeatD;
//import com.snorkell.ticketmaster.mapper.DTOToEntityMapper;
import com.snorkell.ticketmaster.exceptions.CustomException;
import com.snorkell.ticketmaster.mapper.EntityToDTOMapper;
import com.snorkell.ticketmaster.model.Booking;
import com.snorkell.ticketmaster.model.MovieShows;
import com.snorkell.ticketmaster.model.Show;
import com.snorkell.ticketmaster.model.Status;
import com.snorkell.ticketmaster.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("BMSBookingServiceDImpl")
//@ConditionalOnProperty(prefix = "lld", name = "design", havingValue = "bookmyshow", matchIfMissing = false)
public class BookingServiceDImpl implements BookingService {
//    private Map<Show, Map<Integer, VirtualSeat>> showSeatMap = new ConcurrentHashMap<>();
    private final MovieRepo movieRepo;
    private final ShowRepo showRepo;
    private final GuestRepo guestRepo;
    private final BookingRepo bookingRepo;
    private final VirtualSeatRepo virtualSeatRepo;
    private final AdminDService adminDService;
//    private final EntityToDTOMapper entityToDTOMapper;
//    private final EntityToDTOMapper entityToDTOMapper;
//    private final DTOToEntityMapper dtoToEntityMapper;

    @Autowired
    public BookingServiceDImpl(MovieRepo movieRepo,ShowRepo showRepo, GuestRepo guestRepo, BookingRepo bookingRepo, VirtualSeatRepo virtualSeatRepo, AdminDService adminDService) {
        this.movieRepo = movieRepo;
        this.showRepo = showRepo;
        this.guestRepo = guestRepo;
        this.bookingRepo = bookingRepo;
        this.virtualSeatRepo = virtualSeatRepo;
        this.adminDService = adminDService;
//        this.entityToDTOMapper = EntityToDTOMapper.INSTANCE;
//        this.dtoToEntityMapper = DTOToEntityMapper.INSTANCE;
//        this.entityToDTOMapper = entityToDTOMapper;
    }

    public List<BookingD> book(ShowD showD, GuestUser guestUser) throws Exception {

        log.trace("booking Started");
        List<BookingD> bookingDList = new ArrayList<>();
//        bookingDList.add(bookShow(showD.getId(), List.of(1, 2, 3), 1));
//        bookingDList.add(bookShow(showD.getId(), List.of(24, 25, 26), 1));
//        bookingDList.add(bookShow(showD.getId(), List.of(14, 15, 16), 1));
        return bookingDList;
    }

    public Booking bookShow(Integer showID, BookingRequestDTO bookingRequestDTO, Integer userId) throws Exception {

        Optional<GuestUser> user = guestRepo.findById(userId);
        if (user.isEmpty()){
            throw new CustomException(HttpStatus.BAD_REQUEST, "User not found");
        }

        // Book the show
        Optional<ShowD> showD = showRepo.findById(showID);
        if (showD.isEmpty()){
            throw new CustomException(HttpStatus.BAD_REQUEST, "show not found");
        }



        List<VirtualSeatD> eligibleSeats = showD.get().getVirtualSeatDList().stream().filter(seat -> bookingRequestDTO.getSeatIds().contains(seat.getSeatNo())).toList();

        log.trace("eligibleSeats {}", eligibleSeats.size());

        BookingD bookingD = new BookingD(showD.get(), user.get());
        if (!eligibleSeats.stream().allMatch(seat -> seat.getStatus() == Status.AVAILABLE)) {
            log.trace("Some of the seats are not available");
            throw new CustomException(HttpStatus.CONFLICT, "Seats are not available");
        }

        double total = eligibleSeats.stream().map(VirtualSeatD::getPrice).reduce(0.0, Double::sum);
        bookingD.setPaymentId("123");
        bookingD.setAmount(total);

        for (VirtualSeatD seat : eligibleSeats) {
            seat.setStatus(Status.BOOKED);
        }
        bookingD.setStatus(Status.BOOKED);
        virtualSeatRepo.saveAll(eligibleSeats);
        bookingRepo.save(bookingD);
        return EntityToDTOMapper.INSTANCE.bookingDTOBookingD(bookingD);

    }

    @Override
    public void book() {

    }

    @Override
    public void goLive(ShowD show) {
        // Go live with the show
//        showSeatMap.putIfAbsent(show, show.getVirtualSeats());
//        log.trace("showSeatMap {}", showSeatMap);
    }


    public Show getShow(Integer id) {
        this.adminDService.init();
//        guestRepo.save(guestUser);
//        log.trace("ticket master user{}", guestUser);
        ShowD showD = showRepo.findById(id).get();
        return EntityToDTOMapper.INSTANCE.showDToShow(showD);
    }

    public List<MovieShows> getShowByMovie(Integer movieId) {
        List<MovieShows> movieShows = movieRepo.getMovieShows(movieId);
        return movieShows;
    }
}

