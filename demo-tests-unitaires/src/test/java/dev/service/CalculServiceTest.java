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
		LOG.info("�tant donn�, une instance de la classe CalculService");
		CalculService c = new CalculService();

		LOG.info("Lorsque j'�value l'addition de l'expression 1+3+4");
		int somme = c.additioner("1+3+4");

		LOG.info("Alors j'obtiens le r�sultat 8");
		assertThat(somme).isEqualTo(8);
	}

	@Test(expected = CalculException.class)
	public void testCalculInvalide() throws Exception {
		LOG.info("�tant donn�, une instance de la classe CalculService");
		CalculService c = new CalculService();

		LOG.info("Lorsque j'�value l'addition de l'expression 1+3+4");
		int somme = c.additioner("3/4");

		LOG.info("Alors j'obtiens le r�sultat 8");
		assertThat(somme).isEqualTo(8);
	}

}
