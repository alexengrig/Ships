package dev.alexengrig.ships.generator;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.factory.GoodsFactory;

import java.util.Random;

public class RandomFoodGenerator extends RandomGoodsGenerator<Food> {
    private final Random random;
    private final int max;

    public RandomFoodGenerator(GoodsFactory<Food> factory) {
        super(factory);
        this.random = new Random();
        this.max = Food.values().length;
    }

    @Override
    protected Food generateValue() {
        return Food.values()[random.nextInt(max)];
    }
}
