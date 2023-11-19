package pl.edu.wszib.application.product;

import pl.edu.wszib.api.product.ProductApi;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ProductSamples {
    static final ProductApi SAMPLE_1 = new ProductApi(UUID.randomUUID().toString(), "Testowy produkt", new BigDecimal("100.23"), Instant.now(), Instant.now());
}
