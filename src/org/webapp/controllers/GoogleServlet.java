package org.webapp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.webapp.database.Database;


@WebServlet("/markersGoogle")
public class GoogleServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Wszedlem do markerow");
		String markery = request.getParameter("getMarkers");
		if(markery.equals("getMarkers"))
		{
			ArrayList<String> lista = new ArrayList<String>();
			String sql = "select * from markers";
			Database baza = new Database();
			lista = baza.getMarkers(baza.executeQuery(baza.statement, sql));
			int recordsSize = (lista.size()/5);
			String[][] tablica = new String[5][recordsSize];
			int licznik = 0;
			for(int i =0 ; i< recordsSize ;i++)
			{
				for(int j =0;j<5;j++)
				{
					tablica[j][i] = lista.get(licznik);
					System.out.print(tablica[j][i]+" ");
					licznik++;
				}
				System.out.println();
			}	
			request.setAttribute("zwrocMarkery", tablica);
			request.setAttribute("zwrocMarkerySize", recordsSize);
			request.getRequestDispatcher("googleMaps.jsp").forward(request, response);
		}		
	}

}
