package pl.edu.wszib.application.product;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.api.product.ProductResult;
import pl.edu.wszib.infrastructure.product.InMemoryProductRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductFacadeTest {
    private final ProductFacade productFacade;

    public ProductFacadeTest() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        this.productFacade = new ProductFacade(productRepository);
    }

    @Test
    public void should_create_product() {
        // given: we have simple product
        final ProductApi productApi = ProductSamples.SAMPLE_1;

        // when: we try to create product
        final ProductResult result = productFacade.create(productApi);

        // then: we expect success
        assertTrue(result.isSuccess());
        // and: created product has the same data as we passed to
        assertEquals(productApi, result.success());
    }

    @Test
    public void should_find_created_product() {
        // given: we have created product
        final String createdProductId = productFacade.create(ProductSamples.SAMPLE_1).success().id();

        // when: we try to find product by id
        ProductResult result = productFacade.getById(createdProductId);

        // then: we expect success
        assertTrue(result.isSuccess());
        // and: resulted product is not null
        assertNotNull(result.success());
    }

    @Test
    public void should_not_find_not_existing_product() {
        // given: we have not existing product id
        final String notExistingProductId = UUID.randomUUID().toString();

        // when: we try to find product by id
        final ProductResult result = productFacade.getById(notExistingProductId);

        // then: we expect failure
        assertTrue(result.isFailure());
        // and: code should be not_exist
        assertEquals(ProductResult.Code.NOT_FUND, result.code());
    }

    @Test
    public void should_not_create_product_twice() {
        // given: we have simple product
        final ProductApi sample1 = ProductSamples.SAMPLE_1;
        // and: product is created
        productFacade.create(sample1);

        // when: we try to create the same product again
        final ProductResult result = productFacade.create(sample1);

        // then: we expect failure
        assertTrue(result.isFailure());
        // and: code should be already_exists
        assertEquals(ProductResult.Code.ALREADY_EXISTS, result.code());
    }
}