package com.example.habittracker_v2.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Getter
@Setter
@Table(name = "STATISTICS")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;


    @Column(name = "GOAL_NAME")
    private String goalName;

    @OneToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;

    @Column(name = "HABIT_NAME")
    private String habitName;

    @Column(name = "PROGRESS")
    private String progress;



    @OneToMany(mappedBy = "statistics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities = new ArrayList<>();

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;
}

