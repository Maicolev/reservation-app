package riservims.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import riservims.repository.ReservationRepositoryCustom;

@Repository
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public ReservationRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Double callCreateReservationProcedure(Integer customerId, Integer scheduleId, Integer reservationTypeId, Integer numberOfPeople) {
        // Crear la consulta del procedimiento almacenado
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_reservation");

        // Establecer los parámetros de entrada
        query.registerStoredProcedureParameter("p_customer_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_schedule_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_reservation_type", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_number_of_people", Integer.class, jakarta.persistence.ParameterMode.IN);

        // Establecer los parámetros de salida
        query.registerStoredProcedureParameter("p_discount", Double.class, jakarta.persistence.ParameterMode.OUT);

        // Asignar los valores de entrada
        query.setParameter("p_customer_id", customerId);
        query.setParameter("p_schedule_id", scheduleId);
        query.setParameter("p_reservation_type", reservationTypeId);
        query.setParameter("p_number_of_people", numberOfPeople);

        // Ejecutar el procedimiento almacenado
        query.execute();

        // Obtener el valor del parámetro de salida
        Double discount = (Double) query.getOutputParameterValue("p_discount");

        return discount;
    }
}