package com.example.habittracker_v2.repository;

import com.example.habittracker_v2.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Optional<Statistics> findByHabit_Id(Long habitId);
}
