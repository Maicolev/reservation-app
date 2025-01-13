package riservims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riservims.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationRepositoryCustom {
    List<Reservation> findByid(Integer customerId);
}