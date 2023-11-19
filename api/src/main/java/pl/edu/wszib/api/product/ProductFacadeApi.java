package pl.edu.wszib.api.product;

public interface ProductFacadeApi {
    ProductResult create(ProductApi productApi);
    ProductResult getById(String id);
}
