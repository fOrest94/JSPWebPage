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


@WebServlet(urlPatterns ={"/znajdzLekarza", "/showSpecialist", "/setWizyta"})

public class ShowSpecServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ShowSpecBean bean;
	private ArrayList<String> lista;
	private int mode;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		bean = new ShowSpecBean();
		lista = new ArrayList<String>();
		
		mode = Integer.valueOf(request.getParameter("mode"));
		
		switch(mode)
		{
			case 1:
			{
				if(request.getParameter("typeOfSearch").equals("byName"))
				{
					
					bean.setImie(request.getParameter("imie"));
					bean.setNazwisko(request.getParameter("nazwisko"));
					lista = bean.getNamedRecords();
					request.setAttribute("rekordy", lista);
					request.setAttribute("label", bean.getReturnStatement());
					request.setAttribute("mode", mode);
					request.getRequestDispatcher("znajdzLekarza.jsp").forward(request, response);
					
					
				}
				else if(request.getParameter("typeOfSearch").equals("noName"))
				{
					
					bean.setMiasto(request.getParameter("miasto"));
					bean.setSpecjalizacja(request.getParameter("specjalizacja"));
					lista = bean.getNoNamedRecords();
					request.setAttribute("rekordy", lista);
					request.setAttribute("label", bean.getReturnStatement());
					request.setAttribute("mode", mode);
					request.getRequestDispatcher("znajdzLekarza.jsp").forward(request, response);
			
				}
				else
				{
					System.out.println("Blad wyszukiwania");
					response.sendRedirect("errorJSP.jsp");
				}
			}
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		mode = Integer.valueOf(request.getParameter("mode"));
		switch(mode)
		{
			/**
			 * sluzy do wyswietlania konkretnego specjalisty z bazy
			 */
			case 2:
			{
				bean = new ShowSpecBean();
				request.setAttribute("mode", mode);
				request.setAttribute("spec", bean.getSpecialist(request.getParameter("id_specjalisty")));
				request.setAttribute("id_specjalisty", request.getParameter("id_specjalisty"));
				request.getRequestDispatcher("znajdzLekarza.jsp").forward(request, response);
				break;
			}
			/**
			 * sluzy do tworzenia formularzu wizyty
			 */
			case 3:
			{	
				if(request.getParameter("login").equals("null") && request.getParameter("type").equals("null"))
				{
					request.setAttribute("mode", "2");
					request.setAttribute("rekordy","Tylko zalogowani uzytkownicy moga korzystac z opcji \" Umow Wizyte \"");
					request.getRequestDispatcher("login.jsp").forward(request, response);		
				}
				else if( request.getParameter("type").equals("Specjalista"))
				{
					request.setAttribute("mode", "2");
					request.setAttribute("rekordy","Zaloguj sie jako Pacjent aby skorzystac z opcji \" Umow Wizyte \"");
					request.getRequestDispatcher("login.jsp").forward(request, response);		
				}
				else if(request.getParameter("login") != null && request.getParameter("type") != null)
				{
					bean = new ShowSpecBean();
					request.setAttribute("mode", mode);
					request.setAttribute("placeInfo", bean.getPlaceInfo(request.getParameter("id_specjalisty")));
					request.setAttribute("PESEL", bean.getIdUser(request.getParameter("login"), request.getParameter("type")));
					request.setAttribute("id_specjalisty",request.getParameter("id_specjalisty"));
					request.setAttribute("imie", request.getParameter("imie"));
					request.setAttribute("nazwisko", request.getParameter("nazwisko"));
					request.setAttribute("miejsce", request.getParameter("miejsce"));
					request.getRequestDispatcher("znajdzLekarza.jsp").forward(request, response);		
				}
			}
			case 4:
			{
				
			}
		}
	}
	

}
