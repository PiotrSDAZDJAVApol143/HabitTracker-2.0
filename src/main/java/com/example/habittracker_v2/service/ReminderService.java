package com.example.habittracker_v2.service;

import com.example.habittracker_v2.dto.ReminderDto;
import com.example.habittracker_v2.dto.ReminderReqDto;
import com.example.habittracker_v2.model.Activity;
import com.example.habittracker_v2.model.Goal;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Reminder;
import com.example.habittracker_v2.repository.GoalRepository;
import com.example.habittracker_v2.repository.HabitRepository;
import com.example.habittracker_v2.repository.ReminderRepository;
import com.example.habittracker_v2.utils.mapper.ReminderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;
    private final HabitRepository habitRepository;
    private final GoalRepository goalRepository;


    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public ReminderDto addReminder(ReminderReqDto request) {
        Habit habit = getHabitFromId(request.getHabitId());
        Goal goal = habit.getGoal();
        Reminder reminder = reminderMapper.toEntity(request, habit, goal);
        reminder = reminderRepository.save(reminder);
        return reminderMapper.toDto(reminder);
    }

    public ReminderDto getReminderById(Long id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reminder not found"));
        return reminderMapper.toDto(reminder);
    }


    public ReminderDto updateReminderById(Long id, ReminderReqDto request) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reminder not found"));
        reminder.setHabit(getHabitFromId(request.getHabitId()));
        reminder.setGoal(getGoalFromId(request.getGoalId()));
        reminderMapper.updateReminderFromDto(request, reminder);
        reminder = reminderRepository.save(reminder);
        return reminderMapper.toDto(reminder);
    }

    public void deleteReminderById(Long id) {
        if (!reminderRepository.existsById(id)) {
            throw new EntityNotFoundException("Reminder not found");
        }
        reminderRepository.deleteById(id);
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
}
