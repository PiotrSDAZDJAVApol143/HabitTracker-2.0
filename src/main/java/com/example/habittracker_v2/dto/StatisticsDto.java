package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.Activity;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatisticsDto {
    private Long id;
    private Goal goal;
    private String goalName;
    private Habit habit;
    private String habitName;
    private List<Activity> activities;
    private Status status;


}
