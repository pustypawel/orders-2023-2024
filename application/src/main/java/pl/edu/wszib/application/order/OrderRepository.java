package pl.edu.wszib.application.order;

import pl.edu.wszib.api.order.OrderApi;

import java.util.Optional;

public interface OrderRepository {
    OrderApi save(OrderApi order);

    Optional<OrderApi> findById(String id);
}
