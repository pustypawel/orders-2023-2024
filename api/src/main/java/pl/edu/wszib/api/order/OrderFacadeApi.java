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

    OrderResult getById(String orderId);

    default OrderResult addProduct(String orderId, String productId) {
        return addProduct(orderId, productId, 1);
    }

    OrderResult addProduct(String orderId, String productId, Integer quantity);

    OrderResult removeProduct(String orderId, String productId);

    OrderResult changeQuantity(String orderId, String productId, Integer quantity);

    OrderResult increaseQuantity(String orderId, String productId);

    OrderResult decreaseQuantity(String orderId, String productId);
}
