package org.webapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.models.UserBean;

@WebServlet(urlPatterns ={"/SessionServlet", "/userProfile" })
public class SessionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private UserBean userBean;  
    private int mode;
    private boolean sprawdz;
    private String infoMess;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		mode = Integer.valueOf(request.getParameter("mode"));
		if(request.getParameter("login") != null && request.getParameter("typeOfUser") != null)
		{
		userBean = new UserBean((request.getParameter("login")), request.getParameter("typeOfUser"));
		request.setAttribute("rekordy", userBean.pokazProfil(userBean.getLogin(),userBean.getType()));
		}
		System.out.println("UserBean [imie=" + userBean.getImie() + ", nazwisko=" + userBean.getNazwisko() + ", email=" + userBean.getEmail() + ", PESEL=" + userBean.getPESEL()
				+ ", login=" + userBean.getLogin() + ", haslo=" + userBean.getHaslo() + ", telefon=" + userBean.getTelefon() + ", specjalizacja=" + userBean.getSpecjalizacja()
				+ ", miasto=" + userBean.getMiasto() + ", type=" + userBean.getType() + "]");
		switch(mode)
		{
		 	case 0:
		 	{
		 		if(request.getParameter("changeSet") != null)
		 		{
			 		if(request.getParameter("changeSet").equals("zmienLogin"))
			 		{
			 			System.out.println("Zmieniam login");
			 			if(userBean.walidacja(request.getParameter("newLogin")))
			 			{
			 				if(userBean.zmienLogin(userBean.getLogin(), request.getParameter("newLogin"),userBean.getType()))
				 			{
				 				
				 			}
			 				else
			 				{
			 					request.setAttribute("rekordy", "Wystapil blad");
								request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
			 				}
			 			}
			 			else
			 			{
			 				request.setAttribute("rekordy", "Login jest za krotki");
							request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
			 			}
			 			
			 		}
			 		else if(request.getParameter("changeSet").equals("zmienHaslo"))
			 		{
			 			infoMess = userBean.zmienHaslo(request.getParameter("oldPass"), request.getParameter("newPass0"), request.getParameter("newPass1"), userBean.getType());
				 		
			 				request.setAttribute("infoMess", infoMess);
			 				request.setAttribute("mode", "0");
							request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
			 		}
			 		else if(request.getParameter("changeSet").equals("zmienEmail"))
			 		{
			 			userBean.zmienEmail(userBean.getEmail(), request.getParameter("newEmail"), userBean.getType());
			 		}
		 		}
		 		request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
				break;
		 	}
		 	case 1:
			{
				request.setAttribute("mode", mode);
				request.setAttribute("setWizyty",userBean.pobierzWizyty());
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
				break;
		 	}
		 	case 2:
		 	{
		 		System.out.println("Jestem w 2!");
				request.setAttribute("label", userBean.getType());
				request.setAttribute("mode", mode);
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
			    break;
		 	}	
		 	
		 }
	}

}
