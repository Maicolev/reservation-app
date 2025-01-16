package riservims.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import riservims.model.Schedule;
import riservims.repository.ScheduleRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        schedule = new Schedule();
        schedule.setId(1);
        schedule.setIsAvailable(true);
    }

    @Test
    void testSaveSchedule() {
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        assertNotNull(savedSchedule);
        assertEquals(1, savedSchedule.getId());
        assertTrue(savedSchedule.getIsAvailable());
    }

    @Test
    void testGetAvailableSchedules() {
        when(scheduleRepository.findByIsAvailableTrue()).thenReturn(List.of(schedule));
        List<Schedule> availableSchedules = scheduleService.getAvailableSchedules();
        assertNotNull(availableSchedules);
        assertEquals(1, availableSchedules.size());
        assertTrue(availableSchedules.get(0).getIsAvailable());
    }

    @Test
    void testUpdateScheduleAvailability() {
        Schedule existingSchedule = new Schedule();
        existingSchedule.setId(1);
        existingSchedule.setIsAvailable(true);
        when(scheduleRepository.findById(1)).thenReturn(Optional.of(existingSchedule));
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(existingSchedule);
        scheduleService.updateScheduleAvailability(1, false);
        verify(scheduleRepository, times(1)).save(existingSchedule);
        assertFalse(existingSchedule.getIsAvailable());
    }

    @Test
    void testUpdateScheduleAvailability_ScheduleNotFound() {
        when(scheduleRepository.findById(1)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            scheduleService.updateScheduleAvailability(1, false);
        });
        assertEquals("Schedule not found", exception.getMessage());
    }
}