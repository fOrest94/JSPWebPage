package org.webapp.controllers;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/formUpload")
@MultipartConfig
(
	fileSizeThreshold = 1024 * 1024 * 10,
	maxFileSize = 1024 * 1024 * 50,
	maxRequestSize = 1024 * 1024 * 100
		
)
public class FileUpload extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "images";
	
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	System.out.println(uploadFile(request));
        
    	request.setAttribute("mode", request.getParameter("mode"));
        request.setAttribute("wpisz", request.getParameter("wpisz"));
        request.getRequestDispatcher("userPanel.jsp").forward(request, response);
    }
    private String uploadFile(HttpServletRequest request)
	{
		String fileName = "";
		try
		{
			Part filePart = request.getPart("photo");
			fileName = getFileName(filePart);
			String applicationPath = request.getServletContext().getRealPath("");
			String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
			InputStream inputStream = null;
			OutputStream outputStream = null;
			
			try
			{
				File outputFilePath = new File(basePath + fileName);
				inputStream = filePart.getInputStream();
				outputStream = new FileOutputStream(outputFilePath);
				int read = 0;
				final byte[] bytes = new byte[1024];
				while((read = inputStream.read(bytes)) != -1)
				{
					outputStream.write(bytes, 0, read);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				fileName = "";
			}
			finally
			{
				if(outputStream != null)
				{
					outputStream.close();
				}
				if(inputStream != null)
				{
					inputStream.close();
				}
			}
			
		}
		catch(Exception e)
		{
			fileName = "";
		}
		return fileName;
		
	}
	
	private String getFileName(Part part)
	{
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("***** partHeader: " + partHeader);
		for(String content : part.getHeader("content-disposition").split(";"))
		{
			if(content.trim().startsWith("filename"))
			{
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}

