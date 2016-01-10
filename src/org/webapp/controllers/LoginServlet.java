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

import org.webapp.models.LoginBean;
import org.webapp.models.UserBean;


@WebServlet(urlPatterns ={"/login" , "/logout", "/userPanel"})//, "/znajdzLekarza.jsp" , "/index.jsp" , "/oAutorach.jsp" , "/googleMaps.jsp"})

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
		System.out.println("mode="+mode);
		switch(mode)
		{
			case 0:
			{
				
				userBean = new UserBean();
				userBean.setLogin(request.getParameter("userId"));
				userBean.setHaslo(request.getParameter("password"));
				userBean.setType(request.getParameter("typeOfUser"));
				System.out.println("1");
				bean = new LoginBean();
				waliduj = bean.walidacja(userBean.getLogin(), userBean.getHaslo());
				czyIstnieje = bean.czyIstnieje(userBean.getLogin(),userBean.getHaslo(),userBean.getType());
				System.out.println("2");
				if(waliduj)
				{
					if(czyIstnieje)
					{
						System.out.println("5");
						Cookie[] cookie = new Cookie[2];
						cookie[0] = new Cookie("userBean", userBean.getLogin());
						cookie[1] = new Cookie("passWord", userBean.getHaslo());
						cookie[0].setMaxAge(30); 
						cookie[1].setMaxAge(30);
						response.addCookie(cookie[0]);
						response.addCookie(cookie[1]);
						response.sendRedirect("index.jsp");
					}
					else
					{
						System.out.println("4");
						request.setAttribute("rekordy", "Niepoprawny login lub haslo");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}	
				}
				else
				{
					System.out.println("3");
					request.setAttribute("rekordy", "Za krotki login lub haslo");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				break;
			}
			case 1:
			{
				
				Cookie cookie = new Cookie("userBean", "");
				cookie.setMaxAge(0);
				cookie.setValue("");
				response.addCookie(cookie);
				response.sendRedirect("index.jsp");
				break;
			}
			case 2:
			{
				
				String sessionIDL = request.getParameter("userId");
				String sessionIDH = request.getParameter("password");
				System.out.println("Wszedlem do sesji i "+sessionIDL+"dsf"+sessionIDH);
			}
		}
	}

}
