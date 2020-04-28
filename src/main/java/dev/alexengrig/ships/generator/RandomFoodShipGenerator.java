package dev.alexengrig.ships.generator;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.factory.ShipFactory;

public class RandomFoodShipGenerator extends RandomShipGenerator<Food> {
    private final int minCapacity;
    private final int maxCapacity;

    public RandomFoodShipGenerator(ShipFactory<Food> factory, int minCapacity, int maxCapacity) {
        super(factory);
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected int getMinCapacity() {
        return minCapacity;
    }

    @Override
    protected int getMaxCapacity() {
        return maxCapacity;
    }
}
