package com.example.habittracker_v2.dto;

import com.example.habittracker_v2.model.Status;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class StatisticsReqDto {
    private Long goalId;
    private Long habitId;
    private Status status;
}
