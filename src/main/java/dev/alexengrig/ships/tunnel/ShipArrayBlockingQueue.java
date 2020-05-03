package dev.alexengrig.ships.tunnel;

import dev.alexengrig.ships.domain.Ship;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ShipArrayBlockingQueue<T> extends LimitedShipTunnel<T> {
    private final BlockingQueue<Ship<T>> queue;

    public ShipArrayBlockingQueue(int limit) {
        super(limit);
        queue = new ArrayBlockingQueue<>(limit);
    }

    @Override
    public void put(Ship<T> ship) throws InterruptedException {
        queue.put(ship);
    }

    @Override
    public Ship<T> take() throws InterruptedException {
        return queue.take();
    }
}
