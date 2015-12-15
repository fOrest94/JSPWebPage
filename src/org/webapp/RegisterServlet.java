package org.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.serwis.LogRegService;


@WebServlet("/login")
public class RegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String userId, password, name, surname, email;
		userId = request.getParameter("userId");
		password = request.getParameter("password");
		name = request.getParameter("imie");
		surname = request.getParameter("nazwisko");
		email = request.getParameter("email");
		System.out.println(userId+" "+password+"\n" );
		LogRegService loginserwis = new LogRegService();
		
		
		if(loginserwis.zarejestruj(name,surname,userId, password,email))
		{
			response.sendRedirect("index.jsp");
			System.out.println("sukces" );
			return;
		}
		else
		{
			response.sendRedirect("register.jsp");
			
			return;
		}
	}

}
