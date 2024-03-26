package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.FrequencyUnit;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class HabitReqDto {

    private String habitName;
    private String description;
    private Integer frequency;
    private FrequencyUnit frequencyUnit;
    private Long goalId;
    private List<Long> activityIds;
    private List<Long> reminderIds;
}
