package innlevering1;


import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Innlevering 1 – PG4100
 * Created by Anita Ilieva
 * on 16/05/16.
 */

public class CarTest {

    @Test
    public void testGetRegNumber() {
      String regNumber = "RG1111";
        Car car = new Car(regNumber);
        assertEquals(car.getRegNumber(), regNumber);
    }
}