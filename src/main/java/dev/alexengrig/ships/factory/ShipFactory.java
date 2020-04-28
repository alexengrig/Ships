package dev.alexengrig.ships.factory;

import dev.alexengrig.ships.domain.Goods;
import dev.alexengrig.ships.domain.Ship;

import java.util.List;

public interface ShipFactory<T> {
    Ship<T> createShip(int capacity);

    default Ship<T> createShip(int capacity, Goods<T> goods) {
        Ship<T> ship = createShip(capacity);
        ship.addGoods(goods);
        return ship;
    }

    default Ship<T> createShip(int capacity, List<Goods<T>> goods) {
        Ship<T> ship = createShip(capacity);
        ship.addGoods(goods);
        return ship;
    }
}
