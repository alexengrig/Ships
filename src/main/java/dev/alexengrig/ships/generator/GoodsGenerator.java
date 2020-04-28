package dev.alexengrig.ships.generator;

import dev.alexengrig.ships.domain.Goods;

public interface GoodsGenerator<T> {
    Goods<T> generate();
}
