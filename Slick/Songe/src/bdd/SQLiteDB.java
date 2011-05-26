package bdd;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Conf;

/**
 * Manages an SQLite DB
 * 
 * @author Afnarel
 */
public class SQLiteDB {

	protected Connection connection;
	protected Statement statement;
	
	public SQLiteDB(String db_name) {

		try {
			Class.forName("org.sqlite.JDBC");
			//if under jnlp
    		if(System.getProperty("javawebstart.version") != null)
    			this.connection = DriverManager
					.getConnection("jdbc:sqlite:" + Conf.HOME+File.separator+db_name);
    		//if under CD Devint
    		else
    			this.connection = DriverManager
					.getConnection("jdbc:sqlite:" + Conf.RESS_PATH+db_name);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			this.statement = this.connection.createStatement();
			// this.statement.setQueryTimeout(30); // set timeout to 30 sec
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// connection close failed.
			System.err.println(e);
		}
	}
	
	public void executeQuery(String query) {
		if(query.startsWith("SELECT") || query.startsWith("select")) {
			executeSelectQuery(query);
		}
		else {
			executeUpdateQuery(query);
		}
	}
	
	/*
	private boolean stringInTab(String str, String[] tab) {
		for(String s : tab) {
			if(str.equals(s))
				return true;
		}
		return false;
	}
	*/

	/**
	 * La query doit probablement se finir par un point-virgule... A voir
	 * 
	 * @param query
	 */
	private void executeSelectQuery(String query) {
		ResultSet rs;
		try {
			rs = this.statement.executeQuery(query);
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
				System.out.println(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i));
				//System.out.println(rs.getMetaData().getColumnName(i));
				//System.out.println(rs.getMetaData().getColumnLabel(i));
			}
			/*
			while (rs.next()) {
                System.out.println(rs.getString("nom"));
                System.out.println(rs.getString("type"));
                System.out.println(rs.getString("duree"));
                System.out.println(rs.getInt("points"));
                System.out.println("======");
            }
            */
			rs.close();
			System.out.println("La requête (" + query + ") a été exécutée avec succès.");
		} catch (SQLException e) {
			System.err.println("Problème lors de l'exécution de la requête (" + query + ").");
		}
	}
	
	public ArrayList<HashMap<String,String>> select(String query) {
		ArrayList<HashMap<String,String>> values = new ArrayList<HashMap<String,String>>();
		
		ResultSet rs;
		try {
			rs = this.statement.executeQuery(query);
			while(rs.next()) {
				HashMap<String,String> rowValues = new HashMap<String,String>();
				for(int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					System.out.println(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i));
					rowValues.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				values.add(rowValues);
			}
			rs.close();
			System.out.println("La requête (" + query + ") a été exécutée avec succès.");
		} catch (SQLException e) {
			System.err.println("Problème lors de l'exécution de la requête (" + query + ").");
		}
		
		return values;
	}
	
	public HashMap<String,String> selectSingle(String query) {
		HashMap<String,String> rowValues = new HashMap<String,String>();;
		
		ResultSet rs;
		try {
			rs = this.statement.executeQuery(query);
			
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
				System.out.println(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i));
				rowValues.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			}
				
			rs.close();
			System.out.println("La requête (" + query + ") a été exécutée avec succès.");
		} catch (SQLException e) {
			System.err.println("Problème lors de l'exécution de la requête (" + query + ").");
		}
		
		return rowValues;
	}
	
	private void executeUpdateQuery(String query) {
	    try {
            this.statement.executeUpdate(query);
            System.out.println("La requête (" + query + ") a été exécutée avec succès.");
        } catch (SQLException e) {
            System.err.println("Problème lors de l'exécution de la requête (" + query + ").");
            e.printStackTrace();
        }
	}

}
