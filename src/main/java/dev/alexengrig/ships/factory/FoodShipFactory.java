package dev.alexengrig.ships.factory;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.domain.FoodShip;
import dev.alexengrig.ships.domain.Ship;

public class FoodShipFactory implements ShipFactory<Food> {
    @Override
    public Ship<Food> createShip(int capacity) {
        return new FoodShip(capacity);
    }
}
