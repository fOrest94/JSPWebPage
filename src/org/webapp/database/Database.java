package org.webapp.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;

public class Database 
{
	public Statement statement;
	
	public Database()
	{
		ladujSterownik();
		statement = createStatement(connectToDatabase("localhost:3306","saisp", "root", ""));
		
	}
	
	public boolean ladujSterownik() 
	{
		System.out.print("Sprawdzanie sterownika:");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.print(" Sterownik OK");
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public Connection connectToDatabase(String adress, String dataBaseName, String userName, String password) 
	{
		System.out.print("\nLaczenie z baza danych:");
		String baza = "jdbc:mysql://" + adress + "/" + dataBaseName;
		
		java.sql.Connection connection = null;
		try 
		{
			connection = DriverManager.getConnection(baza, userName, password);
		} 
		catch (SQLException e) 
		{
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		return connection;
	}
	
	public Statement createStatement(Connection connection) 
	{
		try 
		{
			return connection.createStatement();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean dodajDoBazy(Statement statement, String zapytanie_sql) 
	{
		try
		{
			statement.executeUpdate(zapytanie_sql);
			return true;
		}
		catch (SQLException e) 
		{
			System.out.println("metoda nie chodzi");
			return false;
		}
	}
	
	public boolean updateUserData(Statement statement, String zapytanie_sql) 
	{
		try
		{
			System.out.println("jestem tu ziomek malaka");
			statement.executeUpdate(zapytanie_sql);
			return true;
		}
		catch (SQLException e) 
		{
			System.out.println("metoda nie chodzi");
			return false;
		}
	}
	
	public boolean pobierzZBazy(Statement statement, String zapytanie_sql)
	{
		try
		{
			statement.executeUpdate(zapytanie_sql);
			return true;
		}
		catch (SQLException e) 
		{
			System.out.println("metoda nie chodzi");
			return false;
		}
	}
	
	public ResultSet executeQuery(Statement state, String zapytanie_sql) 
	{
		System.out.print("executeQuery");
		try 
		{
			
			return state.executeQuery(zapytanie_sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> pokazSpecjalistow(ResultSet result) 
	{
		ResultSetMetaData rsmd;
		ArrayList<String> arrlist = new ArrayList<String>();
		int licznik = 0;
		try 
		{
			rsmd = result.getMetaData();
			int numcols = rsmd.getColumnCount();
 
			for (int i = 1; i <= numcols; i++) 
			{
				if(rsmd.getColumnLabel(i).equals("imie") || rsmd.getColumnLabel(i).equals("nazwisko") ||
				   rsmd.getColumnLabel(i).equals("email") || rsmd.getColumnLabel(i).equals("telefon"))
				{
					System.out.print(arrlist.add(rsmd.getColumnLabel(i))+" ");
					licznik++;
				}
				
			}
			while (result.next()) 
			{
						arrlist.add(result.getString("id_specjalisty"));
						arrlist.add(result.getString("imie"));
						arrlist.add(result.getString("nazwisko"));
						arrlist.add(result.getString("email"));
						arrlist.add(result.getString("telefon"));
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Bl퉐 odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return arrlist;
	}
	
	public ArrayList<String> pokazProfil(ResultSet result, String type) 
	{
		ArrayList<String> arrlist = new ArrayList<String>();
		
			System.out.println("2.0"+type);
		int licznik = 0;
		try 
		{
			while (result.next()) 
			{
				if(type.equals("Pacjent"))
				{
					arrlist.add(result.getString("imie"));
					arrlist.add(result.getString("nazwisko"));
					arrlist.add(result.getString("PESEL"));
					arrlist.add(result.getString("email"));
					arrlist.add(result.getString("login"));
					arrlist.add(result.getString("haslo"));
				}
				else if(type.equals("Specjalista"))
				{
					arrlist.add(result.getString("imie"));
					arrlist.add(result.getString("nazwisko"));
					arrlist.add(result.getString("email"));
					arrlist.add(result.getString("telefon"));
					arrlist.add(result.getString("login"));
					arrlist.add(result.getString("haslo"));
					arrlist.add(result.getString("specjalizacja"));
					arrlist.add(result.getString("miasto"));
					arrlist.add(result.getString("id_specjalisty"));
				}
				else if(type.equals("ShowSpecProfile"))
				{
					arrlist.add(result.getString("imie"));
					arrlist.add(result.getString("nazwisko"));
					arrlist.add(result.getString("email"));
					arrlist.add(result.getString("telefon"));
					arrlist.add(result.getString("specjalizacja"));
					arrlist.add(result.getString("miasto"));
					arrlist.add(result.getString("opis"));
				}
				else if(type.equals("PacjentPESEL"))
				{
					arrlist.add(result.getString("PESEL"));
				}
				else if(type.equals("SpecjalistaId_Placowki"))
				{
					arrlist.add(result.getString("id_placowki"));
				}
				else if(type.equals("ShowPlaceInfo"))
				{
					arrlist.add(result.getString("nazwa"));
					arrlist.add(result.getString("miasto"));
					arrlist.add(result.getString("ulica"));
				}
				else if(type.equals("PacjentW"))
				{
					Database baza = new Database();
					String sql = "SELECT Imie,Nazwisko from lekarze where id_specjalisty = '"+result.getString("id_specjalisty")+"';";
					arrlist = baza.pokazProfil(baza.executeQuery(baza.statement, sql), sql);
					arrlist.add(result.getString("typ_wizyty"));
					arrlist.add(result.getString("miejsce_wizyty"));
					arrlist.add(result.getString("termin_wizyty"));
					arrlist.add(result.getString("dolegliwosci"));
				}
				else if(type.equals("SpecjalistaW"))
				{
					System.out.println("2dsdf342");
					Database baza = new Database();
					String sql = "SELECT imie,nazwisko from pacjenci where PESEL = '"+result.getString("PESEL")+"';";
					arrlist = baza.pokazProfil(baza.executeQuery(baza.statement, sql), sql);
					arrlist.add(result.getString("PESEL"));
					arrlist.add(result.getString("typ_wizyty"));
					arrlist.add(result.getString("miejsce_wizyty"));
					arrlist.add(result.getString("termin_wizyty"));
					arrlist.add(result.getString("dolegliwosci"));
				}
				System.out.println("2.5");
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Bl퉐 odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return arrlist;
	}
	
	public boolean ifUserExist(ResultSet result, String login, String email) 
	{
		try 
		{
			while (result.next()) 
			{
						if(result.getString("login").equals(login))
						{
							return false;
						}
						else if(result.getString("email").equals(email))
						{
							return false;
						}
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Bl퉐 odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return true;
	}
	
	public boolean findUserInfo(ResultSet result, String login, String password) 
	{
		try 
		{
			while (result.next()) 
			{
				if(result.getString("login").equals(login) && result.getString("haslo").equals(password))
				{
					return true;
				}
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Bl퉐 odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return false;
	}
	
	public boolean ifCorrectPass(Database baza, String hasloOld, String typeOfUser) 
	{
		String sql = "SELECT haslo FROM "+typeOfUser+" WHERE haslo = '"+hasloOld+"';";
		ResultSet result = baza.executeQuery(baza.statement, sql);
		try 
		{
			while (result.next()) 
			{
				if(result.getString("haslo").equals(hasloOld))
				{
					return true;
				}
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Bl퉐 odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return false;
	}
	
}
