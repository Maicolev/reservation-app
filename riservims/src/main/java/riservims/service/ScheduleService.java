package riservims.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riservims.model.Schedule;
import riservims.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAvailableSchedules() {
        return scheduleRepository.findByIsAvailableTrue();
    }

    public void updateScheduleAvailability(int scheduleId, boolean isAvailable) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));
        schedule.setIsAvailable(isAvailable);
        scheduleRepository.save(schedule);
    }
}