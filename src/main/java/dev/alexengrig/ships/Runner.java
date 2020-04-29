package dev.alexengrig.ships;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.domain.Ship;
import dev.alexengrig.ships.factory.FoodFactory;
import dev.alexengrig.ships.factory.FoodShipFactory;
import dev.alexengrig.ships.generator.RandomFoodGenerator;
import dev.alexengrig.ships.generator.RandomFoodShipGenerator;
import dev.alexengrig.ships.generator.ShipGenerator;
import dev.alexengrig.ships.loader.SlowShipLoader;

public class Runner {
    public static void main(String[] args) {
        ShipGenerator<Food> producer = createGenerator();
        Ship<Food> ship = producer.generate();
        System.out.println("Before load:");
        System.out.println(ship);
        SlowShipLoader<Food> consumer = createLoader();
        consumer.load(ship);
        System.out.println("After load:");
        System.out.println(ship);
    }

    public static SlowShipLoader<Food> createLoader() {
        return new SlowShipLoader<>(new RandomFoodGenerator(new FoodFactory()));
    }

    private static ShipGenerator<Food> createGenerator() {
        return new RandomFoodShipGenerator(new FoodShipFactory(), 1, 5);
    }
}
