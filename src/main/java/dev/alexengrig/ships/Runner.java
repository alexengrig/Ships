package dev.alexengrig.ships;

import dev.alexengrig.ships.domain.Food;
import dev.alexengrig.ships.domain.Ship;
import dev.alexengrig.ships.factory.FoodFactory;
import dev.alexengrig.ships.factory.FoodShipFactory;
import dev.alexengrig.ships.generator.RandomFoodGenerator;
import dev.alexengrig.ships.generator.RandomFoodShipGenerator;
import dev.alexengrig.ships.generator.ShipGenerator;
import dev.alexengrig.ships.loader.ShipLoader;
import dev.alexengrig.ships.loader.SlowShipLoader;
import dev.alexengrig.ships.tunnel.ShipArrayBlockingQueue;
import dev.alexengrig.ships.tunnel.ShipTunnel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int shipCount = 10;
        ShipTunnel<Food> tunnel = new ShipArrayBlockingQueue<>(1);

        ShipGenerator<Food> generator = new RandomFoodShipGenerator(new FoodShipFactory(), 1, 2);
        executorService.execute(() -> {
            System.out.println("Generator started");
            Ship<Food> ship;
            for (int i = 0; i < shipCount; i++) {
                ship = generator.generate();
                try {
                    tunnel.put(ship);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("Generated: " + ship);
            }
            System.out.println("Generator finished");
        });

        ShipLoader<Food> loader = new SlowShipLoader<>(new RandomFoodGenerator(new FoodFactory()));
        executorService.execute(() -> {
            System.out.println("Loader started");
            Ship<Food> ship;
            for (int i = 0; i < shipCount; i++) {
                try {
                    ship = tunnel.take();
                } catch (InterruptedException e) {
                    return;
                }
                loader.load(ship);
                System.out.println("Loaded: " + ship);
            }
            System.out.println("Loader finished");
        });

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("Finished");
    }
}
