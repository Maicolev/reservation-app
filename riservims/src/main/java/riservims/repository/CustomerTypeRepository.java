package riservims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riservims.model.CustomerType;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer> {
}