package pl.edu.wszib;

import pl.edu.wszib.api.product.ProductApi;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

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
    }
}