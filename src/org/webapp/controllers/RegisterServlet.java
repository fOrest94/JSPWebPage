package org.webapp.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.webapp.models.RegistryBean;


@WebServlet(urlPatterns ={"/register"})

public class RegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private RegistryBean bean;
	private boolean walidacja;
	private boolean czyIstnieje;
	private boolean typBledu;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		bean.setImie(request.getParameter("imie"));
		bean.setNazwisko(request.getParameter("nazwisko"));
		bean.setEmail(request.getParameter("email"));
		bean.setLogin(request.getParameter("login"));
		bean.setHaslo(request.getParameter("password"));
		bean.setType(request.getParameter("typeOfUser"));
		bean.setHaslo(request.getParameter("typeOfUser"));
		bean.setPESEL(request.getParameter("PESEL"));
		bean.setSpecjalizacja(request.getParameter("specjalizacja"));
		bean.setTelefon(request.getParameter("telefon"));
		bean.setMiasto(request.getParameter("miasto"));

		walidacja = bean.walidacja();
		czyIstnieje = bean.czyIstnieje();
		
			if(walidacja)
			{
				if(czyIstnieje)
				{
					HttpSession session = request.getSession(true);
					session.setAttribute("login", login);
					session.setAttribute("login", login);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/memberMain.jsp");
					
				}
			}
			else
			{
				response.sendRedirect("index.jsp");
			}
			
	}
}
