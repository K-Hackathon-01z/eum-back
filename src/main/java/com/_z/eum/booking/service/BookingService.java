package com._z.eum.booking.service;

import com._z.eum.booking.dto.response.BookingResponse;
import com._z.eum.booking.entity.Booking;
import com._z.eum.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }


    //전체 에약 조회
    public List<BookingResponse> getAllBookings(){
        return bookingRepository.findAll().stream().map(this:: toResponse).toList();
    }



    //단일 예약 조회




    //예약 생성




    //예약 취소


    private BookingResponse toResponse(Booking booking){
        return new BookingResponse(
                booking.getId(),
                booking.getSchedule().getId(),
                booking.getUser().getId(),
                booking.getDate(),
                booking.getTimeSlot(),
                booking.getState().name()
        );
    }







}
