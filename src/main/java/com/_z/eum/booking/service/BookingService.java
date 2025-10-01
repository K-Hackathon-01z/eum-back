package com._z.eum.booking.service;

import com._z.eum.booking.dto.request.BookingRequest;
import com._z.eum.booking.dto.response.BookingResponse;
import com._z.eum.booking.entity.Booking;
import com._z.eum.booking.entity.BookingState;
import com._z.eum.booking.repository.BookingRepository;
import com._z.eum.onedayClass.classSchedules.entity.ClassSchedule;
import com._z.eum.onedayClass.classSchedules.repository.ClassScheduleRepository;
import com._z.eum.user.entity.User;
import com._z.eum.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClassScheduleRepository classScheduleRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          ClassScheduleRepository classScheduleRepository,
                          UserRepository userRepository){
        this.bookingRepository = bookingRepository;
        this.classScheduleRepository = classScheduleRepository;
        this.userRepository = userRepository;
    }


    //전체 에약 조회
    public List<BookingResponse> getAllBookings(){
        return bookingRepository.findAll().stream().map(this:: toResponse).toList();
    }



    //단일 예약 조회



    //예약 생성
    public BookingResponse createBooking(BookingRequest bookingRequest){

        //일정 존재 예외처리
        ClassSchedule classSchedule = classScheduleRepository.findById(bookingRequest.scheduleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 일정의 클래스가 존재하지 않습니다."));

        //사용자 존재 예외처리
        User user = userRepository.findById(bookingRequest.userId())
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 사용자가 존재하지 않습니다."));

        // 중복 예외처리

        // 예약  정원 초과 방지


        //예약 생성
        Booking booking = Booking.builder()
                .schedule(classSchedule)
                .user(user)
                .date(classSchedule.getDate())
                .timeSlot(classSchedule.getTimeSlot())
                .state(BookingState.RESERVED)
                .build();

        // 예약된 인원 증가
        classSchedule.setCurrentCount(classSchedule.getCurrentCount() + 1);

        return toResponse(bookingRepository.save(booking));

    }



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
