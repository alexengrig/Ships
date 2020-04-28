package dev.alexengrig.ships.generator;

import dev.alexengrig.ships.domain.Goods;
import dev.alexengrig.ships.factory.GoodsFactory;

public abstract class RandomGoodsGenerator<T> implements GoodsGenerator<T> {
    private final GoodsFactory<T> factory;

    public RandomGoodsGenerator(GoodsFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public Goods<T> generate() {
        T value = generateValue();
        return factory.createGoods(value);
    }

    protected abstract T generateValue();
}
