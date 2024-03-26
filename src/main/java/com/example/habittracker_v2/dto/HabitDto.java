package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.Activity;
import com.example.habittracker_v2.model.FrequencyUnit;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Reminder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HabitDto {
    private Long id;
    private String habitName;
    private String description;
    private Integer frequency;
    private FrequencyUnit frequencyUnit;
    private Goal goal;
    private List<Activity> activities;
    private List<Reminder> reminders;


}
