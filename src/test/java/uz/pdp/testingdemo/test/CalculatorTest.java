package uz.pdp.testingdemo.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void checkSumOfTwoNumbers() {
        int actualValue = calculator.add(7, 3);

        int expectedValue = 10;

        assertEquals(expectedValue, actualValue);
//        assertEquals(expectedValue, calculator.add(5, 5));
    }

    @Test
    @Disabled
    void checkSumOfTwoNums() {
        int actualValue = calculator.add(7, 3);

        int expectedValue = 10;

        assertNotEquals(expectedValue, actualValue + 1);
        assertEquals(expectedValue, calculator.add(3, 5));
    }
}