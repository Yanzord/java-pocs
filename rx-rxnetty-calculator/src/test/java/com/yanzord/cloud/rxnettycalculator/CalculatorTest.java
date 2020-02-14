package com.yanzord.cloud.rxnettycalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yanzord.cloud.rxnettycalculator.config.CalculatorModule;
import com.yanzord.cloud.rxnettycalculator.exception.InvalidNumberException;
import com.yanzord.cloud.rxnettycalculator.exception.InvalidOperationException;
import com.yanzord.cloud.rxnettycalculator.service.Calculator;

public class CalculatorTest {

	private Injector injector = Guice.createInjector(new CalculatorModule());
	private Calculator calculator = injector.getInstance(Calculator.class);

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
