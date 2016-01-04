package org.webapp.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.models.LogRegService;
import org.webapp.models.LoginBean;


@WebServlet(urlPatterns ={"/login"})
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String login = request.getParameter("userId");
		String password = request.getParameter("password");
		
		LoginBean bean = new LoginBean();
		bean.setLogin(login);
		bean.setPassword(password);
		
		boolean czyIstnieje = bean.validate();
		
		if(czyIstnieje)
		{
			System.out.println("Jest taki login");
			String type = bean.checkTypeOfUser();
			
			Cookie setLoginSession = new Cookie("login",login);
			Cookie setTypeSession = new Cookie("type",type);
			setLoginSession.setMaxAge(24*60*60);
			setTypeSession.setMaxAge(24*60*60);
			response.addCookie(setTypeSession);
			response.addCookie(setLoginSession);
			
		}
		else
		{
			System.out.println("Co jest kurwa");
			RequestDispatcher rd = request.getRequestDispatcher("login-failed.jsp");  
			rd.forward(request, response);  
		}
		
		
		
		
	}

}
