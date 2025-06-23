package com.snorkell.ticketmaster.service;

import com.snorkell.ticketmaster.entity.BookingD;
import com.snorkell.ticketmaster.entity.VirtualSeatD;
import com.snorkell.ticketmaster.mapper.EntityToDTOMapper;
import com.snorkell.ticketmaster.model.Booking;
import com.snorkell.ticketmaster.model.MovieShows;
import com.snorkell.ticketmaster.model.MovieTheaters;
import com.snorkell.ticketmaster.model.VirtualSeat;
import com.snorkell.ticketmaster.repo.BookingRepo;
import com.snorkell.ticketmaster.repo.VirtualSeatRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("SearchService")
public class SearchService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    VirtualSeatRepo  virtualSeatRepo;

    @Autowired
    BookingRepo bookingRepo;


    public List<MovieTheaters> getTheaterMovie(Integer movieId,Integer pageNumber,Integer pageSize) {
        StringBuilder query = new StringBuilder("select m.id, t.name");
        query.append(" from theater t");
        query.append(" join show s on t.id = s.theater_id");
        query.append(" join movie m on m.id = s.movie_id");

        List<Object> params = new ArrayList<>();

        if (movieId != null) {
            query.append(" where m.id = ?");
            params.add(movieId);
        }

        query.append(" order by t.name asc");
        params.add(pageSize);
        params.add(pageNumber*pageSize);
        query.append(" Limit ? OFFSET ?");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        for (int i = 0; i < params.size(); i++) {
            log.trace(nativeQuery.toString());
            nativeQuery.setParameter(i+1, params.get(i));
        }

        List<Object[]> resultList = nativeQuery.getResultList();

        List<MovieTheaters> movieTheatersList = resultList.stream().map(row -> new MovieTheaters((Integer) row[0], (String) row[1])).collect(Collectors.toList());

        return movieTheatersList;
    }

    public List<VirtualSeat> getSeats(Integer pageNumber,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("seat_no").descending());
        List<VirtualSeatD> virtualSeatDList = virtualSeatRepo.findAllVirtualSeats(pageable);
        return EntityToDTOMapper.INSTANCE.virtualSeatDListToVirtualSeatList(virtualSeatDList);
    }

    public List<Booking> getBookingByUser(Integer userId) {
        List<BookingD> bookingDList = bookingRepo.findBookingsOfUser(userId);
        return EntityToDTOMapper.INSTANCE.bookingDListToBookingList(bookingDList);
    }
}
