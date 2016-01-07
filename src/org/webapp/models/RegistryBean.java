package org.webapp.models;

import java.util.ArrayList;

import org.webapp.database.Database;

public class RegistryBean 
{
	private boolean sprawdz;
	
	public boolean zarejestruj(UserBean userBean)
	{
		Database baza = new Database();
		if(userBean.getType().equals("pacjent"))
		{
			String rejestruj = "insert into pacjenci values('"+userBean.getImie()+"','"+userBean.getNazwisko()+"','"+userBean.getPESEL()+"','"+userBean.getEmail()+"','"+userBean.getLogin()+"','"+userBean.getHaslo()+"');";
			System.out.println(rejestruj);
			
			sprawdz = baza.dodajUzytkownika(baza.statement, rejestruj);
			if(sprawdz)
			{
				return true;
			}
			else
			{
				System.out.println("rejestrujPacjentow false");
				return false;
			}
		}
		else if(userBean.getType().equals("specjalista"))
		{
			String rejestruj = "insert into lekarze values('"+userBean.getImie()+"','"+userBean.getNazwisko()+"','"+userBean.getEmail()+"','"+userBean.getTelefon()+"','"+userBean.getLogin()+"','"+userBean.getHaslo()+"','"+userBean.getSpecjalizacja()+"','"+userBean.getMiasto()+");";
			System.out.println(rejestruj);
			
			sprawdz = baza.dodajUzytkownika(baza.statement, rejestruj);
			if(sprawdz)
			{
				return true;
			}
			else
			{
				System.out.println("rejestrujSpecjalistow false");
				return false;
			}
		}
		else
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
	
	public boolean czyIstnieje(String login, String email)
	{
		Database baza = new Database();
		boolean wynik = false;
		String pobierzZBazy = "SELECT * FROM `pacjenci` WHERE login = '"+login+"' or email = '"+email+"';";
		wynik = baza.ifUserExist(baza.executeQuery(baza.statement, pobierzZBazy), login, email);
		
		if(wynik)
		{
			return true;
		}
		return false;
	}
}
