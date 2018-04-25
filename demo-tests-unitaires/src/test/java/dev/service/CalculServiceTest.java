package dev.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	@Test
	public void testAdditioner() throws CalculException {
		LOG.info("Étant donné, une instance de la classe CalculService");
		CalculService c = new CalculService();

		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = c.additioner("1+3+4");

		LOG.info("Alors j'obtiens le résultat 8");
		assertThat(somme).isEqualTo(8);
	}

	@Test(expected = CalculException.class)
	public void testCalculInvalide() {
		CalculService c = new CalculService();
		try {
			int somme = c.additioner("3/4");
		} catch (CalculException e) {
			System.out.println(e.toString());
		}
	}

}
