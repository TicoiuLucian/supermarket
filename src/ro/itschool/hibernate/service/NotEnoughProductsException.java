package ro.itschool.hibernate.service;

public class NotEnoughProductsException extends Exception {

	public NotEnoughProductsException(String message) {
		super(message);
	}

}
