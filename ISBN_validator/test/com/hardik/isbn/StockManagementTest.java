package com.hardik.isbn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StockManagementTest extends ValidateISBN {

	ExternalISBNDataService testWebService;
	ExternalISBNDataService testDatabaseService;
	StockManager stockManager=new StockManager();
	
	@Before
	public void setup(){
		testWebService=mock(ExternalISBNDataService.class);
		testDatabaseService=mock(ExternalISBNDataService.class);
		stockManager.setWebService(testWebService);
		stockManager.setDataservice(testDatabaseService);
		
		
	}
	
	@Test
	public void testCanGetACorrectLocatorCode() {
		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396","of Mice and Men","J. Steinsback"));
		 
		when(testDatabaseService.lookup(anyString())).thenReturn(null);
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4",locatorCode);
	}

	
	@Test
	public void databaseIsUsedIfDataIsPresent(){
		
	
		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService,never()).lookup(anyString());
	}
	
	@Test
	public void databaseIsUsedIfDataIsNotPresentInDatabase(){
		
	
		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService).lookup("0140177396");
	}
	
	
	
	
	
	
}
