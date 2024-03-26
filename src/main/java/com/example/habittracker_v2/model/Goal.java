package com.example.habittracker_v2.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "GOAL")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GOAL_NAME", length = 128, nullable = false)
    private String goalName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "GOAL_CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @OneToMany(mappedBy = "goal")
    private List<Habit> habits = new ArrayList<>();

    @OneToMany(mappedBy = "goal")
    private List<Reminder> reminders = new ArrayList<>();
}
