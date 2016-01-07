package org.webapp.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.webapp.models.RegistryBean;
import org.webapp.models.UserBean;


@WebServlet(urlPatterns ={"/register"})

public class RegisterServlet extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	private RegistryBean bean;
	private UserBean userBean;
	private boolean walidacja;
	private boolean czyIstnieje;
	private boolean czyZarejestrowano;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		userBean = new UserBean();
		userBean.setImie(request.getParameter("imie"));
		userBean.setNazwisko(request.getParameter("nazwisko"));
		userBean.setEmail(request.getParameter("email"));
		userBean.setLogin(request.getParameter("login"));
		userBean.setHaslo(request.getParameter("password"));
		userBean.setType(request.getParameter("typeOfUser"));
		userBean.setPESEL(request.getParameter("PESEL"));
		userBean.setSpecjalizacja(request.getParameter("specjalizacja"));
		userBean.setTelefon(request.getParameter("telefon"));
		userBean.setMiasto(request.getParameter("miasto"));
		
		bean = new RegistryBean();
		
		walidacja = bean.walidacja(userBean.getLogin(), userBean.getHaslo());
		czyIstnieje = bean.czyIstnieje(userBean.getLogin(), userBean.getEmail());

			if(walidacja)
			{
				if(czyIstnieje)
				{
					czyZarejestrowano = bean.zarejestruj(userBean);
					if(czyZarejestrowano)
					{
						Cookie cookie = new Cookie("userBean", userBean.getLogin());
						cookie.setMaxAge(30); 
						response.addCookie(cookie);
						response.sendRedirect("index.jsp");
					}
					else
					{
						request.setAttribute("rekordy", "Blad podczas dodawania");
						request.getRequestDispatcher("/register.jsp").forward(request, response);
					}	
				}
				else
				{
					request.setAttribute("rekordy", "Podany login lub haslo istnieja");
					request.getRequestDispatcher("/register.jsp").forward(request, response);

				}
			}
			else
			{
				request.setAttribute("rekordy", "Za krotki login lub haslo");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
			
	}
}
