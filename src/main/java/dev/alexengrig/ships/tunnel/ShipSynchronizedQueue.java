package dev.alexengrig.ships.tunnel;

import dev.alexengrig.ships.domain.Ship;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShipSynchronizedQueue<T> extends LimitedShipTunnel<T> {
    private final Queue<Ship<T>> queue;

    public ShipSynchronizedQueue(int limit) {
        super(limit);
        queue = new ArrayDeque<>(limit);
    }

    @Override
    public synchronized void put(Ship<T> ship) throws InterruptedException {
        while (limit == queue.size()) wait();
        queue.offer(ship);
        notifyAll();
    }

    @Override
    public synchronized Ship<T> take() throws InterruptedException {
        while (queue.isEmpty()) wait();
        Ship<T> ship = queue.poll();
        notifyAll();
        return ship;
    }
}
