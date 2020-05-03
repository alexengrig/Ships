package dev.alexengrig.ships.tunnel;

import dev.alexengrig.ships.domain.Ship;

import java.util.Queue;

public abstract class LimitedShipTunnel<T> implements ShipTunnel<T> {
    protected final Queue<Ship<T>> queue;

    protected LimitedShipTunnel(int limit) {
        queue = createQueue(limit);
    }

    protected abstract Queue<Ship<T>> createQueue(int limit);

    @Override
    public void push(Ship<T> ship) {
        queue.offer(ship);
    }

    @Override
    public Ship<T> pull() {
        return queue.poll();
    }
}
