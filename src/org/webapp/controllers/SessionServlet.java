package org.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.models.LoginBean;
import org.webapp.models.UserBean;

@WebServlet(urlPatterns ={"/SessionServlet", "/userProfile", "/edytujWizyte", "/typUsera"})
public class SessionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private UserBean userBean;  
    private int mode;
    private String infoMess;
    
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		mode = Integer.valueOf(request.getParameter("mode"));
		if(request.getParameter("login") != null && request.getParameter("typeOfUser") != null)
		{
			userBean = new UserBean((request.getParameter("login")), request.getParameter("typeOfUser"));
			request.setAttribute("rekordy", userBean.pokazProfil(userBean.getLogin(),userBean.getType()));
		}
		
		switch(mode)
		{
		 	case 0:
		 	{
		 		if(request.getParameter("changeSet") != null)
		 		{
			 		if(request.getParameter("changeSet").equals("zmienLogin"))
			 		{
			 			if(userBean.walidacja(request.getParameter("newLogin")))
			 			{
			 				if(!userBean.zmienLogin(userBean.getLogin(), request.getParameter("newLogin"),userBean.getType()))
			 				{
			 					request.setAttribute("rekordy", "Wystapil blad");
								request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
			 				}
			 				else
			 				{
			 					LoginBean nowaSesja = new LoginBean();
			 					response.addCookie(nowaSesja.getCookie("userBean", userBean.getLogin()));
								response.addCookie(nowaSesja.getCookie("type", userBean.getType()));
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
				request.setAttribute("typeOfUser", userBean.getType());
				if(request.getParameter("id_wizyty")!= null)
				{
					userBean.usunZBazy(request.getParameter("id_wizyty"), request.getParameter("whatToDelete"));
				}
				
				if(!userBean.getType().equals("admin"))
				{
					request.setAttribute("setWizyty",userBean.pobierzWizyty());
				}
				else if(userBean.getType().equals("admin"))
				{
					request.setAttribute("setWizyty",userBean.pobierzWszystkieWizyty());
				}
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
				break;
		 	}
			case 2:
		 	{
				request.setAttribute("label", userBean.getType());
				request.setAttribute("mode", mode);
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
			    break;
		 	}
		 	case 3:
		 	{
		 		if(request.getParameter("typSearch") != null)
		 		{
		 			System.out.println("tu wchodze");
		 			if(request.getParameter("id_usera")!= null)
					{
		 				System.out.println("tu jestem");
		 				System.out.println("tutaj to ma wartosc"+request.getParameter("typSearch"));
		 				if(!userBean.usunZBazy(request.getParameter("id_usera"), request.getParameter("typSearch")))
		 				{
		 					System.out.println("jest false");
		 					request.setAttribute("infoMess", "Nie mozna usunac tego uzytkownika. Usun najpierw wizyty tego uzytkownika z List Wizyt");
			 				request.setAttribute("mode", "3");
		 				}
		 				System.out.println("jest true");
		 				
					}
		 			
		 			if(request.getParameter("typSearch").equals("Pacjent"))
			 		{
			 			request.setAttribute("listOfUsers",userBean.wypiszPacjentow());
			 			request.setAttribute("mode",mode);
			 			request.setAttribute("typ", request.getParameter("typSearch"));
			 			request.setAttribute("typeOfUser",userBean.getType());
			 		}
			 		else if(request.getParameter("typSearch").equals("Specjalista"))
			 		{
			 			request.setAttribute("listOfUsers",userBean.wypiszSpec());
			 			request.setAttribute("mode",mode);
			 			request.setAttribute("typ", request.getParameter("typSearch"));
			 			request.setAttribute("typeOfUser",userBean.getType());
			 		}
		 		}
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
		 		break;
		 	}
		 	
		 }
	}

}
