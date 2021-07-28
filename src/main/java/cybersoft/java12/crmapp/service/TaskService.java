package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.TaskDao;
import cybersoft.java12.crmapp.dto.TaskCreateDto;
import cybersoft.java12.crmapp.model.Task;

public class TaskService {

	private TaskDao taskDao;
	
	public TaskService() {
		taskDao = new TaskDao();
	}
	public List<Task> findAllTask() {
		List<Task> tasks = null;
		try {
			tasks = taskDao.findAllTask();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("nhu quan que");
		System.out.println(tasks.get(0).getStartDate());
		
		return tasks;
	}

	public Task findTaskById(int idToUpdate) {
		Task task = null;
		try {
			task = taskDao.findTaskById(idToUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return task;
	}

	public void addNewTask(TaskCreateDto dto) {
//		try {
//			taskDao.addNewTask();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		
	}

	public void updateTask(TaskCreateDto dto, int idToUpdate) {
//		try {
//			taskDao.updateTask();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
	}

}
