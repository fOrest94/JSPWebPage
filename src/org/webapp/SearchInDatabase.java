package org.webapp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.webapp.serwis.Database;


@WebServlet("/znajdzLekarza")
public class SearchInDatabase extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    Database bazaSQL = new Database();   
    ArrayList<String> listaRekordow = new ArrayList<String>();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String specjalizacja,miasto,imie,nazwisko;
		System.out.println("Wchodze poprawien do goGet");

		if(request.getParameter("imie") != null && request.getParameter("nazwisko") != null)
		{
			System.out.println("Mam imie i nazwisko");
			imie = request.getParameter("imie");
			nazwisko = request.getParameter("nazwisko");
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE imie = '"+imie+"' and nazwisko = '"+nazwisko+"';";
			listaRekordow = bazaSQL.printDataFromQuery(bazaSQL.executeQuery(bazaSQL.statement, pobierzZBazy));
			request.setAttribute("lista_ziomkow", listaRekordow);
			response.sendRedirect("/znajdzLekarza.jsp");
			
			for(int i =0;i<listaRekordow.size();i++)
			{
					System.out.println(listaRekordow.get(i));
			}
		}
		else if(request.getParameter("specjalizacja") != null && !request.getParameter("miasto").equals("wpisz"))
		{
			System.out.println("Mam specjalizacje i miasto");
			Database bazaDanych = new Database();
			specjalizacja = request.getParameter("specjalizacja");
			miasto = request.getParameter("miasto");
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE specjalizacja = '"+specjalizacja+"' and miasto = '"+miasto+"';";
			listaRekordow = bazaSQL.printDataFromQuery(bazaSQL.executeQuery(bazaSQL.statement, pobierzZBazy));
			HttpSession session = request.getSession(true);
			session.setAttribute("rekordy", listaRekordow);
			
		}
		else if(request.getParameter("specjalizacja") != null && request.getParameter("miasto").equals("wpisz"))
		{
			System.out.println("Mam specjalizacje");
			Database bazaDanych = new Database();
			specjalizacja = request.getParameter("specjalizacja");
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE specjalizacja = '"+specjalizacja+"';";
			listaRekordow = bazaSQL.printDataFromQuery(bazaSQL.executeQuery(bazaSQL.statement, pobierzZBazy));
			
			for(int i =0;i<listaRekordow.size();i++)
			{
					System.out.println(listaRekordow.get(i));
			}
			
		}
		else if(request.getParameter("specjalizacja") == null && !request.getParameter("miasto").equals("wpisz"))
		{
			System.out.println("Mam miasto");
			Database bazaDanych = new Database();
			miasto = request.getParameter("miasto");
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE miasto = '"+miasto+"';";
			listaRekordow = bazaSQL.printDataFromQuery(bazaSQL.executeQuery(bazaSQL.statement, pobierzZBazy));
			
			for(int i =0;i<listaRekordow.size();i++)
			{
					System.out.println(listaRekordow.get(i));
			}
			
		}
		else
		{
			System.out.println("Blad wyszukiwania");
			response.sendRedirect("errorJSP.jsp");
		}
		
	}

}
