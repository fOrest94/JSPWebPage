package org.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.serwis.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId, password;
		
		userId = request.getParameter("userId");
		password = request.getParameter("password");
		
		LoginService loginserwis = new LoginService();
		boolean wynik = loginserwis.autoryzacja(userId, password);
		if(wynik)
		{
			response.sendRedirect("success.jsp");
			return;
		}
		else
		{
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
