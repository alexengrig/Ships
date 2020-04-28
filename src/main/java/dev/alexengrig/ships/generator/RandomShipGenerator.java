package dev.alexengrig.ships.generator;

import dev.alexengrig.ships.domain.Ship;
import dev.alexengrig.ships.factory.ShipFactory;

import java.util.Random;

public abstract class RandomShipGenerator<T> implements ShipGenerator<T> {
    private final ShipFactory<T> factory;
    private final Random random;

    public RandomShipGenerator(ShipFactory<T> factory) {
        this.factory = factory;
        this.random = new Random();
    }

    @Override
    public Ship<T> generate() {
        int capacity = generateCapacity();
        return factory.createShip(capacity);
    }

    private int generateCapacity() {
        int min = getMinCapacity();
        int max = getMaxCapacity();
        return random.nextInt(max - min) + min;
    }

    protected abstract int getMinCapacity();

    protected abstract int getMaxCapacity();
}
