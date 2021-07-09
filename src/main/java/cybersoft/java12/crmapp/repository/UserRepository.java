package cybersoft.java12.crmapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;


public class UserRepository {
	
	public boolean checkUser(String email, String password) {
		boolean result = false;
			try {
				Connection connection = MySqlConnection.getConnection();
				String query = "SELECT email, password, name FROM user WHERE email = ? AND password = ?";
				
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, email);
				statement.setString(2, password);
				ResultSet resultSet = statement.executeQuery();
				result = resultSet.next();
				System.out.println(result);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Connection to database has been disconnected!");
				e.printStackTrace();
			}
			
			return result;
	}
}
