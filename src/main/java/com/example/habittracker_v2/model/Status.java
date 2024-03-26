package com.example.habittracker_v2.model;

import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "STATUS")
public enum Status {
    IN_PROGRESS,
    PLANNED,
    COMPLETED,
    CANCELLED,
    FAILED
}
