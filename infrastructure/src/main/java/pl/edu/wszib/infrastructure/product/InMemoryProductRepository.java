package pl.edu.wszib.infrastructure.product;

import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.application.product.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, ProductApi> products = new HashMap<>();

    @Override
    public ProductApi save(ProductApi productApi) {
        products.put(productApi.id(), productApi);
        return productApi;
    }

    @Override
    public Optional<ProductApi> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return products.containsKey(id);
    }
}
