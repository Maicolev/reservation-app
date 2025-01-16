package riservims.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import riservims.model.Schedule;
import riservims.service.ScheduleService;

import java.util.Arrays;
import java.util.List;

class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSchedule_ShouldReturnCreatedSchedule() {
        Schedule schedule = new Schedule();
        when(scheduleService.saveSchedule(any(Schedule.class))).thenReturn(schedule);

        ResponseEntity<Schedule> response = scheduleController.createSchedule(schedule);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(schedule);
    }

    @Test
    void getAvailableSchedules_ShouldReturnAvailableSchedules() {
        List<Schedule> schedules = Arrays.asList(new Schedule(), new Schedule());
        when(scheduleService.getAvailableSchedules()).thenReturn(schedules);

        ResponseEntity<List<Schedule>> response = scheduleController.getAvailableSchedules();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
    }
}