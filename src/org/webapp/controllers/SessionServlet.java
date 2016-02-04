package org.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("Login to "+request.getParameter("login")+" a typ to "+request.getParameter("typeOfUser"));
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
				System.out.println("Uzytkownik : "+userBean.getType());
				request.setAttribute("typeOfUser", userBean.getType());
				if(!userBean.getType().equals("admin"))
				{
					System.out.println("kupa");
					request.setAttribute("setWizyty",userBean.pobierzWizyty());
				}
				else if(userBean.getType().equals("admin"))
				{
					System.out.println("kupa mega");
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
		 		request.setAttribute("mode", 1);
		 	/*//userBean.usunWizyte(request.get);
		 		ArrayList<String> lista = new ArrayList<String>(40);
		 		lista = (ArrayList<String>)request.getAttribute("id_wizyty");
		 		System.out.println("lista wymiar :"+lista.size());
		 		for(int i=0;i<lista.size();i++)
		 			System.out.println(lista.get(i));
		 		request.setAttribute("setWizyty",userBean.pobierzWizyty());
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);*/
		 		break;
		 	}
		 	case 4:
		 	{
		 		System.out.println("typ sercz to : "+request.getParameter("typSearch"));
		 		if(request.getParameter("typSearch") != null)
		 		{
		 			if(request.getParameter("typSearch").equals("Pacjent"))
			 		{
			 			System.out.println("wszedlem do listy");
			 			request.setAttribute("listOfUsers",userBean.wypiszPacjentow());
			 			request.setAttribute("mode",mode);
			 			request.setAttribute("typeOfUser",userBean.getType());
			 		}
			 		else if(request.getParameter("typSearch").equals("Specjalista"))
			 		{
			 			System.out.println("wszedlem do listy");
			 			request.setAttribute("listOfUsers",userBean.wypiszSpec());
			 			request.setAttribute("mode",mode);
			 			request.setAttribute("typeOfUser",userBean.getType());
			 		}
		 		}
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
		 		break;
		 	}
		 	
		 }
	}

}
