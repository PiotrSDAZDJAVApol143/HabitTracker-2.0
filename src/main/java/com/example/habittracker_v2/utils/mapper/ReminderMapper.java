package com.example.habittracker_v2.utils.mapper;

import com.example.habittracker_v2.dto.ReminderDto;
import com.example.habittracker_v2.dto.ReminderReqDto;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Reminder;
import org.springframework.stereotype.Component;

@Component
public class ReminderMapper {
    public ReminderDto toDto(Reminder reminder){
        ReminderDto regDto = new ReminderDto();
        regDto.setMessage(reminder.getMessage());
        regDto.setReminderTime(reminder.getReminderTime());
        regDto.setReminderFrequency(reminder.getReminderFrequency());
        regDto.setFrequencyUnit(reminder.getFrequencyUnit());
        regDto.setHabit(reminder.getHabit());
        regDto.setGoal(reminder.getGoal());
        return regDto;

    }
    public Reminder toEntity(ReminderReqDto dto, Habit habit, Goal goal){
        Reminder reminder = new Reminder();
        reminder.setMessage(dto.getMessage());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setReminderFrequency(dto.getReminderFrequency());
        reminder.setFrequencyUnit(dto.getFrequencyUnit());
        reminder.setHabit(habit);
        reminder.setGoal(goal);
        return reminder;
    }


    public void updateReminderFromDto(ReminderReqDto request, Reminder reminder) {
        reminder.setMessage(request.getMessage());
        reminder.setReminderFrequency(request.getReminderFrequency());
        reminder.setFrequencyUnit(request.getFrequencyUnit());
        reminder.setReminderTime(request.getReminderTime());
    }
}
