package dev.alexengrig.ships.factory;

import dev.alexengrig.ships.domain.Goods;

public interface GoodsFactory<T> {
    Goods<T> createGoods(T value);
}
