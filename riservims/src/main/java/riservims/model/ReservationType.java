package riservims.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reservation_type")
public class ReservationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_type_id")
    private Integer id;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;
}