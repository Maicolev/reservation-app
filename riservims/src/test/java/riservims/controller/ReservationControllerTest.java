package riservims.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import riservims.dto.ReservationRequest;
import riservims.dto.ReservationResponse;
import riservims.model.Reservation;
import riservims.service.ReservationService;

import java.util.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReservation_ShouldReturnSuccessResponse() {
        ReservationRequest request = new ReservationRequest();
        Reservation reservation = new Reservation();
        request.setReservation(reservation);

        Map<Integer, Double> mapResponse = Map.of(1, 10.0);
        when(reservationService.createReservation(any(), any(), any())).thenReturn(mapResponse);

        ResponseEntity<ReservationResponse> response = reservationController.createReservation(request);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getMessage()).isEqualTo("Reservation created successfully");
    }

    @Test
    void getAllReservations_ShouldReturnListOfReservations() {
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(reservationService.getAllReservations()).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationController.getAllReservations();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
    }
}