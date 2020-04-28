package dev.alexengrig.ships.factory;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.domain.FoodBox;
import dev.alexengrig.ships.domain.Goods;

public class FoodFactory implements GoodsFactory<Food> {
    @Override
    public Goods<Food> createGoods(Food food) {
        return new FoodBox(food);
    }
}
