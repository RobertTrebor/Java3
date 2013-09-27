package de.cimdata.kundenreg.data;

public class Kunde {
	private int kundenNummer;
	private String vorname;
	private String nachname;
	private String email;
	private String username;
	private String passwort;

	public Kunde() {
	}

	public Kunde(int kundenNummer, String vorname, String nachname,
			String email, String username, String passwort) {
		super();
		this.kundenNummer = kundenNummer;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.username = username;
		this.passwort = passwort;
	}

	public int getKundenNummer() {
		return kundenNummer;
	}

	public void setKundenNummer(int kundenNummer) {
		this.kundenNummer = kundenNummer;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
}
