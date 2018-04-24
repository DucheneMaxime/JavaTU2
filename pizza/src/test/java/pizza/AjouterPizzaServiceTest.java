package pizza;

import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaServiceTest {

	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testAjoutPizza() {
		Scanner sc = new Scanner(System.in);
		systemInMock.provideLines("TEX");
		String code = sc.nextLine();
		systemInMock.provideLines("Texanne");
		String libelle = sc.nextLine();
		systemInMock.provideLines("12.50");
		double prix = Double.parseDouble(sc.nextLine());

		Pizza p = new Pizza(code, libelle, prix);
		PizzaMemDao pizzas = new PizzaMemDao();
		pizzas.saveNewPizza(p);
		assertTrue(pizzas.findAllPizzas().get(8).getCode().equals("TEX"));
	}

}
