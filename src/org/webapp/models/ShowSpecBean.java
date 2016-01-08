package org.webapp.models;

import java.util.ArrayList;

import org.webapp.database.Database;

public class ShowSpecBean 
{
	private String imie;
	private String nazwisko;
	private String specjalizacja;
	private String miasto;
	private ArrayList<String> lista;
	
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
	
	public ArrayList<String> getNamedRecords()
	{
		System.out.println("2.0");
		if(this.imie.equals("") && !this.nazwisko.equals(""))
		{
			System.out.println("2.1");
			Database baza = new Database();
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE nazwisko = '"+this.nazwisko+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.imie.equals("") && this.nazwisko.equals(""))
		{
			System.out.println("2.2");
			Database baza = new Database();
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE imie = '"+this.imie+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.imie.equals("") && !this.nazwisko.equals(""))
		{
			System.out.println("2.3");
			Database baza = new Database();
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE imie = '"+this.imie+"' and nazwisko = '"+this.nazwisko+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		return this.lista;
	}
	public ArrayList<String> getNoNamedRecords()
	{
		System.out.println("2.0");
		if(this.miasto.equals("") && this.specjalizacja != null)
		{
			System.out.println("2.1");
			Database baza = new Database();
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE specjalizacja = '"+this.specjalizacja+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.miasto.equals("") && this.specjalizacja == null)
		{
			System.out.println("2.2");
			Database baza = new Database();
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE miasto = '"+this.miasto+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.miasto.equals("") && this.specjalizacja != null)
		{
			System.out.println("2.3");
			Database baza = new Database();
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE miasto = '"+this.miasto+"' and specjalizacja = '"+this.specjalizacja+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		return this.lista;
	}
}
