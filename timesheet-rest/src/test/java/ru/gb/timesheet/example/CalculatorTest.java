package ru.gb.timesheet.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    //JUnit (4), JUnit(5) - фреймворки для тестирования в Java
    // Mockito - библиотека для моков
    // AssertJ - библиотека для удобных асертов


    @Test
    void testSum() {
        new Calculator();
        Calculator calculator = new Calculator();
        int actual = calculator.sum(2,3);
        int expected = 5;

        assertEquals(expected, actual);

    }
}