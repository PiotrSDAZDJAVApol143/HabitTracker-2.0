package com.example.habittracker_v2.model;

import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "FREQUENCY_UNIT")
public enum FrequencyUnit {
    DAILY,
    WEEKLY,
    MONTHLY
}