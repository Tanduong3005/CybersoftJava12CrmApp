package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.User;

public class ProjectDao {
	private UserDao userDao;

	public ProjectDao() {
		userDao = new UserDao();
	}

	public List<Project> findAllProject() throws SQLException {
		List<Project> projects = new LinkedList<>();

		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT p.id as id, p.name as name, p.start_date as start_date, p.end_date as end_date, "
				+ "u.id as owner_id " + "FROM project p, user u " + "WHERE p.owner = u.id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Project project = new Project();

				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getDate("start_date"));
				project.setEndDate(resultSet.getDate("end_date"));

				int ownerId = resultSet.getInt("owner_id");
				User owner = userDao.findUserById(ownerId);

				project.setOwner(owner);

				projects.add(project);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return projects;
	}

	public Project findProjectById(int id) throws SQLException {
		Project project = null;
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT p.id as id, p.name as name, p.start_date as start_date, p.end_date as end_date, "
				+ "u.id as owner_id, u.name as owner_name " + "FROM project p, user u " + "WHERE p.owner = u.id "
				+ "AND u.id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				project = new Project();

				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getDate("start_date"));
				project.setEndDate(resultSet.getDate("end_date"));

				int ownerId = resultSet.getInt("owner_id");
				User owner = userDao.findUserById(ownerId);

				project.setOwner(owner);

			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return project;

	}
}
