package se325.assignment01.concert.service.mapper;

import se325.assignment01.concert.common.dto.BookingDTO;
import se325.assignment01.concert.common.dto.ConcertDTO;
import se325.assignment01.concert.service.domain.Booking;
import se325.assignment01.concert.service.domain.Concert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {

//    static Booking toDomainModel(BookingDTO dtoBooking) {
//        Booking fullBooking = new Booking();
//        return fullBooking;
//    }

    public static BookingDTO toDTO(Booking booking){
        return new BookingDTO(booking.getConcertId(), booking.getDate(),
                booking.getSeats().stream().map(s -> SeatMapper.toDTO(s)).collect(Collectors.toList()));
    }

    public static List<BookingDTO> listToDTO(List<Booking> bookingList) {
        ArrayList<BookingDTO> result = new ArrayList<>();

        for (Booking b: bookingList) {
            result.add(BookingMapper.toDTO(b));
        }

        return result;
    }

}