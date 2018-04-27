package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDBDao;

public class CommitService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) {
		try {
			((PizzaDBDao) dao).getConnection().commit();
			System.out.println("Vos changements ont été commit");
		} catch (SQLException e) {
		}

	}

}
