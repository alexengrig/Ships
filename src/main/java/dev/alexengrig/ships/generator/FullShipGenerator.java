package dev.alexengrig.ships.generator;

import dev.alexengrig.ships.domain.Goods;
import dev.alexengrig.ships.domain.Ship;

public class FullShipGenerator<T> implements ShipGenerator<T> {
    private final ShipGenerator<T> shipGenerator;
    private final GoodsGenerator<T> goodsGenerator;

    public FullShipGenerator(ShipGenerator<T> shipGenerator, GoodsGenerator<T> goodsGenerator) {
        this.shipGenerator = shipGenerator;
        this.goodsGenerator = goodsGenerator;
    }

    @Override
    public Ship<T> generate() {
        Ship<T> ship = shipGenerator.generate();
        for (int i = 0, l = ship.getCapacity(); i < l; i++) {
            Goods<T> goods = goodsGenerator.generate();
            ship.addGoods(goods);
        }
        return ship;
    }
}
