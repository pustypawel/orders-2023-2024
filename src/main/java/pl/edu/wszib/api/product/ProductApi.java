package pl.edu.wszib.api.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public final class ProductApi {

    private final String id;
    private final String name;
    // Alternatywa dla BigDecimal: https://javamoney.github.io/api.html
    private final BigDecimal price;

    private final Instant createdAt;
    private final Instant updatedAt;

    public ProductApi(String id,
                      String name,
                      BigDecimal price,
                      Instant createdAt,
                      Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public BigDecimal price() {
        return price;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductApi that = (ProductApi) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "ProductApi{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
