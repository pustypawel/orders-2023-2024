package pl.edu.wszib.api.order;

import pl.edu.wszib.api.product.ProductApi;

import java.math.BigDecimal;

public record OrderLineApi(
        ProductApi product,
        Integer quantity,
        BigDecimal amount) {
}
