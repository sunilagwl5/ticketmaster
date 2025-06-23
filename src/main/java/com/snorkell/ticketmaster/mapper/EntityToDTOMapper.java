package com.snorkell.ticketmaster.mapper;

import com.snorkell.ticketmaster.entity.BookingD;
import com.snorkell.ticketmaster.entity.ShowD;
import com.snorkell.ticketmaster.entity.VirtualSeatD;
import com.snorkell.ticketmaster.model.Booking;
import com.snorkell.ticketmaster.model.Show;
import com.snorkell.ticketmaster.model.VirtualSeat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityToDTOMapper {
    EntityToDTOMapper INSTANCE = Mappers.getMapper(EntityToDTOMapper.class);

    @Mapping(target = "virtualSeats", source="virtualSeatDList")
    Show showDToShow(ShowD showD);

//    @Mapping(target = "userId", source = "userId", ignore = true, expression = "java(bookingD.getUserId().toString())")
    @Mapping(target = "userId", source = "userId", ignore = true)
    Booking bookingDTOBookingD(BookingD bookingD);

    List<Booking> bookingDListToBookingList(List<BookingD> bookingDList);

    List<VirtualSeat> virtualSeatDListToVirtualSeatList(List<VirtualSeatD> virtualSeatDList);
}
