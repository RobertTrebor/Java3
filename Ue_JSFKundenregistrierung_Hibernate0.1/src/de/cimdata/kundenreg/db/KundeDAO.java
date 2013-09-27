package de.cimdata.kundenreg.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import de.cimdata.kundenreg.data.Kunde;

public class KundeDAO {
	private DBConnect db = DBConnect.getInstance();
	private Connection con;

	public KundeDAO() {
		con = db.getConnection();
	}

	/*
	 * Besser PreparedStatement!!!
	 */
	public Kunde findKunde(String usr, String pwd) {

		Kunde user = null;
		try {
			Statement s = con.createStatement();
			ResultSet result = s
					.executeQuery("SELECT * FROM kunde WHERE username ='" + usr
							+ "' AND passwort ='" + pwd + "'");
			while (result.next()) {
				user = createUserByResult(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	private Kunde createUserByResult(ResultSet result) {
		Kunde user = null;
		try {
			user = new Kunde();
			user.setUsername(result.getString("username"));
			user.setPasswort(result.getString("passwort"));
			user.setNachname(result.getString("nachname"));
			user.setVorname(result.getString("vorname"));
			user.setEmail(result.getString("email"));
			user.setKundenNummer(result.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean storeNewKunde(Kunde newKunde) {
		boolean successful = false;
		// Prüfen ob schon vorhanden
		
		String insertQuery = "INSERT INTO kunde ( vorname,nachname,username,passwort, email)  VALUES ( ?,?,?,?,?)";
		try {
			PreparedStatement preparedStmt = con.prepareStatement(insertQuery);
			preparedStmt.setString(1, newKunde.getVorname());
			preparedStmt.setString(2, newKunde.getNachname());
			preparedStmt.setString(3, newKunde.getUsername());
			preparedStmt.setString(4, newKunde.getPasswort());
			preparedStmt.setString(5, newKunde.getEmail());
			int row = preparedStmt.executeUpdate();
			if(row==1){
				successful=true;
			}else{
				// hier Exception auslösen (InsertException row<1, DatensatzzuvielException row >1 )
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return successful;
	}
	
	
	
	/*
	//TODO dummy mock
	public Kunde findKunde(String usr, String pwd){
		
		if(usr.equals("max")&& pwd.equals("123")){
			return new Kunde(23, "TestMax", "TestMeier", "test@tet.de", "max", "passwort");
		}
		
		return null;
		
	}
	
	//TODO dummy
	public boolean storeNewKunde(Kunde newKunde){
		return true;
	}
	*/
	
	
}
