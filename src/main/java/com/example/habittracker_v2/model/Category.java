package com.example.habittracker_v2.model;

import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "CATEGORY")
public enum Category {
    HEALTH,
    EDUCATION,
    FINANCE
}
