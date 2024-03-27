package com.example.habittracker_v2.utils.mapper;

import com.example.habittracker_v2.dto.StatisticsDto;
import com.example.habittracker_v2.dto.StatisticsReqDto;
import com.example.habittracker_v2.model.Statistics;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper {
    public StatisticsDto toDto(Statistics statistics){
        StatisticsDto dto = new StatisticsDto();
        dto.setId(statistics.getId());
        dto.setGoal(statistics.getGoal());
        dto.setGoalName(statistics.getGoal().getGoalName());
        dto.setHabit(statistics.getHabit());
        dto.setHabitName(statistics.getHabit().getHabitName());
        dto.setActivities(statistics.getActivities());
        dto.setStatus(statistics.getStatus());
        return dto;
    }

    public Statistics toEntity(StatisticsReqDto dto){
        Statistics statistics = new Statistics();

        statistics.setStatus(dto.getStatus());
        return statistics;
    }

    public void updateStatisticsFromDto(StatisticsReqDto request, Statistics statistics) {
        statistics.setStatus(request.getStatus());
    }
}
