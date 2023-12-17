package pl.edu.wszib.api.order;

public sealed interface OrderResult
        permits OrderResult.SuccessOrderResult, OrderResult.FailureOrderResult {

    static OrderResult success(final OrderApi order) {
        return new SuccessOrderResult(order);
    }

    static OrderResult failure(
            final Code code,
            final String message) {
        return new FailureOrderResult(code, message);
    }

    enum Code {
        NOT_FOUND
    }

    record SuccessOrderResult(OrderApi order) implements OrderResult {
        public SuccessOrderResult(OrderApi order) {
            this.order = order;
        }
    }

    record FailureOrderResult(Code code, String message) implements OrderResult {

    }
}