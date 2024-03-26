package com.example.habittracker_v2.service;

import com.example.habittracker_v2.dto.GoalDto;
import com.example.habittracker_v2.dto.GoalReqDto;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.repository.GoalRepository;
import com.example.habittracker_v2.repository.HabitRepository;
import com.example.habittracker_v2.utils.mapper.GoalMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final HabitRepository habitRepository;

    public GoalDto addGoal(GoalReqDto request) {
        Goal goal = goalMapper.toEntity(request);
        goal = goalRepository.save(goal);
        return goalMapper.toDto(goal);
    }


    public GoalDto getGoal(Long id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));
        return goalMapper.toDto(goal);
    }

    public GoalDto updateGoal(Long id, GoalReqDto request) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));
        List<Habit> habits = convertIdsToHabits(request.getHabitIds());
        goalMapper.updateGoalFromDto(request, goal, habits);
        goal = goalRepository.save(goal);
        return goalMapper.toDto(goal);
    }

    private List<Habit> convertIdsToHabits(List<Long> habitIds) {
        if (habitIds == null) {
            return null;
        }
        List<Habit> habits = new ArrayList<>();
        for (Long id : habitIds) {
            Habit habit = habitRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Habit not found with id: " + id));
            habits.add(habit);
        }
        return habits;
    }

    public void deleteGoal(Long id) {
        if(!goalRepository.existsById(id)){
            throw new EntityNotFoundException("Goal not found");
        }
        goalRepository.deleteById(id);
    }
}
