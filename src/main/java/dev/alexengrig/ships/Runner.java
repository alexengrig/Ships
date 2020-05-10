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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started");
        int count = 5;
        int producerCount = 2;
        int consumerCount = 2;
        int workerCount = producerCount + consumerCount;
        CountDownLatch latch = new CountDownLatch(workerCount);
        AtomicInteger generatedCount = new AtomicInteger();
        AtomicInteger loadedCount = new AtomicInteger();

        ShipTunnel<Food> queue = new ShipArrayBlockingQueue<>(workerCount / 2);
        Runnable producer = () -> {
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException ignore) {
            }
            long threadId = Thread.currentThread().getId();
            System.out.println(threadId + " Generator started");
            ShipGenerator<Food> generator = new RandomFoodShipGenerator(new FoodShipFactory(), 1, 2);
            Ship<Food> ship;
            while (generatedCount.getAndIncrement() < count) {
                ship = generator.generate();
                try {
                    queue.put(ship);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println(threadId + " Generated: " + ship);
            }
            System.out.println(threadId + " Generator finished");
        };
        Runnable consumer = () -> {
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException ignore) {
            }
            long threadId = Thread.currentThread().getId();
            System.out.println(threadId + " Loader started");
            ShipLoader<Food> loader = new SlowShipLoader<>(new RandomFoodGenerator(new FoodFactory()));
            Ship<Food> ship;
            while (loadedCount.getAndIncrement() < count) {
                try {
                    ship = queue.take();
                } catch (InterruptedException e) {
                    return;
                }
                loader.load(ship);
                System.out.println(threadId + " Loaded: " + ship);
            }
            System.out.println(threadId + " Loader finished");
        };

        ExecutorService executorService = Executors.newFixedThreadPool(workerCount);

        for (int i = 0; i < producerCount; i++) {
            executorService.execute(producer);
        }
        for (int i = 0; i < consumerCount; i++) {
            executorService.execute(consumer);
        }

        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException ignore) {
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("Finished");
    }
}
