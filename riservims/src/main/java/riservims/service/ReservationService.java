package riservims.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import riservims.model.Customer;
import riservims.model.Reservation;
import riservims.model.Schedule;
import riservims.repository.CustomerRepository;
import riservims.repository.ReservationRepository;
import riservims.repository.ScheduleRepository;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CustomerRepository customerRepository, ScheduleRepository scheduleRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // -1 error creating transaction
    // -2 schedule busy
    // -3 not found customer
    @Transactional
    public Map<Integer, Double> createReservation(Reservation reservation, Schedule schedule, Customer customer) {
        log.info("into service");

        log.info(String.valueOf(customer.getId()));
        log.info(String.valueOf(customer.getId()));
        log.info(String.valueOf(reservation.getReservationType().getId()));
        log.info(String.valueOf(reservation.getNumberOfPeople()));

        // 1. sp call
        return reservationRepository.callCreateReservationProcedure(
                customer,
                schedule,
                reservation.getReservationType().getId(),
                reservation.getNumberOfPeople()
        );
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public void updateReservation(Integer id, Reservation reservation, Schedule schedule, Customer customer) {
        log.info("Updating reservation with ID: {}", id);
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + id));

        // Actualización de los datos de la reservación
        existingReservation.setNumberOfPeople(reservation.getNumberOfPeople());
        existingReservation.setReservationType(reservation.getReservationType());

        // Actualización del cliente si es necesario
        if (customer != null) {
            Customer existingCustomer = customerRepository.findById(customer.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customer.getId()));
            existingReservation.setCustomer(existingCustomer);
        }

        // Actualización del horario si es necesario
        if (schedule != null) {
            Schedule existingSchedule = scheduleRepository.findById(schedule.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Schedule not found with ID: " + schedule.getId()));
            existingReservation.setSchedule(existingSchedule);
        }

        reservationRepository.save(existingReservation);
        log.info("Reservation updated successfully.");
    }

    @Transactional
    public void deleteReservation(Integer id) {
        log.info("Deleting reservation with ID: {}", id);
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + id));

        // Liberar el horario al eliminar la reservación
        Schedule schedule = reservation.getSchedule();
        schedule.setIsAvailable(true);
        scheduleRepository.save(schedule);

        reservationRepository.delete(reservation);
        log.info("Reservation deleted successfully.");
    }
}