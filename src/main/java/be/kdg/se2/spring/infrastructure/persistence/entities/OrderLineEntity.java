package be.kdg.se2.spring.infrastructure.persistence.entities;

import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "order_lines")
public class OrderLineEntity {

    @Id
    @GeneratedValue
    private Long id;

    private UUID productId;
    private int quantity;
    private BigDecimal unitPrice;

    // getters and setters
}
