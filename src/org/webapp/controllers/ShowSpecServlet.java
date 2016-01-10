package org.webapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.webapp.database.Database;
import org.webapp.models.ShowSpecBean;


@WebServlet("/znajdzLekarza")

public class ShowSpecServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ShowSpecBean bean;
	private ArrayList<String> lista;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		bean = new ShowSpecBean();
		lista = new ArrayList<String>();
		
		if(request.getParameter("typeOfSearch").equals("byName"))
		{
			
			bean.setImie(request.getParameter("imie"));
			bean.setNazwisko(request.getParameter("nazwisko"));
			lista = bean.getNamedRecords();
			request.setAttribute("rekordy", lista);
			request.setAttribute("label", bean.getByNameLabel());
			request.getRequestDispatcher("znajdzLekarza.jsp").forward(request, response);
			
			
		}
		else if(request.getParameter("typeOfSearch").equals("noName"))
		{
			
			bean.setMiasto(request.getParameter("miasto"));
			bean.setSpecjalizacja(request.getParameter("specjalizacja"));
			lista = bean.getNoNamedRecords();
			request.setAttribute("rekordy", lista);
			request.getRequestDispatcher("znajdzLekarza.jsp").forward(request, response);;
	
		}
		else
		{
			System.out.println("Blad wyszukiwania");
			response.sendRedirect("errorJSP.jsp");
		}
		
	}

}
