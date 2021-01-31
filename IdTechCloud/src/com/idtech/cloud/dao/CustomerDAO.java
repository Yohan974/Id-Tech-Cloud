package com.idtech.cloud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.idtech.cloud.Customer;

public class CustomerDAO extends DAOContext {
	
	public static int insert(Customer customer) {
		//méthode d'insertion
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "INSERT INTO Customer (name) VALUES (?)";
			// création d'un objet requête préparée
			try (PreparedStatement statement = connection.prepareStatement(strSql, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, customer.getName());
				statement.execute(); // exécution de la requête
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					int generatedKey = 0;
					if (resultSet.next()) {
						generatedKey = resultSet.getInt(1);
					} 
					return generatedKey;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void update(Customer customer) {
		//méthode de modification
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "UPDATE Customer SET name = ?, contactFirstname = ?, contactLastname = ?, contactCity = ?, contactAddress = ?, contactPhone = ?, contactEmail = ? WHERE customerID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString(1, customer.getName());
				statement.setString(2, customer.getContactFirstname());
				statement.setString(3, customer.getContactLastname());
				statement.setString(4, customer.getContactCity());
				statement.setString(5, customer.getContactAddress());
				statement.setString(6, customer.getContactPhone());
				statement.setString(7, customer.getContactEmail());
				statement.setInt(8, customer.getCustomerID());
				statement.executeUpdate();	
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void delete(Customer customer) {
		//méthode de suppression
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "DELETE FROM Customer WHERE customerID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setInt(1, customer.getCustomerID());
				statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Customer find(int ID) {
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM Customer WHERE customerID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setInt(1, ID);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						return new Customer(
								resultSet.getInt("customerID"),
								resultSet.getString("name"),
								resultSet.getString("contactFirstname"),
								resultSet.getString("contactLastname"),
								resultSet.getString("contactCity"),
								resultSet.getString("contactAddress"),
								resultSet.getString("contactPhone"),
								resultSet.getString("contactEmail")
						);
					} else {
						return null;
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Customer> list() {
		//méthode de création de liste
		List<Customer> result = new ArrayList<Customer>();
		//établissement de la connexion au lien JDBC
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			try (Statement statement = connection.createStatement()) {
				String strSql = "SELECT * FROM Customer";
				try (ResultSet resultSet = statement.executeQuery(strSql)) {
					while (resultSet.next()) {
						Customer c = new Customer();
						c.setCustomerID(resultSet.getInt("customerID"));
						c.setName(resultSet.getString("name"));
						result.add(c);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public static Customer isExistingName(String name) {
		try ( Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM Customer WHERE name=?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString( 1, name );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                    	return new Customer(
                                resultSet.getInt( "customerID" ),
                                resultSet.getString( "name" ),
                                resultSet.getString( "contactFirstname" ),
                                resultSet.getString( "contactLastname" ),
                                resultSet.getString( "contactCity" ),
                                resultSet.getString( "contactAddress" ),
                                resultSet.getString( "contactPhone" ),
                                resultSet.getString( "contactEmail" )
                                );
                    } else {
                    	return null;
                    }
                }
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
}
