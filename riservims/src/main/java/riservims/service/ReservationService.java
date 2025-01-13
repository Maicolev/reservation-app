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

import java.math.BigDecimal;
import java.util.List;

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
    public BigDecimal createReservation(Reservation reservation, Schedule schedule, Customer customer) {
        log.info("Entre el service");

        // 1.Schedule validate and create
        Schedule savedSchedule = scheduleRepository.findById(schedule.getId())
                .orElseGet(() -> {
                    log.info("Entre crea schedule");
                    schedule.setIsAvailable(true);
                    return scheduleRepository.save(schedule);
                });

        if (!savedSchedule.getIsAvailable()) {
            log.info("Schedule no disponible");
            return BigDecimal.valueOf(-2);
        }

        savedSchedule.setIsAvailable(false);
        scheduleRepository.save(savedSchedule);
        scheduleRepository.flush();

        // 2. Client validate
        Customer savedCustomer = customerRepository.findById(customer.getId())
                .orElseGet(() -> {
                    log.info("Entre crea customer");
                    //return customerRepository.save(customer);
                    return null;
                });
        if (savedCustomer == null) return BigDecimal.valueOf(-3);

        customerRepository.flush();

        log.info(String.valueOf(savedCustomer.getId()));
        log.info(String.valueOf(savedSchedule.getId()));
        log.info(String.valueOf(reservation.getReservationType().getId()));
        log.info(String.valueOf(reservation.getNumberOfPeople()));
        // 3. sp call
        BigDecimal discount = BigDecimal.valueOf(reservationRepository.callCreateReservationProcedure(
                savedCustomer.getId(),
                savedSchedule.getId(),
                reservation.getReservationType().getId(),
                reservation.getNumberOfPeople()
        ));

        // 4. discount check
        log.info("Descuento obtenido: " + discount);
        if (discount.compareTo(BigDecimal.ZERO) >= 0) {
            reservation.setDiscountApplied(discount);
            reservation.setSchedule(savedSchedule);
            return discount;
        } else {
            throw new IllegalStateException("Cannot create reservation.");
        }
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}