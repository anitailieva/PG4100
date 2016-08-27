package innlevering1;

import java.util.concurrent.Semaphore;

/**
 * Innlevering 1 – PG4100
 * Created by Anita Ilieva
 * on 28/01/16
 */

public class Customer implements Runnable {
    private String name;
    private static CarRental carRental = new CarRental();
    private  static Semaphore semaphore = new Semaphore(carRental.getCars().size(), true);


    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        //Waiting for countdownlatch of 5
        try {
            CarRentalApp.latch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Customers will aquire() a permit from the semaphore that has 5 permits. Wait if the permit is not available.
        try{
            semaphore.acquire();

        }catch (InterruptedException e){
            e.printStackTrace();
        }


        while (true) { //Infinite loop
            try {
                for (int i = 1; i <= 10; i++) {   // Sleeping for 1-10 seconds before renting a car
                    Thread.sleep((int) (Math.random() * 10000) + 1);
                    carRental.rentCar(this);


                    Thread.sleep((int) (Math.random() * 3000) + 1); // Sleeping for 1-3 seconds before returning a car
                    carRental.returnCar(this);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }finally {
                //Release the permit to the semaphore
                semaphore.release();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}