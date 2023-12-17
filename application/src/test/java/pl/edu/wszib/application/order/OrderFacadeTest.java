package pl.edu.wszib.application.order;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.api.order.OrderApi;
import pl.edu.wszib.api.order.OrderResult;
import pl.edu.wszib.application.product.ProductFacade;
import pl.edu.wszib.infrastructure.order.InMemoryOrderRepository;
import pl.edu.wszib.infrastructure.product.InMemoryProductRepository;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderFacadeTest {
    private final OrderFacade orderFacade;

    public OrderFacadeTest() {
        final UUIDOrderIdProvider orderIdProvider = new UUIDOrderIdProvider();
        final InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();

        final ProductFacade productFacade = new ProductFacade(new InMemoryProductRepository());

        this.orderFacade = new OrderFacade(orderIdProvider, orderRepository, productFacade, Clock.systemDefaultZone());
    }

    @Test
    public void should_create_order_without_lines() {
        // given:

        // when:
        final OrderApi order = orderFacade.create();

        // then:
        final OrderResult result = orderFacade.getById(order.id());
        assertTrue(result.isSuccess());
        assertEquals(order.id(), result.success().id());
    }

    @Test
    public void should_add_product_to_order() {
        // given: we have created empty order

        // and: we have existing product

        // when: we try to add existing product to order

        // then: order should contain that product
    }
}
