package innlevering1;

import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Innlevering 1 – PG4100
 * Created by Anita Ilieva
 * on 16/05/16.
 */

public class CarRentalTest {
    private List<Car> cars;
    private CarRental carRental;
    private Car c = null;

    @Before
    public void setup() {
        cars = new ArrayList<>();
        cars.add(new Car("RG1111"));
        cars.add(c);
        carRental = new CarRental();
    }

    @Test
    public void testNotNull() {
        assertNotNull("Car is not null", cars.get(0));
    }

    @Test
    public void testRentCar() throws InterruptedException {
        Customer customer = mock(Customer.class);
        when(customer.getName()).thenReturn("Customer");
        carRental.rentCar(customer);
        assertEquals("Customer", customer.getName());
    }

    @Test
    public void testReturnCar() throws InterruptedException {
        Customer customer = mock(Customer.class);
        when(customer.getName()).thenReturn(("Customer"));
        carRental.rentCar(customer);
        carRental.returnCar(customer);
        assertEquals("Customer", customer.getName());
    }

}
