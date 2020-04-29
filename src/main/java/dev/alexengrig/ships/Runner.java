package dev.alexengrig.ships;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.domain.Ship;
import dev.alexengrig.ships.factory.FoodFactory;
import dev.alexengrig.ships.factory.FoodShipFactory;
import dev.alexengrig.ships.factory.GoodsFactory;
import dev.alexengrig.ships.factory.ShipFactory;
import dev.alexengrig.ships.generator.*;

public class Runner {
    public static void main(String[] args) {
        ShipGenerator<Food> shipGenerator = createFullShipGenerator();
        Ship<Food> ship = shipGenerator.generate();
        System.out.println(ship);
    }

    private static ShipGenerator<Food> createFullShipGenerator() {
        ShipFactory<Food> shipFactory = new FoodShipFactory();
        ShipGenerator<Food> shipGenerator = new RandomFoodShipGenerator(shipFactory, 1, 10);
        GoodsFactory<Food> goodsFactory = new FoodFactory();
        GoodsGenerator<Food> goodsGenerator = new RandomFoodGenerator(goodsFactory);
        return new FullShipGenerator<>(shipGenerator, goodsGenerator);
    }
}
