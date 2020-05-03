package dev.alexengrig.ships.tunnel;

import dev.alexengrig.ships.domain.Ship;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShipConcurrentQueue<T> extends LimitedShipTunnel<T> {
    private final Queue<Ship<T>> queue;
    private final Lock lock;
    private final Condition nonFull;
    private final Condition nonEmpty;

    public ShipConcurrentQueue(int limit) {
        super(limit);
        queue = new ArrayDeque<>(limit);
        lock = new ReentrantLock();
        nonFull = lock.newCondition();
        nonEmpty = lock.newCondition();
    }

    @Override
    public void put(Ship<T> ship) throws InterruptedException {
        lock.lock();
        try {
            while (limit == queue.size()) nonFull.await();
            queue.offer(ship);
            nonEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Ship<T> take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) nonEmpty.await();
            Ship<T> ship = queue.poll();
            nonFull.signal();
            return ship;
        } finally {
            lock.unlock();
        }
    }
}
