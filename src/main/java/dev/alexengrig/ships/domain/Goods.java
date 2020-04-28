package dev.alexengrig.ships.domain;

public class Goods<T> {
    private final T value;

    public Goods(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
