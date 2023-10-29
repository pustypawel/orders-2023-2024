package pl.edu.wszib.api.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record OrderApi(
        String id,
        Instant createdAt,
        Instant updatedAt,
        BigDecimal amount,
        Set<OrderLineApi> lines) {
    public OrderApi(String id,
                    Instant createdAt,
                    Instant updatedAt,
                    BigDecimal amount,
                    Set<OrderLineApi> lines) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.amount = amount;
        this.lines = Set.copyOf(lines);
    }


    public OrderApi(String id,
                    Instant createdAt,
                    Instant updatedAt,
                    BigDecimal amount,
                    OrderLineApi... lines) {
        this(id, createdAt, updatedAt, amount, Set.of(lines));
    }
}
