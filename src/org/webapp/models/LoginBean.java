package org.webapp.models;

import org.webapp.database.Database;

public class LoginBean 
{
	String login;
	String password;
	
	public String getLogin() 
	{
		return login;
	}
	public void setLogin(String login) 
	{
		this.login = login;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public boolean validate()
	{
		Database bazaSQL = new Database();
		if(login.equals("admin") && password.equals("admin"))
		{
			return true;
		}
		else
		{
			String SQL = "SELECT login FROM `wszyscy` WHERE login = '"+this.login+"' and haslo = '"+password+"';";
			String loginExist = bazaSQL.findUserInfo(bazaSQL.executeQuery(bazaSQL.statement, SQL),"login");
			
			if(loginExist.equals(login))
			{
				return true;
			}
			else
				return false;
		}
	}
	public String checkTypeOfUser()
	{
		Database bazaSQL = new Database();
		String SQL = "SELECT typ FROM `wszyscy` WHERE login = '"+this.login+"';";
		String type = bazaSQL.findUserInfo(bazaSQL.executeQuery(bazaSQL.statement, SQL),"typ");
		
		return type;
	}
	
}
