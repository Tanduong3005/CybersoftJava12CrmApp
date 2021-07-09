package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.ServletConst;
import cybersoft.java12.crmapp.util.UrlConst;




@WebServlet(name = ServletConst.AUTHSERVLET, urlPatterns = {
		UrlConst.AUTH_LOGIN,
		UrlConst.AUTH_LOGOUT,
		UrlConst.AUTH_SIGNUP,
		UrlConst.AUTH_FORGOT_PASSWORD
})
public class AuthServlet extends HttpServlet {
	private UserService service;
//	private static final long serialVersionUID = -6096425364732430148L;

	public AuthServlet() {
		service = new UserService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.AUTH_LOGIN:
			//cookies demo
			Cookie cookie = new Cookie("firstcookie", "Thisisfirstcookie");
			cookie.setMaxAge(60);
			resp.addCookie(cookie);
			
			//kiem tra cookie - email
			Cookie[] cookies = req.getCookies();
			int cookiesCount = cookies == null ? 0 : cookies.length;
			for(int i=0; i<cookiesCount; i++)
				if(cookies[i].getName().equals("email")) {
					req.setAttribute("email", cookies[i].getValue());
				}
			String status = String.valueOf(req.getSession().getAttribute("status"));
			if(!status.equals("null"))
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			else
				req.getRequestDispatcher(JspConst.AUTH_LOGIN)
					.forward(req, resp);
			
			break;
//			//session demo
//			
//			HttpSession currentSession = req.getSession();
//			currentSession.setAttribute("pingo", "Thisisthefirstsessionattribute");
//			currentSession.setMaxInactiveInterval(60*60);
//			req.getRequestDispatcher(JspConst.AUTH_LOGIN).forward(req, resp);
			
		case UrlConst.AUTH_LOGOUT:
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			break;
		case UrlConst.AUTH_SIGNUP:
			req.getRequestDispatcher(JspConst.AUTH_SIGNUP).forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.AUTH_LOGIN:
			//cookies demo
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String remember = req.getParameter("rememberUsername");
			boolean isLoginSuccessfully = true;
			boolean userLogin = service.checkUser(email, password);
			
			if(remember != null) {
				Cookie cookie = new Cookie("email", email);
				cookie.setMaxAge(60*60*24*30);
				resp.addCookie(cookie);
			}
			
			System.out.printf("Email: %s, Remember: %s\n", email, remember);
			
//			session demo
			HttpSession currentSession = req.getSession();
			String pingo = (String)currentSession.getAttribute("pingo");
			System.out.printf("Pingo: %s\n", pingo);
			
			//logic dang nhap

			
			if(email == null || password == null)
				isLoginSuccessfully = false;
			else if(!userLogin)
				isLoginSuccessfully = false;
			if(isLoginSuccessfully) {
				currentSession.setAttribute("status", "Logged in successfully");
				resp.sendRedirect(req.getContextPath()+ UrlConst.HOME);
			} else
				resp.sendRedirect(req.getContextPath()+ UrlConst.AUTH_LOGIN);
			break;
		case UrlConst.AUTH_SIGNUP:
			req.getRequestDispatcher(JspConst.AUTH_SIGNUP).forward(req, resp);
			break;
		default:
			break;
		}
	}
}
