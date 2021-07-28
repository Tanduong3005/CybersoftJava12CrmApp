package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.dto.TaskCreateDto;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.model.Task;
import cybersoft.java12.crmapp.service.TaskService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.ServletConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = ServletConst.TASK, urlPatterns = { UrlConst.TASK_DASHBOARD, UrlConst.TASK_ADD, UrlConst.TASK_UPDATE,
		UrlConst.TASK_DELETE })
public class TaskServlet extends HttpServlet {

	private TaskService service;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		service = new TaskService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			getTaskDashboard(req, resp);
			break;

		case UrlConst.TASK_ADD:
			getTaskAdd(req, resp);

			break;

		case UrlConst.TASK_UPDATE:
			getTaskUpdate(req, resp);

			break;

		case UrlConst.TASK_DELETE:
			getTaskDelete(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			getTaskDashboard(req, resp);
			break;

		case UrlConst.TASK_ADD:
			postTaskAdd(req, resp);

			break;

		case UrlConst.TASK_UPDATE:
			postTaskUpdate(req, resp);

			break;

		case UrlConst.TASK_DELETE:
			getTaskDelete(req, resp);
			break;
		default:
			break;
		}
	}

	private void getTaskDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = service.findAllTask();
		
		if(tasks != null && !tasks.isEmpty()) {
			req.setAttribute("tasks", tasks);
		}
		
		req.getRequestDispatcher(JspConst.TASK_DASHBOARD).forward(req, resp);
	}

	private void getTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.TASK_ADD).forward(req, resp);
	}

	private void getTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idToUpdate = Integer.parseInt(req.getParameter("id"));
		Task taskToUpdate = service.findTaskById(idToUpdate);
		
		req.setAttribute("tasks", taskToUpdate);
		req.getRequestDispatcher(JspConst.TASK_UPDATE).forward(req, resp);
	}

	private void getTaskDelete(HttpServletRequest req, HttpServletResponse resp) {

	}

	private void postTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		TaskCreateDto dto = extractDtoFromReq(req);

		service.addNewTask(dto);

		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
	}

	private TaskCreateDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Date start_date = (Date)req.getAttribute("start_date");
		Date end_date = (Date)req.getAttribute("end_date");
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		int statusId = Integer.parseInt(req.getParameter("statusId"));
		int projectId = Integer.parseInt(req.getParameter("projectId"));
		return new TaskCreateDto(name, description, start_date, end_date, roleId, statusId, projectId);
	
	}

	private void postTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idToUpdate = Integer.parseInt(req.getParameter("id"));
		TaskCreateDto dto = extractDtoFromReq(req);

		service.updateTask(dto, idToUpdate);

		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);

	}
}
