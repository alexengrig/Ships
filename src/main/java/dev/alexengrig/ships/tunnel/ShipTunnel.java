package dev.alexengrig.ships.tunnel;

import dev.alexengrig.ships.domain.Ship;

public interface ShipTunnel<T> {
    void push(Ship<T> ship);

    Ship<T> pull();
}
