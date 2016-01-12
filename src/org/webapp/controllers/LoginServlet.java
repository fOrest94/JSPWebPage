package org.webapp.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.webapp.models.LoginBean;
import org.webapp.models.UserBean;


@WebServlet(urlPatterns ={"/login" , "/logout", "/showProfile", "/ziomek", "/click1"})
		//"/znajdzLekarza.jsp" , "/index.jsp" , "/oAutorach.jsp" , "/googleMaps.jsp"})

public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private LoginBean bean;
	private UserBean userBean;
	private boolean waliduj;
	private boolean czyIstnieje;
	private int mode;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			
		mode = Integer.valueOf(request.getParameter("mode"));

		switch(mode)
		{
			case 0:
			{
				bean = new LoginBean(request.getParameter("userId"), request.getParameter("password"), request.getParameter("typeOfUser"));
				waliduj = bean.walidacja();
				czyIstnieje = bean.czyIstnieje();
				if(waliduj)
				{
					if(czyIstnieje)
					{
						response.addCookie(bean.getCookie("userBean", bean.getLogin()));
						response.addCookie(bean.getCookie("type", bean.getType()));
						response.sendRedirect("index.jsp");
					}
					else
					{
						request.setAttribute("rekordy", "Niepoprawny login lub haslo");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}	
				}
				else
				{
					request.setAttribute("rekordy", "Za krotki login lub haslo");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				break;
			}
			case 1:
			{
				response.addCookie(bean.getCookie("userBean", ""));
				response.addCookie(bean.getCookie("type", ""));
				response.sendRedirect("index.jsp");
				break;
			}
			case 2:
			{	
				userBean = new UserBean();
				userBean.setLogin(request.getParameter("login"));
				userBean.setType(request.getParameter("typeOfUser"));
				request.setAttribute("rekordy", userBean.pokazProfil(userBean.getLogin(),userBean.getType()));
				request.setAttribute("label", userBean.getType());
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
				break;
			}
			case 3:
			{	
				System.out.println("sadsadsadsad");
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
				break;
			}
			case 4:
			{
				System.out.println("Zmien hasla itp");
				request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
			}
		}
	}

}
