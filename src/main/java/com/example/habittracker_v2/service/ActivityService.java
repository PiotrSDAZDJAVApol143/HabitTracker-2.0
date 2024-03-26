package com.example.habittracker_v2.service;

import com.example.habittracker_v2.dto.ActivityDto;
import com.example.habittracker_v2.dto.ActivityReqDto;
import com.example.habittracker_v2.model.Activity;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Statistics;
import com.example.habittracker_v2.repository.ActivityRepository;
import com.example.habittracker_v2.repository.HabitRepository;
import com.example.habittracker_v2.repository.StatisticsRepository;
import com.example.habittracker_v2.utils.mapper.ActivityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final HabitRepository habitRepository;
    private final StatisticsRepository statisticsRepository;

    public ActivityDto addActivity(ActivityReqDto request) {
        Habit habit = getHabitFromId(request.getHabitId());
        Statistics statistics = getStatisticsFromHabitAndGoal(request.getHabitId());
        Activity activity = activityMapper.toEntity(request, habit, statistics);
        activity = activityRepository.save(activity);
        return activityMapper.toDto(activity);
    }
    private Statistics getStatisticsFromHabitAndGoal(Long habitId) {
        return statisticsRepository.findByHabit_Id(habitId)
                .orElseThrow(() -> new EntityNotFoundException("Statistics not found with habitId: " + habitId ));
    }

    public ActivityDto getActivity(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found"));
        return activityMapper.toDto(activity);
    }

    public ActivityDto updateActivity(Long id, ActivityReqDto activityDto) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found"));
        activityMapper.updateFromDto(activityDto, activity);
        activity = activityRepository.save(activity);
        return activityMapper.toDto(activity);
    }

    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new EntityNotFoundException("Activity not found");
        }
        activityRepository.deleteById(id);
    }

    private Habit getHabitFromId(Long habitId) {
        if (habitId == null) {
            return null;
        }
        return habitRepository.findById(habitId)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found with id: " + habitId));
    }
}
