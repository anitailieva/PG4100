package innlevering1;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Innlevering 1 – PG4100
 * Created by Anita Ilieva
 * on 28/01/16.
 */
public class CarRentalApp {
    private static CarRental carRental = new CarRental();
    public static CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) {
        carRental.statusUpdate();

        //Creating 10 threads/customers using user input
        try (Scanner in = new Scanner(System.in)) {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 9; i++) {
                executor.execute(new Customer(in.nextLine()));
                latch.countDown();
            }
            executor.shutdown();
            in.close();
        }
    }
}
