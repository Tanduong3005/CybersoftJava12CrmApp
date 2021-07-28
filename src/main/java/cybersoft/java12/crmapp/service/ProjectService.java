package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.ProjectDao;
import cybersoft.java12.crmapp.model.Project;

public class ProjectService {
	private ProjectDao dao;
	
	public ProjectService() {
		dao = new ProjectDao();
	}

	public List<Project> findAllProject() {
		List<Project> projects = null;
		try {
			projects = dao.findAllProject();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

}
