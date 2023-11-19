package pl.edu.wszib.api.product;

// TODO refactor using sealed interfaces
public record ProductResult(
        ProductApi success,
        Code code,
        String message
) {
    public enum Code {
        ALREADY_EXISTS,
        NOT_FUND,
    }

    public static ProductResult success(ProductApi productApi) {
        return new ProductResult(productApi, null, null);
    }

    public static ProductResult failure(Code code, String message) {
        return new ProductResult(null, code, message);
    }

    public boolean isSuccess() {
        return success != null;
    }

    public boolean isFailure() {
        return !isSuccess();
    }
}
