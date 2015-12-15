package org.webapp.serwis;


public class LogRegService 
{
	
	public void sprawdzSterownik(boolean sterownik)
	{
		if(sterownik)
		{
			System.out.println("Zaladowano sterownik MySQL" );
		}
		else
		{
			System.out.println("Blad podczas ladowania sterownika" );
			System.exit(1);
		}
	}
	
	public boolean zaloguj(String userId, String password)
	{
		// and password = '"+password+"'
		
		String szukajWBazie = "SELECT * FROM uzytkownicy WHERE Login = '"+userId+"' and Haslo = '"+password+"';";
		System.out.println(szukajWBazie );
		String szukaj = "insert into uzytkownicy values ('f65fs','sdfsdf','sdfsdf','dsffds','dsfs')";
		Database baza = new Database();
		boolean wynik = baza.sprawdzUzytkownika(baza.statement, szukaj);
		System.out.print(wynik);
		if(wynik)
		{
			System.out.println("autoryzacja" );
			return true;
		}
		return false;
	}
	
	public boolean zarejestruj(String imie, String nazwisko, String login, String haslo, String email)
	{
		String rejestruj = "insert into uzytkownicy values("+imie+","+nazwisko+","+login+","+email+");";
		Database baza = new Database();
		
		if(baza.dodajUzytkownika(baza.statement, rejestruj))
		{
			return true;
		}
		return false;
	}
}
