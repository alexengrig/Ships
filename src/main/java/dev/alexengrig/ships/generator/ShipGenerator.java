package dev.alexengrig.ships.generator;

import dev.alexengrig.ships.domain.Ship;

public interface ShipGenerator<T> {
    Ship<T> generate();
}
