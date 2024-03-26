package com.example.habittracker_v2.service;

import com.example.habittracker_v2.dto.HabitDto;
import com.example.habittracker_v2.dto.HabitReqDto;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.repository.GoalRepository;
import com.example.habittracker_v2.repository.HabitRepository;
import com.example.habittracker_v2.utils.mapper.HabitMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository repository;
    private final HabitMapper mapper;
    private final GoalRepository goalRepository;
    public HabitDto addHabit(HabitReqDto request) {
        Habit habit = mapper.toEntity(request);
        habit.setGoal(getGoalFromId(request.getGoalId()));
        habit = repository.save(habit);
        return mapper.toDto(habit);
    }

    private Goal getGoalFromId(Long goalId) {
        if (goalId == null) {
            return null;
        }
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + goalId));
    }

    public HabitDto getHabit(Long id) {
        Habit habit = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Habit not found!"));
        return mapper.toDto(habit);
    }

    public HabitDto updateHabit(Long id, HabitReqDto request) {
        Habit habit = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Habit not found!"));
        habit.setGoal(convertGoalFromId(request.getGoalId()));
        mapper.updateHabitFromDto(request, habit);
        habit = repository.save(habit);
        return mapper.toDto(habit);
    }

    public void deleteHabit(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Habit not found!");
        }
        repository.deleteById(id);
    }
    private Goal convertGoalFromId(Long goalId) {
        if (goalId == null) {
            return null;
        }
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + goalId));
    }
}
