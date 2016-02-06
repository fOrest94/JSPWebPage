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
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public Connection connectToDatabase(String adress, String dataBaseName, String userName, String password) 
	{
		String baza = "jdbc:mysql://" + adress + "/" + dataBaseName;
		java.sql.Connection connection = null;
		
		try 
		{
			connection = DriverManager.getConnection(baza, userName, password);
		} 
		catch (SQLException e) 
		{
			System.out.println("Blad przy laczeniu z baza!");
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
			System.out.println(zapytanie_sql);
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
			statement.executeUpdate(zapytanie_sql);
			return true;
		}
		catch (SQLException e) 
		{
			System.out.println("metoda nie chodzi");
			return false;
		}
	}
	
	public ResultSet executeQuery(Statement statement, String zapytanie_sql) 
	{
		try 
		{
			return statement.executeQuery(zapytanie_sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> showListOfPacjent(ResultSet result) 
	{
		ArrayList<String> arrlist = new ArrayList<String>();
		try 
		{
			arrlist.add("PESEL");
			arrlist.add("Imie");
			arrlist.add("Nazwisko");
			arrlist.add("Email");
			arrlist.add("Login");
			arrlist.add("Haslo");
			
			while (result.next()) 
			{
						arrlist.add(result.getString("PESEL"));
						arrlist.add(result.getString("imie"));
						arrlist.add(result.getString("nazwisko"));
						arrlist.add(result.getString("email"));
						arrlist.add(result.getString("login"));
						arrlist.add(result.getString("haslo"));
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Blad odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return arrlist;
	}
	
	public ArrayList<String> showListOfSpec(ResultSet result) 
	{
		ArrayList<String> arrlist = new ArrayList<String>();
		try 
		{
			arrlist.add("Id_Specjalisty");
			arrlist.add("Imie");
			arrlist.add("Nazwisko");
			arrlist.add("Email");
			arrlist.add("Login");
			arrlist.add("Haslo");
			
			while (result.next()) 
			{
						arrlist.add(result.getString("id_specjalisty"));
						arrlist.add(result.getString("imie"));
						arrlist.add(result.getString("nazwisko"));
						arrlist.add(result.getString("email"));
						arrlist.add(result.getString("login"));
						arrlist.add(result.getString("haslo"));
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Blad odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return arrlist;
	}
	
	public ArrayList<String> showListOfWisits(ResultSet result) 
	{
		ArrayList<String> arrlist = new ArrayList<String>();
		try 
		{
			arrlist.add("Id_wizyty");
			arrlist.add("Pesel Pacjenta");
			arrlist.add("Id specjalisty");
			arrlist.add("Typ wizyty");
			arrlist.add("Miejsce wizyty");
			arrlist.add("Termin wizyty");
			arrlist.add("Dolegliwosci");
			
			while (result.next()) 
			{
						arrlist.add(result.getString("id_wizyty"));
						arrlist.add(result.getString("PESEL"));
						arrlist.add(result.getString("id_specjalisty"));
						arrlist.add(result.getString("typ_wizyty"));
						arrlist.add(result.getString("miejsce_wizyty"));
						arrlist.add(result.getString("termin_wizyty"));
						arrlist.add(result.getString("dolegliwosci"));
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Blad odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return arrlist;
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
			System.out.println("Blad odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return arrlist;
	}
	
	public ArrayList<String> getMarkers(ResultSet result) 
	{
		ArrayList<String> arrlist = new ArrayList<String>();
		int licznik = 0;
		try 
		{
			while (result.next()) 
			{
						arrlist.add(result.getString("id"));
						arrlist.add(result.getString("name"));
						arrlist.add(result.getString("address"));
						arrlist.add(result.getString("lat"));
						arrlist.add(result.getString("lng"));
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

		try 
		{
			while (result.next()) 
			{
				if(type.equals("Pacjent") || type.equals("admin"))
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
					ArrayList<String> arraylistIN = new ArrayList<String>(2);
					Database baza = new Database();
					String sql = "SELECT Imie,Nazwisko from lekarze where id_specjalisty = '"+result.getString("id_specjalisty")+"';";
					arraylistIN = baza.pokazProfil(baza.executeQuery(baza.statement, sql), "getIN");
					arrlist.add(result.getString("id_wizyty"));
					arrlist.addAll(arraylistIN);
					arrlist.add(result.getString("typ_wizyty"));
					arrlist.add(result.getString("miejsce_wizyty"));
					arrlist.add(result.getString("termin_wizyty"));
					arrlist.add(result.getString("dolegliwosci"));
				}
				else if(type.equals("SpecjalistaW"))
				{
					ArrayList<String> arraylistIN = new ArrayList<String>(2);
					Database baza = new Database();
					String sql = "SELECT imie,nazwisko from pacjenci where PESEL = '"+result.getString("PESEL")+"';";
					arraylistIN = baza.pokazProfil(baza.executeQuery(baza.statement, sql), "getIN");
					arrlist.add(result.getString("id_wizyty"));
					arrlist.addAll(arraylistIN);
					arrlist.add(result.getString("typ_wizyty"));
					arrlist.add(result.getString("miejsce_wizyty"));
					arrlist.add(result.getString("termin_wizyty"));
					arrlist.add(result.getString("dolegliwosci"));
				}
				else if(type.equals("getIN"))
				{
					arrlist.add(result.getString("imie"));
					arrlist.add(result.getString("nazwisko"));	
				}
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
