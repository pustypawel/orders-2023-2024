package pl.edu.wszib.api.order;

import pl.edu.wszib.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderLineApi {
    // TODO: zaimplementować resztę struktury
    private final ProductApi product;
    // https://unitsofmeasurement.gitbook.io/unit-ri-userguide/
    private final Integer quantity;
    private final BigDecimal amount;

    public OrderLineApi(ProductApi product,
                        Integer quantity,
                        BigDecimal amount) {
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    public ProductApi product() {
        return product;
    }

    public Integer quantity() {
        return quantity;
    }

    public BigDecimal amount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineApi that = (OrderLineApi) o;
        return Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, amount);
    }

    @Override
    public String toString() {
        return "OrderLineApi{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
