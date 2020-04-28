package dev.alexengrig.ships.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Goods<T>> getGoods() {
        return Collections.unmodifiableList(values);
    }
}
