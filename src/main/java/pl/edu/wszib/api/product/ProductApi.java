package pl.edu.wszib.api.product;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductApi(
        String id,
        String name,
        BigDecimal price,
        Instant createdAt,
        Instant updatedAt) {
}
