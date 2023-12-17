package pl.edu.wszib.api.order;

import java.util.NoSuchElementException;

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

    boolean isSuccess();

    OrderApi success();

    enum Code {
        // internal
        NOT_FOUND,

        NO_PRODUCT, // caused in external modules
        PRODUCT_ERROR
    }

    record SuccessOrderResult(OrderApi order) implements OrderResult {
        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public OrderApi success() {
            return order;
        }
    }

    record FailureOrderResult(Code code, String message) implements OrderResult {
        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public OrderApi success() {
            throw new NoSuchElementException();
        }
    }
}