package com.example.habittracker_v2.controller;

import com.example.habittracker_v2.dto.StatisticsDto;
import com.example.habittracker_v2.dto.StatisticsReqDto;
import com.example.habittracker_v2.service.StatisticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@Validated
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @PostMapping("/add")
    public ResponseEntity<StatisticsDto> addStatistics(@Valid @RequestBody StatisticsReqDto request){
        StatisticsDto statistics = statisticsService.addStatistics(request);
        return new ResponseEntity<>(statistics, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatisticsDto> getStatistics(@PathVariable Long id){
        StatisticsDto statistics = statisticsService.getStatistics(id);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<StatisticsDto> updateStatistics(@PathVariable Long id, @Valid @RequestBody StatisticsReqDto request){
        StatisticsDto statistics = statisticsService.updateStatistics(id, request);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStatistics(@PathVariable Long id){
        statisticsService.deleteStatistics(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
