package pl.edu.wszib.application.product;

import pl.edu.wszib.api.product.ProductApi;

import java.util.Optional;

public interface ProductRepository {
    ProductApi save(ProductApi productApi);

    Optional<ProductApi> findById(String id);

    boolean existsById(String id);
}
