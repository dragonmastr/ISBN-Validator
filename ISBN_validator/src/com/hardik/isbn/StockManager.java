package com.hardik.isbn;

public class StockManager {

	private ExternalISBNDataService webService;
	private ExternalISBNDataService dataservice;
	

	public void setDataservice(ExternalISBNDataService dataservice) {
		this.dataservice = dataservice;
	}

	public void setWebService(ExternalISBNDataService service) {
		this.webService = service;
	}

	public String getLocatorCode(String isbn) {
		Book book=dataservice.lookup(isbn);
		if (book==null) book=webService.lookup(isbn);
		StringBuilder locator=new StringBuilder();
		locator.append(isbn.substring(isbn.length()-4));
		locator.append(book.getAuthor().substring(0,1));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();
		
	}

}

