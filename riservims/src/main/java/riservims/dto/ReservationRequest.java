package riservims.dto;

import lombok.Data;
import riservims.model.Customer;
import riservims.model.Reservation;
import riservims.model.Schedule;

@Data
public class ReservationRequest {
    private Reservation reservation;
    private Schedule schedule;
    private Customer customer;
}