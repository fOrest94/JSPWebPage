package org.webapp.serwis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Database 
{
	public Statement statement;
	
	Database()
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
	public ResultSet executeQuery(Statement state, String zapytanie_sql) 
	{
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
