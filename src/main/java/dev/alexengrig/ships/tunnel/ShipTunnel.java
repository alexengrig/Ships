package dev.alexengrig.ships.tunnel;

import dev.alexengrig.ships.domain.Ship;

public interface ShipTunnel<T> {
    void put(Ship<T> ship) throws InterruptedException;

    Ship<T> take() throws InterruptedException;
}
