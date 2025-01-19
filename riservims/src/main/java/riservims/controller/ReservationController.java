package riservims.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riservims.dto.ReservationRequest;
import riservims.dto.ReservationResponse;
import riservims.model.Reservation;
import riservims.service.ReservationService;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        log.info("Creating reservation: {}", request);
        Map<Integer, Double> mapResponse = reservationService.createReservation(request.getReservation(), request.getReservation().getSchedule(), request.getReservation().getCustomer());
        Map.Entry<Integer, Double> entry = mapResponse.entrySet().iterator().next();
        Integer idReservation = entry.getKey();
        Double discount = entry.getValue();

        if (Double.compare(discount, -2.0) == 0) {
            ReservationResponse reservationResponse = new ReservationResponse(0, 0.0, "Reservation failed - schedule does not available");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reservationResponse);
        }
        ReservationResponse reservationResponse = new ReservationResponse(idReservation, discount, "Reservation created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationResponse);

    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable Integer id, @RequestBody ReservationRequest request) {
        log.info("Updating reservation with ID: {}", id);
        try {
            reservationService.updateReservation(id, request.getReservation(), request.getReservation().getSchedule(), request.getReservation().getCustomer());
            return ResponseEntity.ok("Reservation updated successfully.");
        } catch (Exception e) {
            log.error("Error updating reservation: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Integer id) {
        log.info("Deleting reservation with ID: {}", id);
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Reservation deleted successfully.");
        } catch (Exception e) {
            log.error("Error deleting reservation: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found.");
        }
    }
}