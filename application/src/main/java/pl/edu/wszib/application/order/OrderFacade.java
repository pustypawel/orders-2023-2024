package pl.edu.wszib.application.order;

import pl.edu.wszib.api.order.OrderApi;
import pl.edu.wszib.api.order.OrderFacadeApi;
import pl.edu.wszib.api.order.OrderLineApi;
import pl.edu.wszib.application.product.ProductFacade;

import java.util.Set;

public class OrderFacade implements OrderFacadeApi {
    // TODO: zaimplementować repozytorium in memory, klasę umieścić w odpowiednim miejscu
    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;

    public OrderFacade(final OrderRepository orderRepository,
                       final ProductFacade productFacade) {
        this.orderRepository = orderRepository;
        this.productFacade = productFacade;
    }

    // TODO: impl
    @Override
    public OrderApi create(final Set<OrderLineApi> lines) {
        return null;
    }

    // TODO: impl
    @Override
    public OrderApi getById(final String id) {
        return null;
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
