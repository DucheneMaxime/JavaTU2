package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculService {

	private static final Logger LOGGER = LoggerFactory.getLogger("monLogger");

	public int additioner(String expression) throws CalculException {
		LOGGER.debug("Evaluation de l'expression " + expression.toString());
		// On teste par exemple si l'utilisateur rentre une division
		if (expression.contains("/"))
			throw new CalculException();
		int somme = 0;
		for (int i = 0; i < expression.length(); i++) {
			char nextChar = expression.charAt(i);
			if (nextChar != '+')
				somme += Character.getNumericValue(nextChar);
		}
		return somme;
	}

}
