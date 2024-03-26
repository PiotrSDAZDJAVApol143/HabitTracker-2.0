package com.example.habittracker_v2.utils.mapper;

import com.example.habittracker_v2.dto.ActivityDto;
import com.example.habittracker_v2.dto.ActivityReqDto;
import com.example.habittracker_v2.model.Activity;
import com.example.habittracker_v2.model.Habit;
import com.example.habittracker_v2.model.Statistics;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public ActivityDto toDto(Activity activity) {
        ActivityDto dto = new ActivityDto();
        dto.setId(activity.getId());
        dto.setActivityName(activity.getActivityName());
        dto.setTimeOfActivity(activity.getTimeOfActivity());
        dto.setDateOfActivity(activity.getDateOfActivity());
        dto.setDuration(activity.getDuration());
        dto.setHabit(activity.getHabit());
        dto.setStatistics(activity.getStatistics());
        return dto;
    }

    public Activity toEntity(ActivityReqDto dto, Habit habit, Statistics statistics) {
        Activity activity = new Activity();
        activity.setActivityName(dto.getActivityName());
        activity.setTimeOfActivity(dto.getTimeOfActivity());
        activity.setDateOfActivity(dto.getDateOfActivity());
        activity.setDuration(dto.getDuration());
        activity.setHabit(habit);
        activity.setStatistics(statistics);
        return activity;
    }

    public void updateFromDto(ActivityReqDto activityDto, Activity activity) {
        activity.setActivityName(activityDto.getActivityName());
        activity.setTimeOfActivity(activityDto.getTimeOfActivity());
        activity.setDateOfActivity(activityDto.getDateOfActivity());
        activity.setDuration(activityDto.getDuration());
    }
}
