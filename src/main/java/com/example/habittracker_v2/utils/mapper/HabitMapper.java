package com.example.habittracker_v2.utils.mapper;
import com.example.habittracker_v2.dto.HabitDto;
import com.example.habittracker_v2.dto.HabitReqDto;
import com.example.habittracker_v2.model.Habit;
import org.springframework.stereotype.Component;



@Component
public class HabitMapper {
    public HabitDto toDto(Habit habit) {
        HabitDto dto = new HabitDto();
        dto.setHabitName(habit.getHabitName());
        dto.setDescription(habit.getDescription());
        dto.setFrequency(habit.getFrequency());
        dto.setFrequencyUnit(habit.getFrequencyUnit());
        dto.setGoal(habit.getGoal());
        dto.setActivities(habit.getActivities());
        dto.setReminders(habit.getReminders());
        return dto;
    }
    public Habit toEntity(HabitReqDto dto) {
        Habit habit = new Habit();
        habit.setHabitName(dto.getHabitName());
        habit.setDescription(dto.getDescription());
        habit.setFrequency(dto.getFrequency());
        habit.setFrequencyUnit(dto.getFrequencyUnit());
        return habit;
    }

    public void updateHabitFromDto(HabitReqDto habitReqDto, Habit habit){
        habit.setHabitName(habitReqDto.getHabitName());
        habit.setDescription(habitReqDto.getDescription());
        habit.setFrequency(habitReqDto.getFrequency());
        habit.setFrequencyUnit(habitReqDto.getFrequencyUnit());
    }
}