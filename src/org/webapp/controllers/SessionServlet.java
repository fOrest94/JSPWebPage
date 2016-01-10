package org.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.models.UserBean;

@WebServlet("/userPanel")
public class SessionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private UserBean userBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
