package riservims.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import riservims.model.Customer;
import riservims.model.Schedule;
import riservims.repository.ReservationRepositoryCustom;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public ReservationRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Map<Integer, Double> callCreateReservationProcedure(Customer customer, Schedule schedule, Integer reservationTypeId, Integer numberOfPeople) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_reservation_v2");

        // set into parameter
        query.registerStoredProcedureParameter("p_customer_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_first_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_last_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_phone_number", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_customer_type_id", Integer.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("p_schedule_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_schedule_date", java.sql.Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_start_time", java.sql.Time.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_end_time", java.sql.Time.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("p_reservation_type", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_number_of_people", Integer.class, ParameterMode.IN);

        // set out paramter
        query.registerStoredProcedureParameter("p_discount", Double.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_id_reservation", Integer.class, ParameterMode.OUT);

        // set in values
        query.setParameter("p_customer_id", customer.getId());
        query.setParameter("p_first_name", customer.getFirstName());
        query.setParameter("p_last_name", customer.getLastName());
        query.setParameter("p_email", customer.getEmail());
        query.setParameter("p_phone_number", customer.getPhoneNumber());
        query.setParameter("p_customer_type_id", customer.getCustomerType() != null ? customer.getCustomerType().getId() : null);

        query.setParameter("p_schedule_id", schedule.getId());
        query.setParameter("p_schedule_date", java.sql.Date.valueOf(schedule.getScheduleDate()));
        query.setParameter("p_start_time", java.sql.Time.valueOf(schedule.getStartTime()));
        query.setParameter("p_end_time", java.sql.Time.valueOf(schedule.getEndTime()));

        query.setParameter("p_reservation_type", reservationTypeId);
        query.setParameter("p_number_of_people", numberOfPeople);

        query.execute();

        // get out values
        Double discount = (Double) query.getOutputParameterValue("p_discount");
        Integer idReservation = (Integer) query.getOutputParameterValue("p_id_reservation");

        Map<Integer, Double> map = new HashMap<>();
        map.put(idReservation, discount);
        return map;
    }
}