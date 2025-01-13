package riservims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import riservims.model.Schedule;
import riservims.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Schedule>> getAvailableSchedules() {
        List<Schedule> schedules = scheduleService.getAvailableSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PatchMapping("/{scheduleId}/availability")
    public ResponseEntity<Void> updateScheduleAvailability(
            @PathVariable int scheduleId, @RequestParam boolean available) {
        scheduleService.updateScheduleAvailability(scheduleId, available);
        return ResponseEntity.noContent().build();
    }
}