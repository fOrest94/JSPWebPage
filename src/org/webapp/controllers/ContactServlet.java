package org.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.models.ContactBean;


@WebServlet("/whatIsYourQuestion")
public class ContactServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ContactBean bean = new ContactBean(request.getParameter("specjalizacja"),request.getParameter("email"),request.getParameter("textArea"));
		
		if(bean.sendQuestion(bean.getType(), bean.getEmail(), bean.getOpis()))
		{
			request.setAttribute("odpowiedz", "Pytanie zostalo wystalne. Dziekujemy!");
			request.getRequestDispatcher("oAutorach.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("odpowiedz", "Cos poszlo nie tak :(");
			request.getRequestDispatcher("oAutorach.jsp").forward(request, response);
		}
	}

}
