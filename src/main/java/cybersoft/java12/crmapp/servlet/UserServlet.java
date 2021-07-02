package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.util.Path;
import cybersoft.java12.crmapp.util.Url;


@WebServlet(name = "userServlet", urlPatterns = {Path.USER_PROFILE})
public class UserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case Path.USER_PROFILE:
			req.getRequestDispatcher(Url.USER_PROFILE).forward(req, resp);
			break;

		default:
			break;
		}
	}
}
