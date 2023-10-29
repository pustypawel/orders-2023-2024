package pl.edu.wszib;

import pl.edu.wszib.api.order.OrderApi;
import pl.edu.wszib.api.order.OrderFacadeApi;
import pl.edu.wszib.api.order.OrderLineApi;
import pl.edu.wszib.api.product.ProductApi;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String productId = UUID.randomUUID().toString();

        ProductApi productApi = new ProductApi(
                productId,
                "Testowy produkt 1",
                new BigDecimal("10.25"),
                Instant.now(),
                Instant.now()
        );

        ProductApi product2Api = new ProductApi(
                productId,
                "Testowy produkt 1",
                new BigDecimal("10.25"),
                Instant.now(),
                Instant.now()
        );
        if (Objects.equals(productApi, product2Api)) {
            System.out.println("Produkty są takie same");
        } else {
            System.out.println("Produkty nie są takie same");
        }

        System.out.println("Dane produktu 1: " + productApi);
        System.out.println("Dane produktu 2: " + product2Api);

        Set<OrderLineApi> lines = new LinkedHashSet<>();
        OrderLineApi line1 = new OrderLineApi(productApi, 1, productApi.price());
        lines.add(line1);
        OrderApi order = new OrderApi(
                UUID.randomUUID().toString(),
                Instant.now(),
                Instant.now(),
                productApi.price(),
                lines
        );
        System.out.println("Dane zamówienia: " + order);
//        order.lines().add(new OrderLineApi(product2Api, 2, product2Api.price().multiply(BigDecimal.valueOf(2))));
//        System.out.println("Dane zamówienia: " + order);

        OrderFacadeApi orderFacade = null;
        OrderApi order1 = orderFacade.create();
        OrderApi order2 = orderFacade.create(line1, line1, line1);
    }
}