package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.util.Path;
import cybersoft.java12.crmapp.util.Url;


@WebServlet(name = "homeServlet", urlPatterns = Path.HOME)
public class HomeServlet extends HttpServlet{
	
	private static final long serialVersionUID = -5747914875620363540L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(Url.DASHBOARD).forward(req, resp);
		
	}
}
