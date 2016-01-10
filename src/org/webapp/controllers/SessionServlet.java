package org.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/*request.setAttribute("attributeName",yourStringVAlue);
		RequestDispatcher rd = request.getRequestDispatcher("yourServletPattern");
		rd.forward(request,response);
		In your Servlet2

		String someName = (String)request.getAttribute("attributeName");*/
	}

}
