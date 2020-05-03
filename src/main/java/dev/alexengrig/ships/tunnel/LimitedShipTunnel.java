package dev.alexengrig.ships.tunnel;

public abstract class LimitedShipTunnel<T> implements ShipTunnel<T> {
    protected final int limit;

    protected LimitedShipTunnel(int limit) {
        this.limit = limit;
    }
}
