package org.webapp.models;

import javax.servlet.http.Cookie;

import org.webapp.database.Database;

public class LoginBean 
{
	private String login;
	private String haslo;
	private String type ="admin";

	public LoginBean() 
	{

	}
	public LoginBean(String login, String haslo, String type) 
	{
		this.login = login;
		this.haslo = haslo;
		this.type = type;
	}

	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}

	public String getHaslo() 
	{
		return haslo;
	}

	public void setHaslo(String haslo) 
	{
		this.haslo = haslo;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public boolean czyIstnieje(String type)
	{
		Database bazaSQL = new Database();
		if(login.equals("admin") && haslo.equals("admin"))
		{
			return true;
		}
		else if(type.equals("Pacjent") || type.equals("admin"))
		{
			String SQL = "SELECT * FROM pacjenci WHERE login = '"+login+"' and haslo = '"+haslo+"';";
			boolean loginExist = bazaSQL.findUserInfo(bazaSQL.executeQuery(bazaSQL.statement, SQL), login, haslo);

			if(loginExist)
			{
				return true;
			}
			else
				return false;
		}
		else if(type.equals("Specjalista"))
		{
			String SQL = "SELECT * FROM lekarze WHERE login = '"+login+"' and haslo = '"+haslo+"';";
			boolean loginExist = bazaSQL.findUserInfo(bazaSQL.executeQuery(bazaSQL.statement, SQL), login, haslo);
			
			if(loginExist)
			{
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public boolean walidacja(String login, String haslo)
	{
		if((login.length() < 6) || (haslo.length() < 6))
		{
			return false;
		}
		return true;
	}
	
	public Cookie getCookie(String name, String var)
	{
		Cookie cookie = new Cookie(name, var);
		
		if(var.equals(""))	
			cookie.setMaxAge(0); 
		else
			cookie.setMaxAge(1200);
		
		return cookie;
	}
	
	
}
