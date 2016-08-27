package innlevering1;

/**
 * Innlevering 1 – PG4100
 * Created by Anita Ilieva
 * on 28/01/16
 */

public class Car {
    private String regNumber;
    private Customer rentedBy;

    public Car(String regNumber){
        this.regNumber = regNumber;
    }

    public String getRegNumber(){
        return regNumber;
    }

    public Object setRentedBy(Customer customer){
        this.rentedBy = customer;
        return null;
    }

    public Customer getRentedBy(){
        return rentedBy;
    }

}
