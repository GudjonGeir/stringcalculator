package is.ru.stringcalculator;

import static org.junit.Assert.*;
import is.ru.stringcalculator.Calculator.NegativeNumberException;

import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() throws NegativeNumberException {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() throws NegativeNumberException {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testOneNumber2() throws NegativeNumberException {
		assertEquals(2, Calculator.add("2"));
	}

	@Test
	public void testTwoNumbers() throws NegativeNumberException {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers() throws NegativeNumberException {
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test
    public void testNewLines() throws NegativeNumberException {
    	assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    public void testDelimiter() throws NegativeNumberException {
    	assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Test
    public void testNegative1() throws NegativeNumberException {
    	try 
    	{
    		Calculator.add("-1,2");
    		fail( "Expected exception not thrown" );
		} 
		catch (NegativeNumberException ex) 
		{
			assertEquals("Negatives not allowed: -1", ex.getMessage());
		}

    }

    @Test
    public void testNegative2() throws NegativeNumberException {
    	try 
    	{
    		Calculator.add("2,-4,3,-5");
    		fail( "Expected exception not thrown" );
		} 
		catch (NegativeNumberException ex) 
		{
			assertEquals("Negatives not allowed: -4,-5", ex.getMessage());
		}
	}

	@Test
    public void testNumbersBiggerThan1000() throws NegativeNumberException {
    	assertEquals(2, Calculator.add("1001,2"));
    }

    @Test
    public void testAnyLengthDelimiter() throws NegativeNumberException {
    	assertEquals(6, Calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void testMultipleDelimiters() throws NegativeNumberException {
    	assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleDelimitersOfAnyLength() throws NegativeNumberException {
    	assertEquals(6, Calculator.add("//[***][%%*]\n1***2%%*3"));
    }


}