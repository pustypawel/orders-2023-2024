package pl.edu.wszib.application.product;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.api.product.ProductResult;
import pl.edu.wszib.infrastructure.product.InMemoryProductRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductFacadeTest {
    private final ProductFacade productFacade;

    public ProductFacadeTest() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        this.productFacade = new ProductFacade(productRepository);
    }

    @Test
    public void should_create_product() {
        // given: we have simple product
        final ProductApi productApi = new ProductApi(UUID.randomUUID().toString(), "Testowy produkt", new BigDecimal("100.23"), Instant.now(), Instant.now());

        // when: we try to create product
        final ProductResult result = productFacade.create(productApi);

        // then: we expect success
        assertTrue(result.isSuccess());
        // and: created product has the same data as we passed to
        assertEquals(productApi, result.success());
    }

    // TODO: implement tests

    // should find created product (utworzyć produkt, spróbować wyszukać produkt, produkt powinien być odnaleziony)
    // should not find not existing produkt (utworzyć nieistniejące productId, wyszukać produktu na podstawie tego id, produkt nie powinien być odnaleziony)
    // should not create product twice (utworzyc produkt, spróbować utworzyć ten sam produkt, produkt nie powinien zostać utworzony (błędny resultat + kod błędu already exsists))

}