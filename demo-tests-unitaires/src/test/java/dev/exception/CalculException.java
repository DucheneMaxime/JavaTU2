package dev.exception;

public class CalculException extends Exception {

	public CalculException() {
		super();
	}

	public void toString(String expression) {
		System.out.println("L'expression " + expression + " est invalide");
	}

}
