package org.webapp.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
 
@WebServlet("/getFota")
public class UploaderServlet extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "D:/Apki Java 5 semestr/WebApplication/WebContent/profilePics";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String Iname ="";
		
		PrintWriter out = response.getWriter();
		if(ServletFileUpload.isMultipartContent(request))
		{
			try
			{
				List<FileItem> multiports = new ServletFileUpload(new DiskFileItemFactory()).parseRequest((RequestContext) request);
				
				for(FileItem item: multiports)
				{
					if(!item.isFormField())
					{
						Iname  = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY+File.separator+Iname));
					}
				}
				
				out.println("File uploaded pomyslnie </br>"+UPLOAD_DIRECTORY+File.separator+Iname);
			}
			catch(Exception e)
			{
				out.print("nie udane  z powodu"+e);
			}
		}
		else
		{
			out.println("tylko udostepnianie tutaj");
		}
	}
}