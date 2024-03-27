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

    /**
     * This method is used to add a new activity.
     * @param request This is a request object which contains the details of the activity to be added.
     * @return ActivityDto This returns the added activity.
     */
    public ActivityDto addActivity(ActivityReqDto request) {
        Habit habit = getHabitFromId(request.getHabitId());
        Statistics statistics = getStatisticsFromHabitAndGoal(request.getHabitId());
        Activity activity = activityMapper.toEntity(request, habit, statistics);
        activity = activityRepository.save(activity);
        return activityMapper.toDto(activity);
    }

    /**
     * This method is used to get statistics associated with a habit.
     * @param habitId This is the id of the habit.
     * @return Statistics This returns the statistics associated with the habit.
     */
    private Statistics getStatisticsFromHabitAndGoal(Long habitId) {
        return statisticsRepository.findByHabit_Id(habitId)
                .orElseThrow(() -> new EntityNotFoundException("Statistics not found with habitId: " + habitId ));
    }

    /**
     * This method is used to get an activity by its id.
     * @param id This is the id of the activity to be fetched.
     * @return ActivityDto This returns the fetched activity.
     */
    public ActivityDto getActivity(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found"));
        return activityMapper.toDto(activity);
    }

    /**
     * This method is used to update an existing activity.
     * @param id This is the id of the activity to be updated.
     * @param activityDto This is a request object which contains the updated details of the activity.
     * @return ActivityDto This returns the updated activity.
     */
    public ActivityDto updateActivity(Long id, ActivityReqDto activityDto) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found"));
        activityMapper.updateFromDto(activityDto, activity);
        activity = activityRepository.save(activity);
        return activityMapper.toDto(activity);
    }

    /**
     * This method is used to delete an activity by its id.
     * @param id This is the id of the activity to be deleted.
     */
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new EntityNotFoundException("Activity not found");
        }
        activityRepository.deleteById(id);
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
}
