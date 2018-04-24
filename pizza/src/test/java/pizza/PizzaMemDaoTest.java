package pizza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {

	@Before
	public void testListe() {
		PizzaMemDao pizzas = new PizzaMemDao();
		assertTrue(pizzas.findAllPizzas().get(4).getPrix() == 12.50);
	}

	@Test
	public void testSaveNewPizza() {
		Pizza p = new Pizza("TEX", "Texanne", 12.50);
		PizzaMemDao pizzas = new PizzaMemDao();
		pizzas.saveNewPizza(p);
		assertTrue(pizzas.findAllPizzas().get(8).getCode().equals("TEX"));
	}

	@Test
	public void testUpdatePizza() {
		Pizza p = new Pizza("LUX", "Pizza de riche", 152.00);
		PizzaMemDao pizzas = new PizzaMemDao();
		pizzas.updatePizza("PEP", p);
		assertEquals(pizzas.findAllPizzas().get(0).getCode(), "LUX");
		assertEquals(pizzas.findAllPizzas().get(0).getLibelle(), "Pizza de riche");
		assertTrue(pizzas.findAllPizzas().get(0).getPrix() == 152.00);
	}

	@Test
	public void testDeletePizza() {
		PizzaMemDao pizzas = new PizzaMemDao();
		pizzas.deletePizza("PEP");
		assertTrue(!pizzas.findAllPizzas().get(0).getCode().equals("PEP"));
	}

}
