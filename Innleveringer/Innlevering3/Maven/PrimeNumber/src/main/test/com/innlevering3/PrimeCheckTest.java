package com.innlevering3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 *     A test suite for PrimeCheck
 * </p>
 * @author Anita Ilieva
 * Created on 17.04.2016
 */
public class PrimeCheckTest {

    /**
     * Checks if input is valid or not
     * @throws Exception exception
     */
    @Test
    public void testGetError() throws Exception {
        PrimeCheck p1 = new PrimeCheck("aaa");
        assertEquals("Input is not valid. Enter a number greater than 1.", p1.getError());

        PrimeCheck p2 = new PrimeCheck("24");
        assertEquals("No errors.", p2.getError());

    }

    /**
     * Checks if an integer has been returned
     * @throws Exception exception
     */
    @Test
    public void testGetNum() throws Exception {
    PrimeCheck p3 = new PrimeCheck("13");
        assertEquals(13, p3.getNum());
    }


    /**
     * Checks if input is an integer
     * @throws Exception exception
     */
    @Test
    public void testIsInt() throws Exception {
    PrimeCheck p3 = new PrimeCheck("7");
        assertTrue(String.valueOf(7), p3.isInt());
    }

    /**
     * Checks if correct answer has been sent to the client
     * @throws Exception exception
     */
    @Test
    public void testAnswer() throws Exception{
        PrimeCheck p4 = new PrimeCheck("22");
        assertEquals("You entered 22 and this number is not a prime number.", p4.answer(22));

    }
}