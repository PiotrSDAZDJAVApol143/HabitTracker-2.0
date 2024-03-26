package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Statistics;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ActivityDto {
    private Long id;
    private String activityName;
    private LocalTime timeOfActivity;
    private LocalDate dateOfActivity;
    private Integer duration;
    private Habit habit;
    private Statistics statistics;
}
