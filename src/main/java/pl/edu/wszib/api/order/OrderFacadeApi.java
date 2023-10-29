package pl.edu.wszib.api.order;

import pl.edu.wszib.api.product.ProductApi;

import java.util.Set;

public interface OrderFacadeApi {
    // create
    // getById
    // addProduct
    // removeProduct
    // changeQuantity
    // increaseQuantity
    // decreaseQuantity
    default OrderApi create() {
        return create(Set.of());
    }

    default OrderApi create(OrderLineApi... lines) {
        return create(Set.of(lines));
    }

    OrderApi create(Set<OrderLineApi> lines);

    OrderApi getById(String id);

    default OrderApi addProduct(ProductApi product) {
        return addProduct(product, 1);
    }

    OrderApi addProduct(ProductApi product, Integer quantity);
}
