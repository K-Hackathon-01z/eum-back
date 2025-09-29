package com._z.eum.booking.controller;


import com._z.eum.booking.dto.response.BookingResponse;
import com._z.eum.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@Tag(name = "원데이 클래스 예약 API", description = "")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }


    //전체 에약 조회
    @GetMapping("/all")
    @Operation(summary = "전체 예약 조회", description = "저장된 모든 예약을 조회")
    public ResponseEntity<List<BookingResponse>> getAllBooking(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }



    //단일 예약 조회




    //예약 생성






    //예약 취소








}
