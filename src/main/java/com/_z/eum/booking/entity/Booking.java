package com._z.eum.booking.entity;

import com._z.eum.onedayClass.classSchedules.entity.ClassSchedule;
import com._z.eum.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 일정 FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private ClassSchedule schedule;

    // 사용자 FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 예약 날짜 (년/월/일)
    private LocalDate date;

    // 예약 시간 (시/분)
    private LocalTime timeSlot;

    // 예약 상태
    @Enumerated(EnumType.STRING)
    private BookingState state;
}
