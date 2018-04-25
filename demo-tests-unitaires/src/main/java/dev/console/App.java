package dev.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;
import dev.service.CalculServiceTest;

public class App {
	private Scanner scanner;
	private CalculService calculatrice;

	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}

	protected void afficherTitre() {
		LOG.info("**** Application Calculatrice ****");
	}

	public void demarrer() {
		afficherTitre();
	}

	protected void evaluer(String expression) throws CalculException {
		if (expression.contains("/"))
			throw new CalculException();
		int res = calculatrice.additioner(expression);
		System.out.println(expression + "=" + res);
	}
}