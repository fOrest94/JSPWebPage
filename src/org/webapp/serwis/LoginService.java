package org.webapp.serwis;

public class LoginService 
{
	public boolean autoryzacja(String userId, String password)
	{
		if(password == null || password.trim() == "")
		{
			return false;
		}
		return true;
	}
}
