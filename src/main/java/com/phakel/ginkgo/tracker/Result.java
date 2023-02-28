package com.phakel.ginkgo.tracker;

import java.util.function.Function;

public sealed interface Result<LEFT, RIGHT> permits Result.Success, Result.Failure {

    // Both functions have a common result supertype
    // e.g. `T` can be a `Result<X,Y>` or a resolved type like a `String` / `Request`
    <T, L2 extends T, R2 extends T> T either(Function<LEFT, L2> left, Function<RIGHT, R2> right);

    default <T> T then(Function<Result<LEFT, RIGHT>, T> function) {
        return function.apply(this);
    }

    record Success<L, R>(L value) implements Result<L, R> {
        @Override
        public <T, L2 extends T, R2 extends T> T either(Function<L, L2> left, Function<R, R2> right) {
            return left.apply(value);
        }
    }

    record Failure<L, R>(R error) implements Result<L, R> {
        @Override
        public <T, L2 extends T, R2 extends T> T either(Function<L, L2> left, Function<R, R2> right) {
            return right.apply(error);
        }
    }
}