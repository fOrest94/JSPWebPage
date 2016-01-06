package org.webapp.models;

import java.util.ArrayList;

import org.webapp.database.Database;

public class RegistryBean 
{
	boolean sprawdz;
	private String imie;
	private String nazwisko;
	private String email;
	private String PESEL;
	private String login;
	private String haslo;
	private String telefon;
	private String specjalizacja;
	private String miasto;
	private String type;
	

	public boolean isSprawdz() 
	{
		return sprawdz;
	}
	
	public void setSprawdz(boolean sprawdz) 
	{
		this.sprawdz = sprawdz;
	}
	
	public String getImie() 
	{
		return imie;
	}
	
	public void setImie(String imie) 
	{
		this.imie = imie;
	}
	
	public String getNazwisko() 
	{
		return nazwisko;
	}
	
	public void setNazwisko(String nazwisko) 
	{
		this.nazwisko = nazwisko;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPESEL() 
	{
		return PESEL;
	}
	
	public void setPESEL(String PESEL) 
	{
		this.PESEL = PESEL;
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
	
	public String getTelefon() 
	{
		return telefon;
	}
	
	public void setTelefon(String telefon) 
	{
		this.telefon = telefon;
	}
	
	public String getSpecjalizacja() 
	{
		return specjalizacja;
	}
	
	public void setSpecjalizacja(String specjalizacja) 
	{
		this.specjalizacja = specjalizacja;
	}
	
	public String getMiasto() 
	{
		return miasto;
	}
	
	public void setMiasto(String miasto) 
	{
		this.miasto = miasto;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public boolean zarejestruj()
	{
		Database baza = new Database();
		if(this.miasto.equals(null) && this.specjalizacja.equals(null))
		{
			String rejestruj = "insert into uzytkownicy values('"+imie+"','"+nazwisko+"','"+login+"','"+haslo+"','"+email+"','"+PESEL+"');";
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
		else if(!this.miasto.equals(null) && !this.specjalizacja.equals(null))
		{
			String rejestruj = "insert into uzytkownicy values('"+imie+"','"+nazwisko+"','"+email+"','"+telefon+"','"+login+"','"+haslo+"','"+specjalizacja+"','"+miasto+");";
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
	public boolean walidacja()
	{
		if((login.length() < 6) || (haslo.length() < 6))
		{
			return false;
		}
		return true;
	}
	public boolean czyIstnieje()
	{
		Database baza = new Database();
		ArrayList<String> lista = new ArrayList<String>(2);
		String pobierzZBazy = "SELECT * FROM `lekarze` WHERE imie = '"+login+"' and nazwisko = '"+email+"';";
		lista = baza.wypiszZBazy(baza.executeQuery(baza.statement, pobierzZBazy));
		if(lista.size() > 0)
		{
			return false;
		}
		return true;
	}
}
