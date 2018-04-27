package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDBDao;

public class RollbackService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) {
		try {
			((PizzaDBDao) dao).getConnection().rollback();
			System.out.println("Vos derniers changements ont été annulé");
		} catch (SQLException e) {
		}

	}

}
