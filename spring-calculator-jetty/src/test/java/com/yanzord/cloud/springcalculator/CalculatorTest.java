package com.yanzord.cloud.springcalculator;

import com.yanzord.cloud.springcalculator.config.AppConfig;
import com.yanzord.cloud.springcalculator.exception.InvalidNumberException;
import com.yanzord.cloud.springcalculator.exception.InvalidOperationException;
import com.yanzord.cloud.springcalculator.service.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CalculatorTest {
    @Autowired
    Calculator calculator;

    @Test
    public void shouldDoSumTest() throws InvalidNumberException, InvalidOperationException {
        Double actual = calculator.doOperation(5.0, "sum", 5.0);
        Double expected = 10.0;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoSubTest() throws InvalidNumberException, InvalidOperationException {
        Double actual = calculator.doOperation(10.0, "sub", 5.0);
        Double expected = 5.0;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoMultiplyTest() throws InvalidNumberException, InvalidOperationException {
        Double actual = calculator.doOperation(5.0, "mult", 5.0);
        Double expected = 25.0;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoDivisionTest() throws InvalidNumberException, InvalidOperationException {
        Double actual = calculator.doOperation(10.0, "div", 2.0);
        Double expected = 5.0;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoPowTest() throws InvalidNumberException, InvalidOperationException {
        Double actual = calculator.doOperation(5.0, "pow", 2.0);
        Double expected = 25.0;

        assertEquals(expected, actual);
    }

    @Test(expected = InvalidNumberException.class)
    public void shouldNotDivideByZeroTest() throws InvalidNumberException, InvalidOperationException {
        Double result = calculator.doOperation(10.0, "div", 0.0);
    }

    @Test(expected = InvalidNumberException.class)
    public void shouldNotDoOperationWithNullValueTest() throws InvalidNumberException, InvalidOperationException {
        Double result = calculator.doOperation(null, "mult", 2.0);
    }

    @Test(expected = InvalidOperationException.class)
    public void shouldNotDoOperationIfTheOperationDoesntExistTest() throws InvalidNumberException, InvalidOperationException {
        Double result = calculator.doOperation(10.0, "multiply", 2.0);
    }

}
