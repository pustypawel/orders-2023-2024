package pl.edu.wszib.application.order;

import pl.edu.wszib.api.order.*;
import pl.edu.wszib.api.product.ProductResult;
import pl.edu.wszib.application.product.ProductFacade;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class OrderFacade implements OrderFacadeApi {
    private final OrderIdProvider orderIdProvider;
    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;
    private final Clock clock;


    public OrderFacade(final OrderIdProvider orderIdProvider,
                       final OrderRepository orderRepository,
                       final ProductFacade productFacade,
                       final Clock clock) {
        this.orderIdProvider = orderIdProvider;
        this.orderRepository = orderRepository;
        this.productFacade = productFacade;
        this.clock = clock;
    }

    @Override
    public OrderApi create(final Set<OrderLineApi> lines) {
        final BigDecimal amount = lines.stream()
                .map(OrderLineApi::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        final OrderApi order = new OrderApi(orderIdProvider.create(), Instant.now(clock), Instant.now(clock), amount, lines);
        return orderRepository.save(order);
    }

    @Override
    public OrderResult getById(final String id) {
        return orderRepository.findById(id)
                .map(OrderResult::success)
                .orElseGet(() -> OrderResult.failure(OrderResult.Code.NOT_FOUND, "Order with id %s does not exist.".formatted(id)));
    }

    @Override
    public OrderApi addProduct(
            final String productId,
            final Integer quantity) {
        return null;
    }

    @Override
    public OrderApi removeProduct(final String productId) {
        return null;
    }

    @Override
    public OrderApi changeQuantity(
            final String productId,
            final Integer quantity) {
        return null;
    }

    @Override
    public OrderApi increaseQuantity(final String productId) {
        return null;
    }

    @Override
    public OrderApi decreaseQuantity(final String productId) {
        return null;
    }
}
