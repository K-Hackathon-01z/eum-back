package com._z.eum.onedayClass.classSchedules.entity;

import com._z.eum.onedayClass.classes.entity.Classes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "class_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Classes classes;

    // 수업 날짜 (년월일)
    private LocalDate date;

    // 수업 시간 (시분)
    private LocalTime timeSlot;

    // 최대 수용 인원
    private int capacity;

    // 현재 예약된 인원
    private int currentCount;
}
