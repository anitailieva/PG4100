package innlevering1;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Innlevering 1 – PG4100
 * Created by Anita Ilieva
 * on 16/05/16.
 */

public class CustomerTest {
    private Customer customer;
    private String name = "Name";


    @Before
    public void setup(){
        customer = new Customer(name);
    }

    @Test
    public void testName() throws InterruptedException {
       assertEquals(customer.getName(), name);

    }
}
