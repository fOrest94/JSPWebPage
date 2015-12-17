package org.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.serwis.LogRegService;


@WebServlet(urlPatterns ={"/registerMedic", "/registerUser"})

public class RegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String userId, password, name, surname, email, specjalizacja, miasto,telefon;
		userId = request.getParameter("userId");
		password = request.getParameter("password");
		name = request.getParameter("imie");
		surname = request.getParameter("nazwisko");
		email = request.getParameter("email");
		System.out.println(userId+" "+password+"\n" );
		LogRegService loginserwis = new LogRegService();
		boolean check = false;

			if(request.getParameter("specjalizacja")!=null)
			{
				System.out.println("Wszedlem do lekarza");
				telefon = request.getParameter("telefon");
				miasto = request.getParameter("miasto");
				specjalizacja = request.getParameter("specjalizacja");
				check = loginserwis.rejestrujSpecjalistow(name, surname, userId, password, email,telefon, specjalizacja, miasto);
				if(check)
				{
					response.sendRedirect("index.jsp");
					System.out.println("sukces" );
					return;
				}
				else
				{
					System.out.println("nieudana" );
					response.sendRedirect("registerMedic.jsp");
					return;
				}
			}
			else
			{
				check = loginserwis.rejestrujPacjentow(name,surname,userId, password,email);
				System.out.println("Wszedlem do dupka");
				if(check)
				{
					response.sendRedirect("index.jsp");
					System.out.println("sukces" );
					return;
				}
				else
				{
					System.out.println("nieudana" );
					response.sendRedirect("registerUser.jsp");
					return;
				}
			}
	}
}
