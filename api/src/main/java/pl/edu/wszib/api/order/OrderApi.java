package pl.edu.wszib.api.order;

import pl.edu.wszib.api.product.ProductApi;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public OrderResult changeQuantity(
            final String productId,
            final Integer quantity,
            final Clock clock) {
        final Optional<OrderLineApi> productLineOpt = lines.stream()
                .filter(line -> line.product().id().equals(productId))
                .findFirst();
        if (productLineOpt.isEmpty()) {
            return OrderResult.failure(OrderResult.Code.NO_PRODUCT, "Order with id = %s does not contain product %s, can't change quantity.".formatted(id, productId));
        }
        final OrderLineApi productLine = productLineOpt.get();
        final OrderLineApi changedLine = productLine.addQuantity(quantity);

        final Set<OrderLineApi> linesWithoutChanged = lines.stream()
                .filter(line -> !line.product().id().equals(productId))
                .collect(Collectors.toSet());

        final Set<OrderLineApi> changedLines = new HashSet<>(linesWithoutChanged);
        changedLines.add(changedLine);

        final BigDecimal changedAmount = changedLines.stream()
                .map(OrderLineApi::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return OrderResult.success(new OrderApi(id, createdAt, Instant.now(clock), changedAmount, changedLines));
    }

    public OrderApi addLine(
            final ProductApi product,
            final Integer quantity,
            final Clock clock) {
        final Set<OrderLineApi> changedLines = new HashSet<>();
        changedLines.addAll(lines);
        final BigDecimal lineAmount = product.price().multiply(BigDecimal.valueOf(quantity));
        changedLines.add(new OrderLineApi(product, quantity, lineAmount));

        final BigDecimal changedAmount = changedLines.stream()
                .map(OrderLineApi::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderApi(id, createdAt, Instant.now(clock), changedAmount, changedLines);
    }
}
