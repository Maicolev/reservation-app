package riservims.dto;

import lombok.Data;
import riservims.model.Reservation;

@Data
public class ReservationRequest {
    private Reservation reservation;
}