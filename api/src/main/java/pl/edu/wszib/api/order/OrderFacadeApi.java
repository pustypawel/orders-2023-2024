package pl.edu.wszib.api.order;

import java.util.Set;

public interface OrderFacadeApi {
    default OrderApi create() {
        return create(Set.of());
    }

    default OrderApi create(OrderLineApi... lines) {
        return create(Set.of(lines));
    }

    OrderApi create(Set<OrderLineApi> lines);

    OrderResult getById(String id);

    default OrderApi addProduct(String productId) {
        return addProduct(productId, 1);
    }

    OrderApi addProduct(String productId, Integer quantity);

    OrderApi removeProduct(String productId);

    OrderApi changeQuantity(String productId, Integer quantity);

    OrderApi increaseQuantity(String productId);

    OrderApi decreaseQuantity(String productId);
}
