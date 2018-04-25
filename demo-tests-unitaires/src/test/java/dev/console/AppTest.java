package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class AppTest {

	private static final Logger LOGGER = LoggerFactory.getLogger("monLogger");

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private App app;
	private CalculService calculService;

	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(System.in);
		this.calculService = mock(CalculService.class);
		this.app = new App(sc, calculService);
	}

	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}

	@Test
	public void testEvaluer() throws CalculException {
		LOGGER.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de 1+34");
		String expression = "1+34";
		when(calculService.additioner(expression)).thenReturn(35);

		LOGGER.info("Lorsque la méthode évaluer est invoquée");
		this.app.evaluer(expression);

		LOGGER.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additioner(expression);

		LOGGER.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}

	@Test(expected = CalculException.class)
	public void testExpressionInvalide() throws CalculException {

		LOGGER.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de 1+34");
		String expression = "3/4";
		when(calculService.additioner(expression)).thenThrow(new CalculException());

		LOGGER.info("Lorsque la méthode évaluer est invoquée");
		this.app.evaluer(expression);

		LOGGER.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additioner(expression);

		LOGGER.info("Alors dans la console, s'affiche l'erreur");
		assertThat(systemOutRule.getLog()).contains("L'expression " + expression + " est invalide");
	}
}