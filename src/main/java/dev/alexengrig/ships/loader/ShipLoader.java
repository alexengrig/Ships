package dev.alexengrig.ships.loader;

import dev.alexengrig.ships.domain.Ship;

public interface ShipLoader<T> {
    void load(Ship<T> ship);
}
