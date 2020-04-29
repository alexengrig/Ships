package dev.alexengrig.ships.loader;

import dev.alexengrig.ships.domain.Goods;
import dev.alexengrig.ships.domain.Ship;
import dev.alexengrig.ships.generator.GoodsGenerator;

public class SlowShipLoader<T> implements ShipLoader<T> {
    private final GoodsGenerator<T> goodsGenerator;

    public SlowShipLoader(GoodsGenerator<T> goodsGenerator) {
        this.goodsGenerator = goodsGenerator;
    }

    @Override
    public void load(Ship<T> ship) {
        try {
            Goods<T> goods;
            long start, timeout;
            for (int i = 0, l = ship.getCapacity() - ship.getSize(); i < l; i++) {
                start = System.nanoTime();
                goods = goodsGenerator.generate();
                timeout = 1000L - ((System.nanoTime() - start) / 1_000_000L);
                if (timeout > 0) Thread.sleep(timeout);
                ship.addGoods(goods);
            }
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }
}
