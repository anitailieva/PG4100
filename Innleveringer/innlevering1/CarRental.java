package innlevering1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Innlevering 1 – PG4100
 * Created by Anita Ilieva
 * on 28/01/16
 */

public class CarRental {
    private List<Car> cars;
    private static Lock lock = new ReentrantLock();
    private static Condition waitForCar = lock.newCondition();

    //Creating a list of Car objects
    public CarRental() {
        cars = new ArrayList<>();
        cars.add(new Car("RG1111"));
        cars.add(new Car("RG2222"));
        cars.add(new Car("RG3333"));
        cars.add(new Car("RG4444"));
        cars.add(new Car("RG5555"));
    }

    //Checking if cars are available to rernt
    public void rentCar(Customer customer) throws InterruptedException {
        lock.lock();      //Acquire lock
        try {
            for (Car c : cars) {
                if (c.getRentedBy() == null) {
                    c.setRentedBy(customer);
                    System.out.println(customer.getName() + " har leid " + c.getRegNumber());
                    statusUpdate();
                    break;
                }else{
                    waitForCar.await();
                }
            }
        }finally {
            lock.unlock(); // Release lock
        }
    }

    //Going through a list of cars and checking if they have been rented if yes
    // then changing to null( returning the car)
    public void returnCar(Customer customer) {
        lock.lock();    //Acquire lock
        try {
            for (Car c : cars) {
                if (c.getRentedBy() != null && c.getRentedBy().equals(customer)) {
                    c.setRentedBy(null);
                    System.out.println(customer.getName() + " har levert " + c.getRegNumber());
                    waitForCar.signalAll();
                    statusUpdate();
                    break;
                }
            }
        }finally{
            lock.unlock();  // Release lock
        }
    }
    //Status-updates showing cars which are available or have been rented
    public void statusUpdate(){
        System.out.println("************** Status for utleiebilene *****************");
        for(Car c: cars){

            if(c.getRentedBy() == null){
                System.out.print(c.getRegNumber() + " - ledig, ");
            }else {
                System.out.print(c.getRegNumber() + " er utleid til " + c.getRentedBy().getName() + ", ");
            }
        }
        System.out.println("\n************* Status slutt ********************");
        System.out.println();
    }

    public List<Car> getCars(){
        return cars;
    }
}
