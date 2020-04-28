package dev.alexengrig.ships.domain;

import java.util.*;

public class Ship<T> {
    private final int capacity;
    private final List<Goods<T>> values;

    public Ship(int capacity) {
        this.capacity = capacity;
        this.values = new ArrayList<>(capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return values.size();
    }

    public boolean isFull() {
        return values.size() == capacity;
    }

    public boolean addGoods(Goods<T> goods) {
        if (isFull()) {
            return false;
        }
        return values.add(goods);
    }

    public boolean addGoods(List<Goods<T>> goods) {
        return values.addAll(goods);
    }

    public List<Goods<T>> getGoods() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship<?> ship = (Ship<?>) o;
        return capacity == ship.capacity &&
                values.equals(ship.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, values);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        for (Goods<T> goods : values) {
            joiner.add(goods.toString());
        }
        return String.format("\\%s/%d/%d", joiner, values.size(), capacity);
    }
}
