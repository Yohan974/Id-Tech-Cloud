package com.idtech.cloud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.idtech.cloud.Peripheral;

public class PeripheralDAO extends DAOContext {
	public static void insert(Peripheral peripheral) {
		//méthode d'insertion
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "INSERT INTO Peripheral (peripheralID, type, name, ipAddress, operatingSystem, directoryPath, userID) VALUES (?,?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString(1, peripheral.getPeripheralID());
				statement.setString(2, peripheral.getType());
				statement.setString(3, peripheral.getName());
				statement.setString(4, peripheral.getIpAddress());
				statement.setString(5, peripheral.getOperatingSystem());
				statement.setString(6, peripheral.getDirectoryPath());
				statement.setInt(7, peripheral.getUserID());
				statement.execute();
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void delete(Peripheral peripheral) {
		//méthode de suppression
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "DELETE FROM Peripheral WHERE peripheralID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString(1, peripheral.getPeripheralID());
				statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Peripheral find(String ID) {
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM Peripheral WHERE peripheralID = ?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setString(1, ID);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						return new Peripheral(
								resultSet.getString("peripheralID"),
								resultSet.getString("type"),
								resultSet.getString("name"),
								resultSet.getString("ipAddress"),
								resultSet.getString("operatingSystem"),
                                resultSet.getString("directoryPath"),
								resultSet.getInt("userID")
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
	
	public static List<Peripheral> list(int ID) {
		//méthode de création de liste
		List<Peripheral> result = new ArrayList<Peripheral>();
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String strSql = "SELECT * FROM Peripheral WHERE userID=?";
			try (PreparedStatement statement = connection.prepareStatement(strSql)) {
				statement.setInt(1, ID);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						Peripheral p = new Peripheral();
						p.setPeripheralID(resultSet.getString("peripheralID"));
						p.setType(resultSet.getString("type"));
						p.setName(resultSet.getString("name"));
						p.setIpAddress(resultSet.getString("ipAddress"));
						p.setOperatingSystem(resultSet.getString("operatingSystem"));
						p.setUserID(resultSet.getInt("userID"));
						result.add(p);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
