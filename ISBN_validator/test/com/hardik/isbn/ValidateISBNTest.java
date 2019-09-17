package com.hardik.isbn;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkAValidISBN() {
		ValidateISBN validator=new  ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue("first value",result);
		result=validator.checkISBN("0140177396");
		assertTrue("second value",result);
	}
	
	@Test
	public void CheckInvalid13digitnumber(){
		ValidateISBN validator=new  ValidateISBN();
		boolean result = validator.checkISBN("9781853260088");
		assertFalse(result);
	}
	
	@Test
	public void ISBNNumberEndingInAnXareValid(){
		ValidateISBN validator=new  ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}
	
	@Test
	public void CheckAValid13DigitISBN(){
		ValidateISBN validator = new ValidateISBN();
		boolean result=validator.checkISBN("9781853260087");
		assertTrue("first value",result); 
		 result=validator.checkISBN("9781853267338");
		assertTrue("second value", result);
		
	}

	@Test
	public void checkAnInvalidValidISBN() {
		ValidateISBN validator=new  ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test(expected = NumberFormatException.class)
	public void nineDigitISBNAreNotAllowed(){
		ValidateISBN validator=new ValidateISBN();
		validator.checkISBN("123456789");
	}
	
	@Test(expected = NumberFormatException.class)
	public void CheckFornumber(){
		ValidateISBN validator=new ValidateISBN();
		validator.checkISBN("HelloWorld");
	}
	
}
