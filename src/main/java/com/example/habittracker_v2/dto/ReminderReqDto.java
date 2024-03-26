package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.FrequencyUnit;
import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class ReminderReqDto {
    private String message;
    private LocalTime reminderTime;
    private Integer reminderFrequency;
    private FrequencyUnit frequencyUnit;
    private Long habitId;
    private Long goalId;
}
