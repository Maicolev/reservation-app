package riservims.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import riservims.dto.ReservationRequest;
import riservims.model.Reservation;
import riservims.model.Schedule;
import riservims.service.ReservationService;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request) {
        log.info("Creating entranding:...........");
        log.info("Creating schedule: {}", request.getSchedule());
        log.info("Creating reservation: {}", request.getReservation());
        log.info("Creating reservation: {}", request.getCustomer());
        BigDecimal response = reservationService.createReservation(request.getReservation(), request.getSchedule(), request.getCustomer());
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }
}