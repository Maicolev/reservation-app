package riservims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riservims.model.ReservationType;

@Repository
public interface ReservationTypeRepository extends JpaRepository<ReservationType, Integer> {
}