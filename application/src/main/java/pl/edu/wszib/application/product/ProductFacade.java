package pl.edu.wszib.application.product;

import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.api.product.ProductFacadeApi;
import pl.edu.wszib.api.product.ProductResult;

public class ProductFacade implements ProductFacadeApi {
    private final ProductRepository productRepository;

    public ProductFacade(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResult create(final ProductApi productApi) {
        if (productRepository.existsById(productApi.id())) {
            return ProductResult.failure(
                    ProductResult.Code.ALREADY_EXISTS,
                    "Product with id = " + productApi.id() + " already exists."
            );
        }
        return ProductResult.success(productRepository.save(productApi));
    }

    @Override
    public ProductResult getById(final String id) {
        return productRepository.findById(id)
//                .map(productApi -> ProductResult.success(productApi))
                .map(ProductResult::success)
                .orElseGet(() -> ProductResult.failure(ProductResult.Code.NOT_FUND, "Product with id = " + id + " does not exists."));
    }
}
