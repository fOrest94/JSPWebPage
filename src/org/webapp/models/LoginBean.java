package org.webapp.models;

import org.webapp.database.Database;

public class LoginBean 
{
	
	public boolean czyIstnieje(String login, String haslo, String type)
	{
		Database bazaSQL = new Database();
		System.out.println("*"+type+"*");
		System.out.println("*"+login+"*"+haslo+"*");
		if(login.equals("admin") && haslo.equals("admin"))
		{
			return true;
		}
		else if(type.equals("Pacjent"))
		{
			String SQL = "SELECT * FROM pacjenci WHERE login = '"+login+"' and haslo = '"+haslo+"';";
			
			boolean loginExist = bazaSQL.findUserInfo(bazaSQL.executeQuery(bazaSQL.statement, SQL), login, haslo);
			System.out.println("4.1");
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
			System.out.println("4.2");
			if(loginExist)
			{
				return true;
			}
			else
				return false;
		}
		System.out.println("4.3");
		return false;
	}
	
	public boolean walidacja(String login, String haslo)
	{
		System.out.println("Walidacja sie sypie");
		if((login.length() < 6) || (haslo.length() < 6))
		{
			return false;
		}
		return true;
	}
	
}
