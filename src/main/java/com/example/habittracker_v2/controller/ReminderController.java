package com.example.habittracker_v2.controller;

import com.example.habittracker_v2.dto.GoalDto;
import com.example.habittracker_v2.dto.GoalReqDto;
import com.example.habittracker_v2.dto.ReminderDto;
import com.example.habittracker_v2.dto.ReminderReqDto;
import com.example.habittracker_v2.model.Reminder;
import com.example.habittracker_v2.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminder")
@Validated
@RequiredArgsConstructor
public class ReminderController {
    private final ReminderService reminderService;

    @GetMapping("/showAll")
    public ResponseEntity<List<Reminder>> showAllReminders() {
        List<Reminder> reminderList = reminderService.getAllReminders();
        return new ResponseEntity<>(reminderList, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ReminderDto> addReminder(@Valid @RequestBody ReminderReqDto request){
        ReminderDto reminderDto = reminderService.addReminder(request);
        return new ResponseEntity<>(reminderDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReminderDto> getReminderById(@PathVariable Long id){
        ReminderDto reminderDto = reminderService.getReminderById(id);
        return new ResponseEntity<>(reminderDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReminderDto> updateReminderById(@PathVariable Long id, @Valid @RequestBody ReminderReqDto request){
        ReminderDto reminderDto = reminderService.updateReminderById(id, request);
        return new ResponseEntity<>(reminderDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReminderById(@PathVariable Long id){
        reminderService.deleteReminderById(id);
        return ResponseEntity.noContent().build();
    }


}
