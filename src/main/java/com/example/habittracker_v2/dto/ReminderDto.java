package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.FrequencyUnit;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Habit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ReminderDto {
    private Long id;
    private String message;
    private LocalTime reminderTime;
    private Integer reminderFrequency;
    private FrequencyUnit frequencyUnit;
    private Habit habit;
    private Goal goal;
}
