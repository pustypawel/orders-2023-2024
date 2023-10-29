package pl.edu.wszib.api.product;

public interface ProductFacadeApi {
    ProductApi create(ProductApi productApi);
    ProductApi getById(String id);
}
