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
		System.out.println("Wchodze do pP"+login+" "+typeOfUser);
		if(typeOfUser.equals("Pacjent"))
		{
			System.out.println("Wchodze do pPP");
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
			System.out.println("Wchodze do pPS");
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
		System.out.println("NIgdzie nie wchodze");	
		return listaDanych;
		
	}
	public void zmienLogin(String nowyLogin)
	{
		
	}
	public boolean walidujLogin(String login)
	{
		
		return true;
	}
}
