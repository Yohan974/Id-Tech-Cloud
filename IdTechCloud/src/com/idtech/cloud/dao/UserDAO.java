package com.idtech.cloud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.idtech.cloud.User;

public class UserDAO extends DAOContext {
	
	public static User isValidLogin(String login) {
		try ( Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM User WHERE login=?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString( 1, login );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return new User(
                                resultSet.getInt( "userID" ),
                                resultSet.getString( "firstname" ),
                                resultSet.getString( "lastname" ),
                                resultSet.getString( "email" ),
                                resultSet.getString( "login" ),
                                resultSet.getString( "password" ),
                                resultSet.getString( "role" ), 
                                resultSet.getInt("customerID")
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
	
	public static void insert(User user) {
		//méthode d'insertion
				try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
					String strSql = "INSERT INTO User (firstname,lastname,email,login,password,role,customerID) VALUES (?,?,?,?,?,?,?)";
					try (PreparedStatement statement = connection.prepareStatement(strSql)) {
						statement.setString(1, user.getFirstname());
						statement.setString(2, user.getLastname());
						statement.setString(3, user.getEmail());
						statement.setString(4, user.getLogin());
						statement.setString(5, user.getPassword());
						statement.setString(6, user.getRole());
						statement.setInt(7, user.getCustomerID());
						statement.execute();
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
	}
	
	public static void update(User user) {
		//méthode de modification 
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "UPDATE User SET firstname = ?, lastname = ?, email = ?, login = ?, password = ?, role = ?, customerID = ? WHERE userID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString(1, user.getFirstname());
				statement.setString(2, user.getLastname());
				statement.setString(3, user.getEmail());
				statement.setString(4, user.getLogin());
				statement.setString(5, user.getPassword());
				statement.setString(6, user.getRole());
				statement.setInt(7, user.getCustomerID());
				statement.setInt(8, user.getUserID());
				statement.executeUpdate();	
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void delete(User user) {
		//méthode de suppression
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "DELETE FROM User WHERE userID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setInt(1, user.getUserID());
				statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static User find(int ID) {
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM User WHERE userID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setInt(1, ID);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						return new User(
								resultSet.getInt("userID"),
								resultSet.getString("firstname"),
								resultSet.getString("lastname"),
								resultSet.getString("email"),
								resultSet.getString("login"),
								resultSet.getString("password"),
								resultSet.getString("role"),
								resultSet.getInt("customerID")
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
	
	public static List<User> list(int ID) {
		//méthode de création de liste
		List<User> result = new ArrayList<User>();
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM User WHERE customerID=?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setInt(1, ID);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						User u = new User();
						u.setUserID(resultSet.getInt("userID"));
						u.setFirstname(resultSet.getString("firstname"));
						u.setLastname(resultSet.getString("lastname"));
						u.setEmail(resultSet.getString("email"));
						u.setLogin(resultSet.getString("login"));
						u.setRole(resultSet.getString("role"));
						result.add(u);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public static User isExistingLogin(String login) {
		try ( Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM User WHERE login=?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString( 1, login );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                    	return new User(
                                resultSet.getInt( "userID" ),
                                resultSet.getString( "firstname" ),
                                resultSet.getString( "lastname" ),
                                resultSet.getString( "email" ),
                                resultSet.getString( "login" ),
                                resultSet.getString( "password" ),
                                resultSet.getString( "role" ), 
                                resultSet.getInt("customerID")
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
	
	public static User isExistingEmail(String email) {
		try ( Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM User WHERE email=?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString( 1, email );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                    	return new User(
                                resultSet.getInt( "userID" ),
                                resultSet.getString( "firstname" ),
                                resultSet.getString( "lastname" ),
                                resultSet.getString( "email" ),
                                resultSet.getString( "login" ),
                                resultSet.getString( "password" ),
                                resultSet.getString( "role" ), 
                                resultSet.getInt("customerID")
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
