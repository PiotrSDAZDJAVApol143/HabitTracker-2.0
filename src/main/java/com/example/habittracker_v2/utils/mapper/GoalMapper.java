package com.example.habittracker_v2.utils.mapper;

import com.example.habittracker_v2.dto.GoalDto;
import com.example.habittracker_v2.dto.GoalReqDto;
import com.example.habittracker_v2.model.Goal;

import com.example.habittracker_v2.model.Habit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoalMapper {




    public GoalDto toDto(Goal goal) {
        GoalDto dto = new GoalDto();
        dto.setId(dto.getId());
        dto.setGoalName(goal.getGoalName());
        dto.setDescription(goal.getDescription());
        dto.setCategory(goal.getCategory());
        dto.setStartDate(goal.getStartDate());
        dto.setEndDate(goal.getEndDate());
        dto.setHabits(goal.getHabits());
        dto.setReminders(goal.getReminders());

       // dto.setHabitIds(goal.getHabits().stream().map(Habit::getId).collect(Collectors.toList()));
       // dto.setReminderIds(goal.getReminders().stream().map(Reminder::getId).collect(Collectors.toList()));
        return dto;
    }

    public Goal toEntity(GoalReqDto dto) {
        Goal goal = new Goal();
        goal.setGoalName(dto.getGoalName());
        goal.setDescription(dto.getDescription());
        goal.setCategory(dto.getCategory());
        goal.setStartDate(dto.getStartDate());
        goal.setEndDate(dto.getEndDate());
        return goal;
    }
    public void updateGoalFromDto(GoalReqDto reqDto, Goal goal, List<Habit> habits){
        goal.setGoalName(reqDto.getGoalName());
        goal.setDescription(reqDto.getDescription());
        goal.setCategory(reqDto.getCategory());
        goal.setStartDate(reqDto.getStartDate());
        goal.setEndDate(reqDto.getEndDate());
        goal.setHabits(habits);
    }



}