package riservims.repository;

import riservims.model.Customer;
import riservims.model.Schedule;

import java.util.Map;

public interface ReservationRepositoryCustom {
    Map<Integer, Double> callCreateReservationProcedure(Customer customer, Schedule schedule, Integer reservationTypeId, Integer numberOfPeople);
}