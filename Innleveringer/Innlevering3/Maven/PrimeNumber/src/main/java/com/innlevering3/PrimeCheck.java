package com.innlevering3;

import org.apache.commons.math3.primes.Primes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

/**
 *<p>
 * A class that imports Apache Commons library and calculates if a number is a prime number or not.
 *
 * </p>
 * @author Anita Ilieva
 * created on 17.04.2016
 *
 */
public class PrimeCheck {
    private int num = 0;
    private boolean isInt = false;
    private boolean error = false;

    private static final Logger logger = LogManager.getLogger(PrimeCheck.class);

    /**
     * Constructor
     * @param inputFromUser string is converted to a number
     *
     */

    public PrimeCheck(String inputFromUser) {
        try {
            num = Integer.parseInt(inputFromUser);
            isInt = true;
        } catch (NumberFormatException e) {
            error = true;
            logger.error(e.getMessage() + " - " + Arrays.toString(e.getStackTrace()));
        }

    }

    /**
     *
     * @param number integer
     * @return a string if the number is prime or not
     */

    public String answer(int number) {
        return "You entered " + number + " and this number is "
                + ((Primes.isPrime(number)) ? "" : "not ") + "a prime number.";
    }


    /**
     *
     * @return error message if input is not valid
     */
    public String getError() {
        if (error)
            return "Input is not valid. Enter a number greater than 1.";

        return "No errors.";
    }

    /**
     *
     * @return integer
     */
    public int getNum() {
        return num;
    }


    /**
     *
     * @return true if the a number is a integer.
     */
    public boolean isInt() {
        return isInt;
    }

}
