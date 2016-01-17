package org.webapp.models;

import java.util.ArrayList;

import org.webapp.database.Database;

public class UserBean 
{
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
	private String id_specjalisty;
			
	public String getId_specjalisty() 
	{
		return id_specjalisty;
	}
	public void setId_specjalisty(String id_specjalisty) 
	{
		this.id_specjalisty = id_specjalisty;
	}
	public UserBean()
	{
		
	}
	public UserBean(String login, String type) 
	{
		this.login = login;
		this.type = type;
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
	
	public ArrayList<String> pokazProfil(String login, String typeOfUser)
	{
		Database baza = new Database();
		String sql = new String();
		ArrayList<String> listaDanych = new ArrayList<String>();
		if(typeOfUser.equals("Pacjent"))
		{
			sql = " select * from pacjenci where login = '"+login+"';";
			listaDanych = baza.pokazProfil(baza.executeQuery(baza.statement, sql), typeOfUser);
			setImie(listaDanych.get(0));
			setNazwisko(listaDanych.get(1));
			setPESEL(listaDanych.get(2));
			setEmail(listaDanych.get(3));
			setLogin(listaDanych.get(4));
			setHaslo(listaDanych.get(5));
			return listaDanych;
			
		}
		else if(typeOfUser.equals("Specjalista"))
		{
			sql = " select * from lekarze where login = '"+login+"';";
			listaDanych = baza.pokazProfil(baza.executeQuery(baza.statement, sql), typeOfUser);
			setImie(listaDanych.get(0));
			setNazwisko(listaDanych.get(1));
			setEmail(listaDanych.get(2));
			setTelefon(listaDanych.get(3));
			setLogin(listaDanych.get(4));
			setHaslo(listaDanych.get(5));
			setSpecjalizacja(listaDanych.get(6));
			setMiasto(listaDanych.get(7));
			return listaDanych;
		}	
		return listaDanych;
		
	}
	
	public boolean sprawdzHaslo(String newPass0, String newPass1)
	{
		if(newPass0.equals(newPass1))
		{
			return true;
		}
		return false;
	}
	
	public boolean ifTheSame( String newPass0, String newPass1)
	{
		if(newPass0.equals(newPass1))
		{
			return true;
		}
		return false;
	}
	
	public boolean walidacja(String insert)
	{
		if(insert.length() < 6)
		{
			return false;
		}
		return true;
	}
	
	public String zmienHaslo(String oldPass, String newPass0, String newPass1, String typeOfUser)
	{
		Database baza = new Database();
		String sql = new String();
		String sprawdz = new String();
		
		if(typeOfUser.equals("Pacjent"))
			typeOfUser = "pacjenci";
		else if(typeOfUser.equals("Specjalista"))
			typeOfUser = "lekarze";
		
		if(baza.ifCorrectPass(baza, oldPass, typeOfUser))
		{
			if(walidacja(newPass0))
			{
				if(ifTheSame(newPass0, newPass1))
				{
					sql = "UPDATE "+typeOfUser+" SET haslo = '"+newPass0+"' WHERE haslo = '"+oldPass+"';";
					if(baza.updateUserData(baza.statement, sql))
					{
						setHaslo(newPass0);
						sprawdz = "Pomyslnie zmieniono haslo";
					}
					else
					{
						sprawdz = "Hasla musza byc identyczne";
					}
				}
				else
				{
					sprawdz = "Hasla musza byc identyczne";
				}
			}
			else
			{
				sprawdz = "Nowe haslo jest za krotkie";
			}
		}
		else
		{
			sprawdz = "Wpisales niepoprawne haslo";
		}
	
		return sprawdz;	
	}
	
	public boolean zmienLogin(String oldLogin, String newLogin, String typeOfUser)
	{	
		Database baza = new Database();
		String sql = new String();
		
			if(typeOfUser.equals("Pacjent"))
			{
				sql = "UPDATE pacjenci SET login = '"+newLogin+"' WHERE login = '"+oldLogin+"';";
			}
			else if(typeOfUser.equals("Specjalista"))
			{
				sql = "UPDATE lekarze SET login = '"+newLogin+"' WHERE login = '"+oldLogin+"';";
			}
		
		if(baza.updateUserData(baza.statement, sql))
		{
			setLogin(newLogin);
			return true;
		}
		else
			return false;
	}
	
	public boolean zmienEmail(String oldEmail, String newEmail, String typeOfUser)
	{	
		Database baza = new Database();
		System.out.println("zmieniam email");
		String sql = new String();
		
			if(typeOfUser.equals("Pacjent"))
			{
				sql = "UPDATE pacjenci SET email = '"+newEmail+"' WHERE email = '"+oldEmail+"';";
			}
			else if(typeOfUser.equals("Specjalista"))
			{
				sql = "UPDATE lekarze SET email = '"+newEmail+"' WHERE email = '"+oldEmail+"';";
			}
		if(baza.updateUserData(baza.statement, sql))
		{
			setEmail(newEmail);
			return true;
		}
		else
			return false;
	}
	public ArrayList<String> pobierzWizyty()
	{
		Database baza = new Database();
		String sql = new String();
				
			if(getType().equals("Pacjent"))
			{
				sql = "SELECT * FROM `wizyty` INNER JOIN pacjenci where wizyty.PESEL '"+getPESEL()+"'";
			}
			else if(getType().equals("Specjalista"))
			{
				sql = "SELECT * FROM `wizyty` INNER JOIN lekarze where wizyty.id_specjalisty = '"+getId_specjalisty()+"'";
			}
			return baza.pokazProfil(baza.executeQuery(baza.statement, sql), getType()+"W");
	}
}
