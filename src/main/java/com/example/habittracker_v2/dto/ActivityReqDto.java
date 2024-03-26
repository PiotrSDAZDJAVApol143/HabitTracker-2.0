package com.example.habittracker_v2.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class ActivityReqDto {
    private String activityName;
    private LocalTime timeOfActivity;
    private LocalDate dateOfActivity;
    private Integer duration;
    private Long habitId;
    private Long statisticsId;
}
