package org.webapp.models;

import java.util.ArrayList;

import org.webapp.database.Database;

public class LogRegService 
{
	boolean sprawdz;

	public void zaloguj(String userId, String password)
	{
		ArrayList<String> ifUserExist = new ArrayList<String>();
		String findUser = "SELECT * FROM uzytkownicy WHERE Login = '"+userId+"' and Haslo = '"+password+"';";
		System.out.println(findUser );
		Database bazaSQL = new Database();
		ifUserExist = bazaSQL.pokazSpecjalistow(bazaSQL.executeQuery(bazaSQL.statement, findUser));
		
	}
	
	public boolean zarejestruj(String imie, String nazwisko, String login, String haslo, String email, String telefon, String specjalizacja, String miasto)
	{
		String rejestruj = "insert into lekarze values('"+imie+"','"+nazwisko+"','"+email+"','"+telefon+"','"+login+"','"+haslo+"','"+specjalizacja+"','"+miasto+"');";
		System.out.println(rejestruj);
		Database baza = new Database();
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
	public boolean zarejestruj(String imie, String nazwisko, String login, String haslo, String email)
	{
		String rejestruj = "insert into uzytkownicy values('"+imie+"','"+nazwisko+"','"+login+"','"+haslo+"','"+email+"');";
		System.out.println(rejestruj);
		Database baza = new Database();
		
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
}
