package com.hardik.isbn;

public class ValidateISBN {

	private static final int validationForLongISBN = 10;
	private static final int validationForShortISBN = 11;
	private static final int short_ISBN_number = 10;
	private static final int Long_isbn_number = 13;

	public boolean checkISBN(String isbn) {
		if (isbn.length()==Long_isbn_number) {
			return validateLongISBN(isbn);
		}
		else if(isbn.length()==short_ISBN_number){return validateShortISBN(isbn);}
	
		throw new NumberFormatException("ISBN no. must be 10 or 13 digits long");
		
		

}

	private boolean validateShortISBN(String isbn) {
		int total=0;
		for(int i=0;i<short_ISBN_number;i++){
			if(!Character.isDigit(isbn.charAt(i))) {
				if(i==9 && isbn.charAt(i)=='X')
				{
					total+=10;
				}
			else{
				throw new NumberFormatException("ISBN number can't be a Text");
			}
			}
			else{
			total+=Character.getNumericValue(isbn.charAt(i))*(short_ISBN_number-i); 
		}}
	return (total%validationForShortISBN==0);
	
	}

	private boolean validateLongISBN(String isbn) {
		int total=0;
		for(int i=0;i<Long_isbn_number;i++){
			if(i%2==0){
				total+=Character.getNumericValue(isbn.charAt(i));	
			}
			else{
				total+=Character.getNumericValue(isbn.charAt(i))*3;
			}
			
		}
		return (total%validationForLongISBN==0);
		
	}
}