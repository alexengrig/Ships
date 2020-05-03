package dev.alexengrig.ships;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.domain.Ship;
import dev.alexengrig.ships.factory.FoodFactory;
import dev.alexengrig.ships.factory.FoodShipFactory;
import dev.alexengrig.ships.generator.RandomFoodGenerator;
import dev.alexengrig.ships.generator.RandomFoodShipGenerator;
import dev.alexengrig.ships.generator.ShipGenerator;
import dev.alexengrig.ships.loader.ShipLoader;
import dev.alexengrig.ships.loader.SlowShipLoader;
import dev.alexengrig.ships.tunnel.ShipTunnel;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        ShipGenerator<Food> producer = createGenerator();
        Ship<Food> ship = producer.generate();
        System.out.println("Before load:");
        System.out.println(ship);
        ShipTunnel<Food> tunnel = createTunnel();
        tunnel.push(ship);
        ShipLoader<Food> consumer = createLoader();
        consumer.load(tunnel.pull());
        System.out.println("After load:");
        System.out.println(ship);
    }

    private static ShipLoader<Food> createLoader() {
        return new SlowShipLoader<>(new RandomFoodGenerator(new FoodFactory()));
    }

    private static ShipTunnel<Food> createTunnel() {
        return new ShipTunnel<Food>() {
            private final List<Ship<Food>> list = new ArrayList<>();

            @Override
            public void push(Ship<Food> ship) {
                list.add(ship);
            }

            @Override
            public Ship<Food> pull() {
                return list.get(0);
            }
        };
    }

    private static ShipGenerator<Food> createGenerator() {
        return new RandomFoodShipGenerator(new FoodShipFactory(), 1, 5);
    }
}
