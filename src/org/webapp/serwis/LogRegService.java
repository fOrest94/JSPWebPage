package org.webapp.serwis;


public class LogRegService 
{
	boolean sprawdz;
	/*public void sprawdzSterownik(boolean sterownik)
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
	}*/
	
	public boolean rejestrujSpecjalistow(String imie, String nazwisko, String login, String haslo, String email, String telefon, String specjalizacja, String miasto)
	{
		String rejestruj = "insert into lekarze values('"+imie+"','"+nazwisko+"','"+login+"','"+haslo+"','"+email+"','"+telefon+"','"+specjalizacja+"','"+miasto+"');";
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
	public boolean rejestrujPacjentow(String imie, String nazwisko, String login, String haslo, String email)
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
