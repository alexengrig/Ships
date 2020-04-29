package dev.alexengrig.ships;

import dev.alexengrig.ships.domain.Ship;

public interface Tunnel<T> {
    void push(Ship<T> ship);

    Ship<T> pull();
}
