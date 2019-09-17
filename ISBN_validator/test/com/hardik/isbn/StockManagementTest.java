package com.hardik.isbn;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class StockManagementTest extends ValidateISBN {

	@Test
	public void testCanGetACorrectLocatorCode() {
		
		ExternalISBNDataService testWebService=new ExternalISBNDataService()
		{
			
			@Override
			public Book lookup(String isbn){
				return new Book(isbn,"of Mice and Men","J. Steinsback");
			}	
		};  
		
		ExternalISBNDataService testDatabaseService=new ExternalISBNDataService(){
			@Override
			public Book lookup(String isbn){
				return null;
			}
			
		}; 
		
		
		 
		StockManager stockManager=new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDataservice(testDatabaseService);
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4",locatorCode);
	}

	
	@Test
	public void databaseIsUsedIfDataIsPresent(){
		ExternalISBNDataService databaseService=mock(ExternalISBNDataService.class);
		ExternalISBNDataService WebService=mock(ExternalISBNDataService.class);
	
		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		StockManager stockManager=new StockManager();
		stockManager.setWebService(WebService);
		stockManager.setDataservice(databaseService);
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(databaseService).lookup("0140177396");
		verify(WebService,never()).lookup(anyString());
	}
	
	@Test
	public void databaseIsUsedIfDataIsNotPresentInDatabase(){
		ExternalISBNDataService databaseService=mock(ExternalISBNDataService.class);
		ExternalISBNDataService WebService=mock(ExternalISBNDataService.class);
	
		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(WebService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		StockManager stockManager=new StockManager();
		stockManager.setWebService(WebService);
		stockManager.setDataservice(databaseService);
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(databaseService).lookup("0140177396");
		verify(WebService).lookup("0140177396");
	}
	
	
	
	
	
	
}
