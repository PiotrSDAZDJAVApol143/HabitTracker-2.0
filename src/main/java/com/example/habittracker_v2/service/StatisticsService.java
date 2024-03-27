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
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final StatisticsMapper statisticsMapper;
    private final GoalRepository goalRepository;
    private final HabitRepository habitRepository;

    /**
     * This method is used to add a new statistics.
     * @param request This is a request object which contains the details of the statistics to be added.
     * @return StatisticsDto This returns the added statistics.
     */
    public StatisticsDto addStatistics(StatisticsReqDto request) {
        Statistics statistics = statisticsMapper.toEntity(request);
        statistics.setGoal(getGoalFromId(request.getGoalId()));
        statistics.setHabit(getHabitFromId(request.getHabitId()));
        statistics.setGoalName(getGoalFromId(request.getGoalId()).getGoalName());
        statistics.setHabitName(getHabitFromId(request.getHabitId()).getHabitName());
        statistics.setProgress(calculateProgress(statistics));
        updateStatus(statistics); // aktualizacja statusu
        statistics = statisticsRepository.save(statistics);
        return statisticsMapper.toDto(statistics);
    }

    /**
     * This method is used to get a statistics by its id.
     * @param id This is the id of the statistics to be fetched.
     * @return StatisticsDto This returns the fetched statistics.
     */
    public StatisticsDto getStatistics(Long id) {
        Statistics statistics = statisticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statistics not found"));
        updateStatus(statistics); // aktualizacja statusu
        return statisticsMapper.toDto(statistics);
    }

    /**
     * This method is used to update an existing statistics.
     * @param id This is the id of the statistics to be updated.
     * @param request This is a request object which contains the updated details of the statistics.
     * @return StatisticsDto This returns the updated statistics.
     */
    public StatisticsDto updateStatistics(Long id, StatisticsReqDto request) {
        Statistics statistics = statisticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statistics not found"));
        statistics.setGoal(getGoalFromId(request.getGoalId()));
        statistics.setHabit(getHabitFromId(request.getHabitId()));
        statisticsMapper.updateStatisticsFromDto(request, statistics);
        statistics.setProgress(calculateProgress(statistics));
        updateStatus(statistics); // aktualizacja statusu
        statistics = statisticsRepository.save(statistics);
        return statisticsMapper.toDto(statistics);
    }

    /**
     * This method is used to delete a statistics by its id.
     * @param id This is the id of the statistics to be deleted.
     */
    public void deleteStatistics(Long id) {
        if (!statisticsRepository.existsById(id)) {
            throw new EntityNotFoundException("Statistics not found");
        }
        statisticsRepository.deleteById(id);
    }

    /**
     * This method is used to get a goal by its id.
     * @param goalId This is the id of the goal.
     * @return Goal This returns the fetched goal.
     */
    private Goal getGoalFromId(Long goalId) {
        if (goalId == null) {
            return null;
        }
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + goalId));
    }

    /**
     * This method is used to get a habit by its id.
     * @param habitId This is the id of the habit.
     * @return Habit This returns the fetched habit.
     */
    private Habit getHabitFromId(Long habitId) {
        if (habitId == null) {
            return null;
        }
        return habitRepository.findById(habitId)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found with id: " + habitId));
    }

    /**
     * This method is used to update the status of a statistics object.
     * @param statistics This is the statistics object to be updated.
     */
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

    /**
     * This method is used to calculate the progress of a statistics object.
     * @param statistics This is the statistics object for which the progress is calculated.
     * @return String This returns the progress as a string.
     */
    public String calculateProgress(Statistics statistics) {
        Goal goal = statistics.getGoal();
        Habit habit = statistics.getHabit();
        long totalDays = ChronoUnit.DAYS.between(goal.getStartDate(), goal.getEndDate());
        long totalActivities;
        switch (habit.getFrequencyUnit()) {
            case DAILY:
                totalActivities = totalDays * habit.getFrequency();
                break;
            case WEEKLY:
                totalActivities = (totalDays / 7) * habit.getFrequency();
                break;
            case MONTHLY:
                totalActivities = (totalDays / 30) * habit.getFrequency();
                break;
            default:
                throw new IllegalArgumentException("Wprowadz logikę w metodzie calculateProgress dla nowego enuma " + habit.getFrequencyUnit());
        }
        long currentActivities = habit.getActivities().size();
        return currentActivities + " / " + totalActivities;

}
}
