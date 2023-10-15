package pl.edu.wszib.api.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

public record OrderApi(
        String id,
        Instant createdAt,
        Instant updatedAt,
        Set<OrderLineApi> lines,
        BigDecimal amount) {
    public OrderApi(String id,
                    Instant createdAt,
                    Instant updatedAt,
                    Collection<OrderLineApi> lines,
                    BigDecimal amount) {
        this(id, createdAt, updatedAt, Set.copyOf(lines), amount);
    }

    public OrderApi(String id,
                    Instant createdAt,
                    Instant updatedAt,
                    OrderLineApi[] lines,
                    BigDecimal amount) {
        this(id, createdAt, updatedAt, Set.of(lines), amount);
    }
}
