										package de.cimdata.kundenreg.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.cimdata.kundenreg.data.Kunde;
import de.cimdata.kundenreg.hibernate.KundeHome;
import de.cimdata.kundenreg.jsfutil.FacesHelper;

@SessionScoped
@ManagedBean
public class KundeBean {

	
	public static final int NEUKUNDE = 1;
	public static final int BESTANDSKUNDE = 2;
	private String usr, pwd;
	private Kunde kunde;
	private int kundenStatus = 0;
	private KundeHome dbm;

	public KundeBean() {
		kunde = new Kunde();
		dbm = new KundeHome();
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public int getKundenStatus() {
		return kundenStatus;
	}

	public void setKundenStatus(int kundenStatus) {
		this.kundenStatus = kundenStatus;
	}

	/*
	 * Verbesserungen 1. neues LoginBean mit Attributen: username, passwort
	 * (evtl. FacesContext benutzen) 2. isKundeValid boolean?
	 */
	public String isKundeValid() {
		String link = "showKunde";
		kunde = dbm.findKunde(usr, pwd);  //TODO dbm.findKunde(usr, pwd) impl
		if (kunde == null) {
		
			FacesHelper.setMSG("kundeForm:login_fail", "Kunde nicht vorhanden!");
			
		System.out.println("kein Kunde");
			link = "";
		}

		return link;
	}

	public String save() {
		String result = "insertNotOk";
		System.out.println("daten werden gespeichert");
		boolean ok = dbm.storeNewKunde(kunde);// TODO dbm.storeNewKunde(kunde) impl
		
		if (ok) {
			kundenStatus = BESTANDSKUNDE;
			result = "formKunde";
			setKunde(new Kunde()); // zum Rücksetzen (refresh)
		}
		return result;
	}

	public String logout() {
		setKunde(new Kunde());
		return "formKunde";
	}

}
