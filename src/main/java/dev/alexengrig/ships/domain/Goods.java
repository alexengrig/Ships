package dev.alexengrig.ships.domain;

import java.util.Objects;

public class Goods<T> {
    private final T value;

    public Goods(T value) {
        this.value = Objects.requireNonNull(value, "Value is null");
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods<?> goods = (Goods<?>) o;
        return value.equals(goods.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
