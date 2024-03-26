package com.example.habittracker_v2.controller;

import com.example.habittracker_v2.dto.HabitDto;
import com.example.habittracker_v2.dto.HabitReqDto;
import com.example.habittracker_v2.dto.ReminderReqDto;
import com.example.habittracker_v2.model.Reminder;
import com.example.habittracker_v2.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit")
@Validated
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping("/add")
    public ResponseEntity<HabitDto> addHabit(@Valid @RequestBody HabitReqDto request){
        HabitDto habit = habitService.addHabit(request);
        return new ResponseEntity<>(habit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitDto> getHabit(@PathVariable Long id){
        HabitDto habit = habitService.getHabit(id);
        return new ResponseEntity<>(habit, HttpStatus.OK);
    }

    @PatchMapping ("/update/{id}")
    public ResponseEntity<HabitDto> updateHabit(@PathVariable Long id, @Valid @RequestBody HabitReqDto request){
        HabitDto habit = habitService.updateHabit(id, request);
        return new ResponseEntity<>(habit, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/allReminders")
    public ResponseEntity<List<Reminder>> getAllRemindersByHabitId(@PathVariable Long id) {
        HabitDto habit = habitService.getHabit(id);
        return new ResponseEntity<>(habit.getReminders(), HttpStatus.OK);
    }





}
