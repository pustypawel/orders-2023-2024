package pl.edu.wszib.api.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class OrderApi {
    private final String id;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final Set<OrderLineApi> lines;
    private final BigDecimal amount;

    public OrderApi(String id,
                    Instant createdAt,
                    Instant updatedAt,
                    Collection<OrderLineApi> lines,
                    BigDecimal amount) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lines = Set.copyOf(lines);
        this.amount = amount;
    }

    public OrderApi(String id,
                    Instant createdAt,
                    Instant updatedAt,
                    OrderLineApi[] lines,
                    BigDecimal amount) {
        this(id, createdAt, updatedAt, Set.of(lines), amount);
    }

    public String id() {
        return id;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public Set<OrderLineApi> lines() {
        return lines;
    }

    public BigDecimal amount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderApi orderApi = (OrderApi) o;
        return Objects.equals(id, orderApi.id) && Objects.equals(createdAt, orderApi.createdAt) && Objects.equals(updatedAt, orderApi.updatedAt) && Objects.equals(lines, orderApi.lines) && Objects.equals(amount, orderApi.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, lines, amount);
    }

    @Override
    public String toString() {
        return "OrderApi{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", lines=" + lines +
                ", amount=" + amount +
                '}';
    }
}
