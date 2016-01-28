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
	private String returnStatement;
	private String type = "ShowSpecProfile";
	
	public ShowSpecBean()
	{
		
	}
	public ShowSpecBean(String imie, String nazwisko, String specjalizacja, String miasto)
	{
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.specjalizacja = specjalizacja;
		this.miasto = miasto;
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
	
	public String getReturnStatement() 
	{
		return "Wyniki dla: "+returnStatement;
	}

	public void setReturnStatement(String returnStatement) 
	{
		this.returnStatement = returnStatement;
	}

	public ArrayList<String> getNamedRecords()
	{
		Database baza = new Database();
		if(this.imie.equals("") && !this.nazwisko.equals(""))
		{
			setReturnStatement(this.nazwisko);
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE nazwisko = '"+this.nazwisko+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.imie.equals("") && this.nazwisko.equals(""))
		{
			setReturnStatement(this.imie);
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE imie = '"+this.imie+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.imie.equals("") && !this.nazwisko.equals(""))
		{
			setReturnStatement(this.imie+" "+this.nazwisko);
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE imie = '"+this.imie+"' and nazwisko = '"+this.nazwisko+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		return this.lista;
	}
	public ArrayList<String> getNoNamedRecords()
	{
		Database baza = new Database();
		if(this.miasto.equals("") && this.specjalizacja != null)
		{
			setReturnStatement(this.specjalizacja);
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE specjalizacja = '"+this.specjalizacja+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.miasto.equals("") && this.specjalizacja == null)
		{
			setReturnStatement(this.miasto);
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE miasto = '"+this.miasto+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		else if(!this.miasto.equals("") && this.specjalizacja != null)
		{
			setReturnStatement(this.specjalizacja+", "+this.miasto);
			String pobierzZBazy = "SELECT * FROM `lekarze` WHERE miasto = '"+this.miasto+"' and specjalizacja = '"+this.specjalizacja+"';";
			this.lista = baza.pokazSpecjalistow(baza.executeQuery(baza.statement, pobierzZBazy));
		}
		return this.lista;
	}
	
	public ArrayList<String> getSpecialist(String id)
	{
		Database baza = new Database();
		String sql = "SELECT * FROM `lekarze` WHERE id_specjalisty = '"+id+"'";
		
		return baza.pokazProfil(baza.executeQuery(baza.statement, sql), type);
	}
	
	public String getIdUser(String login, String type)
	{
		String sql = "SELECT PESEL FROM pacjenci WHERE login = '"+login+"';";
		Database baza = new Database();
	
				System.out.println("kurwa pokaz to :"+baza.pokazProfil(baza.executeQuery(baza.statement,sql), type+"PESEL").get(0));
				return baza.pokazProfil(baza.executeQuery(baza.statement,sql), type+"PESEL").get(0);
	}
	
	public ArrayList<String> getPlaceInfo(String id_uzytkownika)
	{
		Database baza = new Database();
		String sql = "select id_placowki from lekarze where id_specjalisty = '"+id_uzytkownika+"';";
		String sql0 = "SELECT placowki.nazwa, placowki.miasto, placowki.ulica from placowki where placowki.id_placowki = "
				+ "'"+baza.pokazProfil(baza.executeQuery(baza.statement, sql),"SpecjalistaId_Placowki").get(0)+"';";
		return baza.pokazProfil(baza.executeQuery(baza.statement, sql0),"ShowPlaceInfo");
	}
	
	public boolean zapiszDoBazy(String PESEL,int id_specjalisty, String typ_wizyty, String miejsce_wizyty, String termin_wizyty, String dolegliwosci)
	{
		Database baza = new Database();
		boolean sprawdz;
		String sql = "insert into wizyty (PESEL,id_specjalisty,typ_wizyty,miejsce_wizyty,termin_wizyty,dolegliwosci) values ('"+PESEL+"',"+id_specjalisty+",'"+typ_wizyty+"','"+miejsce_wizyty+"','"+termin_wizyty+"','"+dolegliwosci+"');";
		sprawdz = baza.dodajDoBazy(baza.statement, sql);
		if(sprawdz)
		{
			return true;
		}
		else
		{
			System.out.println("dodanieWizyty false");
			return false;
		}
		
	}
}
