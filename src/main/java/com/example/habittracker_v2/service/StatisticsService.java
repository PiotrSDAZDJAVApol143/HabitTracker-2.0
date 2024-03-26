package com.example.habittracker_v2.service;

import com.example.habittracker_v2.dto.StatisticsDto;
import com.example.habittracker_v2.dto.StatisticsReqDto;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Statistics;
import com.example.habittracker_v2.model.Status;
import com.example.habittracker_v2.repository.GoalRepository;
import com.example.habittracker_v2.repository.HabitRepository;
import com.example.habittracker_v2.repository.StatisticsRepository;
import com.example.habittracker_v2.utils.mapper.StatisticsMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final StatisticsMapper statisticsMapper;
    private final GoalRepository goalRepository;
    private final HabitRepository habitRepository;

    public StatisticsDto addStatistics(StatisticsReqDto request) {
        Statistics statistics = statisticsMapper.toEntity(request);
        statistics.setGoal(getGoalFromId(request.getGoalId()));
        statistics.setHabit(getHabitFromId(request.getHabitId()));
        updateStatus(statistics); // aktualizacja statusu
        statistics = statisticsRepository.save(statistics);
        return statisticsMapper.toDto(statistics);
    }

    public StatisticsDto getStatistics(Long id) {
        Statistics statistics = statisticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statistics not found"));
        updateStatus(statistics); // aktualizacja statusu
        return statisticsMapper.toDto(statistics);
    }

    public StatisticsDto updateStatistics(Long id, StatisticsReqDto request) {
        Statistics statistics = statisticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statistics not found"));
        statistics.setGoal(getGoalFromId(request.getGoalId()));
        statistics.setHabit(getHabitFromId(request.getHabitId()));
        statisticsMapper.updateStatisticsFromDto(request, statistics);
        updateStatus(statistics); // aktualizacja statusu
        statistics = statisticsRepository.save(statistics);
        return statisticsMapper.toDto(statistics);
    }

    public void deleteStatistics(Long id) {
        if (!statisticsRepository.existsById(id)) {
            throw new EntityNotFoundException("Statistics not found");
        }
        statisticsRepository.deleteById(id);
    }

    private Goal getGoalFromId(Long goalId) {
        if (goalId == null) {
            return null;
        }
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + goalId));
    }

    private Habit getHabitFromId(Long habitId) {
        if (habitId == null) {
            return null;
        }
        return habitRepository.findById(habitId)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found with id: " + habitId));
    }

    public void updateStatus(Statistics statistics) {
        if (statistics.getGoal() == null) {
            statistics.setStatus(Status.CANCELLED);
        } else if (LocalDate.now().isBefore(statistics.getGoal().getStartDate())) {
            statistics.setStatus(Status.PLANNED);
        } else if (LocalDate.now().isAfter(statistics.getGoal().getEndDate())) {
            statistics.setStatus(Status.COMPLETED);
        } else {
            statistics.setStatus(Status.IN_PROGRESS);
        }
        statisticsRepository.save(statistics);
    }
}
