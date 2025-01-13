package riservims.repository;

public interface ReservationRepositoryCustom {
    Double callCreateReservationProcedure(Integer customerId, Integer scheduleId, Integer reservationTypeId, Integer numberOfPeople);
}