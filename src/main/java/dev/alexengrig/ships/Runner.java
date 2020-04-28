package dev.alexengrig.ships;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.domain.Goods;
import dev.alexengrig.ships.domain.Ship;
import dev.alexengrig.ships.factory.FoodFactory;
import dev.alexengrig.ships.factory.FoodShipFactory;
import dev.alexengrig.ships.factory.GoodsFactory;
import dev.alexengrig.ships.factory.ShipFactory;
import dev.alexengrig.ships.generator.GoodsGenerator;
import dev.alexengrig.ships.generator.RandomFoodGenerator;
import dev.alexengrig.ships.generator.RandomFoodShipGenerator;
import dev.alexengrig.ships.generator.ShipGenerator;

public class Runner {
    public static void main(String[] args) {
        ShipFactory<Food> shipFactory = new FoodShipFactory();
        ShipGenerator<Food> shipGenerator = new RandomFoodShipGenerator(shipFactory, 1, 10);
        Ship<Food> ship = shipGenerator.generate();
        GoodsFactory<Food> goodsFactory = new FoodFactory();
        GoodsGenerator<Food> goodsGenerator = new RandomFoodGenerator(goodsFactory);
        for (int i = 0, l = ship.getCapacity(); i < l; i++) {
            Goods<Food> goods = goodsGenerator.generate();
            ship.addGoods(goods);
        }
        System.out.println(ship);
    }
}
