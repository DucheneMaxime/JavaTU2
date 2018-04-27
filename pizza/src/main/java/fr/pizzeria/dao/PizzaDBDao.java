package fr.pizzeria.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Pizza;

public class PizzaDBDao implements IPizzaDao {
	private Connection myConnection;

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDBDao.class);

	public PizzaDBDao() {
		try {
			InputStream fins = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
			Properties prop = new Properties();
			prop.load(fins);
			Class.forName("org.mariadb.jdbc.Driver");
			String connection = prop.getProperty("connection");
			String user = prop.getProperty("user");
			String pwd = prop.getProperty("pwd");
			myConnection = DriverManager.getConnection(connection, user, pwd);
			myConnection.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			LOG.error("Exception raised in Constructor", e);
		} catch (IOException e) {
			LOG.error("IOException raised in Constructor", e);
		}
	}

	public List<Pizza> findAllPizzas() {
		LOG.debug("FindAllPizzas called");
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		try {
			PreparedStatement selectAllPizzas = myConnection.prepareStatement("select * from pizza");
			ResultSet resultats = selectAllPizzas.executeQuery();
			while (resultats.next()) {
				Integer id = resultats.getInt("ID");
				String code = resultats.getString("CODE");
				String libelle = resultats.getString("LIBELLE");
				double prix = resultats.getDouble("PRIX");
				Pizza toAdd = new Pizza(id, code, libelle, prix);
				pizzas.add(toAdd);
			}
			resultats.close();
		} catch (SQLException e) {
			LOG.error("Exception raised in findAllPizzas", e);
		}
		return pizzas;
	}

	public void saveNewPizza(Pizza pizza) {
		try {
			LOG.debug("saveNewPizza called");
			PreparedStatement addPizza = myConnection
					.prepareStatement("insert into pizza (CODE, LIBELLE, PRIX) values (?, ?, ?)");
			addPizza.setString(1, pizza.getCode());
			addPizza.setString(2, pizza.getLibelle());
			addPizza.setDouble(3, pizza.getPrix());
			addPizza.execute();
			addPizza.close();
		} catch (SQLException e) {
			LOG.error("Exception raised in saveNewPizza", e);
		}
	}

	public void updatePizza(String codePizza, Pizza pizza) {
		try {
			LOG.debug("updatePizza called");
			PreparedStatement updatePizza = myConnection
					.prepareStatement("UPDATE pizza SET CODE=?, LIBELLE=?, PRIX=? where CODE=?");
			updatePizza.setString(1, pizza.getCode());
			updatePizza.setString(2, pizza.getLibelle());
			updatePizza.setDouble(3, pizza.getPrix());
			updatePizza.setString(4, codePizza);
			updatePizza.execute();
			updatePizza.close();
		} catch (SQLException e) {
			LOG.error("Exception raised in updatePizza", e);
		}
	}

	public void deletePizza(String codePizza) {
		try {
			LOG.debug("deletePizza called");
			PreparedStatement deletePizza = myConnection.prepareStatement("delete from pizza where CODE=?");
			deletePizza.setString(1, codePizza);
			deletePizza.execute();
			deletePizza.close();
		} catch (SQLException e) {
			LOG.error("Exception raised in deletePizza", e);
		}
	}

	public Pizza findPizzaByCode(String codePizza) {
		try {
			LOG.debug("findPizzaByCode called");
			PreparedStatement findPizza = myConnection.prepareStatement("select * from pizza where CODE=?");
			findPizza.setString(1, codePizza);
			ResultSet resultat = findPizza.executeQuery();
			Integer id = resultat.getInt("ID");
			String code = resultat.getString("CODE");
			String libelle = resultat.getString("LIBELLE");
			double prix = resultat.getDouble("PRIX");
			findPizza.close();
			return new Pizza(id, code, libelle, prix);
		} catch (SQLException e) {
			LOG.error("Exception raised in findPizzaByCode", e);
		}
		return null;
	}

	public boolean pizzaExists(String codePizza) {
		boolean res = false;
		try {
			LOG.debug("pizzaExists called");
			PreparedStatement isPizza = myConnection.prepareStatement("select ID from pizza where CODE=?");
			isPizza.setString(1, codePizza);
			res = isPizza.execute();
			isPizza.close();
		} catch (SQLException e) {
			LOG.error("Exception raised in pizzaExists", e);
		}
		return res;
	}

	public Connection getConnection() {
		return this.myConnection;
	}

}
