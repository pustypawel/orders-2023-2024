package pl.edu.wszib.application.order;

import pl.edu.wszib.api.order.OrderApi;
import pl.edu.wszib.api.order.OrderFacadeApi;
import pl.edu.wszib.api.order.OrderLineApi;
import pl.edu.wszib.api.order.OrderResult;
import pl.edu.wszib.api.product.ProductFacadeApi;
import pl.edu.wszib.api.product.ProductResult;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

public class OrderFacade implements OrderFacadeApi {
    private final OrderIdProvider orderIdProvider;
    private final OrderRepository orderRepository;
    private final ProductFacadeApi productFacade;
    private final Clock clock;


    public OrderFacade(final OrderIdProvider orderIdProvider,
                       final OrderRepository orderRepository,
                       final ProductFacadeApi productFacade,
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
    public OrderResult getById(final String orderId) {
        return orderRepository.findById(orderId)
                .map(OrderResult::success)
                .orElseGet(() -> OrderResult.failure(OrderResult.Code.NOT_FOUND, "Order with id %s does not exist.".formatted(orderId)));
    }

    @Override
    public OrderResult addProduct(
            final String orderId,
            final String productId,
            final Integer quantity) {
        final Optional<OrderApi> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return OrderResult.failure(OrderResult.Code.NOT_FOUND, "Order with id %s does not exist.".formatted(orderId));
        }
        final OrderApi order = orderOpt.get();

        final OrderResult result = order.changeQuantity(productId, quantity, clock);
        if (result.isSuccess()) {
            orderRepository.save(result.success());
            return result;
        } else {
            final ProductResult productResult = productFacade.getById(productId);
            if (productResult.isFailure()) {
                return OrderResult.failure(OrderResult.Code.PRODUCT_ERROR, productResult.toString());
            }
            final OrderApi changedOrder = order.addLine(productResult.success(), quantity, clock);
            return OrderResult.success(orderRepository.save(changedOrder));
        }
    }

    @Override
    public OrderResult removeProduct(
            final String orderId,
            final String productId) {
        return null;
    }

    @Override
    public OrderResult changeQuantity(
            final String orderId,
            final String productId,
            final Integer quantity) {
        return null;
    }

    @Override
    public OrderResult increaseQuantity(
            final String orderId,
            final String productId) {
        return null;
    }

    @Override
    public OrderResult decreaseQuantity(
            final String orderId,
            final String productId) {
        return null;
    }
}
