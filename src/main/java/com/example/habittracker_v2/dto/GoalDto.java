package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.Category;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Reminder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GoalDto {
    private Long id;
    private String goalName;
    private String description;
    private Category category;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Habit> habits;
    private List<Reminder> reminders;

}
