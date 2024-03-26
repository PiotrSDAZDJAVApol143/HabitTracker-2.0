package com.example.habittracker_v2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Getter
@Setter
@Table(name = "REMINDER")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "REMINDER_TIME")
    private LocalTime reminderTime;

    @Column(name = "REMINDER_FREQUENCY")
    private Integer reminderFrequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "FREQUENCY_UNIT")
    private FrequencyUnit frequencyUnit;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;
}
