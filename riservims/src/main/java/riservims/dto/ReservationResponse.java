package riservims.dto;

import lombok.Data;

@Data
public class ReservationResponse {
    private Integer idReservation;
    private Double discount;
    private String message;

    public ReservationResponse(Integer idReservation, Double discount, String message) {
        this.idReservation = idReservation;
        this.discount = discount;
        this.message = message;
    }
}
