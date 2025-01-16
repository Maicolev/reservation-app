package riservims.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "customer_type")
public class CustomerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_type_id")
    private Integer id;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;

    @Column(name = "monthly_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyFee;

    @Column(name = "discount_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal discountPercentage;
}