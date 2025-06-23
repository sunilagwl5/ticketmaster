package com.snorkell.ticketmaster.controller;

import com.snorkell.ticketmaster.dto.BookingRequestDTO;
import com.snorkell.ticketmaster.dto.SResponse;
import com.snorkell.ticketmaster.model.*;
import com.snorkell.ticketmaster.repo.MovieRepo;
import com.snorkell.ticketmaster.service.BookingServiceDImpl;
import com.snorkell.ticketmaster.service.SearchService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(path = "/v1/lld/bms")
//@ConditionalOnProperty(prefix = "lld", name = "design", havingValue = "bookmyshow", matchIfMissing = false)
public class BMSController {

    private final BookingServiceDImpl bookingServiceD;
    private final SearchService searchService;

    @Autowired
    public BMSController(BookingServiceDImpl bookingServiceD,SearchService searchService) {
        this.bookingServiceD = bookingServiceD;
        this.searchService = searchService;
    }


    @GetMapping(path = "/shows/{showId}")
    public ResponseEntity<SResponse> getPullBasedObserver(@PathVariable("showId") int id) {
        Show show = this.bookingServiceD.getShow(id);
        SResponse response = SResponse.builder().data(show).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping(path = "/shows/{showId}")
    public ResponseEntity<SResponse> updateShow(@RequestHeader("userId") Integer userId,@PathVariable("showId") int showId, @RequestBody BookingRequestDTO bookingRequestDTO) throws Exception {
        //Validation
        log.trace("booking Started");
        Booking booking= bookingServiceD.bookShow(showId, bookingRequestDTO, userId);
        SResponse response = SResponse.builder().data(booking).build();
        log.trace("booking Completed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/movies/{movieId}")
    public ResponseEntity<SResponse> getMovieShows(@PathVariable("movieId") int movieId) {
        List<MovieShows> movieShows= bookingServiceD.getShowByMovie(movieId);
        SResponse response = SResponse.builder().data(movieShows).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/movies/{movieId}/theaters")
    public ResponseEntity<SResponse> getMovieTheaters(@PathVariable("movieId") int movieId,@PathParam("pageNumber") Integer pageNumber,@PathParam("pageSize") Integer pageSize) {
        List<MovieTheaters> movieTheaters= searchService.getTheaterMovie(movieId,pageNumber,pageSize);
        SResponse response = SResponse.builder().data(movieTheaters).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/seats")
    public ResponseEntity<SResponse> getMovieTheaters(@PathParam("pageNumber") Integer pageNumber,@PathParam("pageSize") Integer pageSize) {
        List<VirtualSeat> virtualSeatList= searchService.getSeats(pageNumber,pageSize);
        SResponse response = SResponse.builder().data(virtualSeatList).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/booking")
    public ResponseEntity<SResponse> getMovieTheaters(@RequestHeader("userId") Integer userId) {
        List<Booking> bookingList= searchService.getBookingByUser(userId);
        SResponse response = SResponse.builder().data(bookingList).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
