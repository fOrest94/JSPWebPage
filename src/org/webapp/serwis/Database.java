package org.webapp.serwis;
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
	
/*	public boolean sprawdzUzytkownika(Statement statement, String zapytanie_sql) 
	{
		try 
		{
			if(statement.execute(zapytanie_sql))
			{
			System.out.println("Czemu tak ?");
	
			return true;
			}
			else
				return false;
		} 
		catch (SQLException e) 
		{
			return false;
		}
		*
	}*/
	
	public boolean dodajUzytkownika(Statement statement, String zapytanie_sql) 
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
	
	public ArrayList<String> printDataFromQuery(ResultSet result) 
	{
		System.out.print("printDataFromQuery");
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
						arrlist.add(result.getString("imie"));
						arrlist.add(result.getString("nazwisko"));
						arrlist.add(result.getString("email"));
						arrlist.add(result.getString("telefon"));
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Bl¹d odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		return arrlist;
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
}
