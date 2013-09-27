package de.cimdata.kundenreg.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import de.cimdata.kundenreg.data.Kunde;

public class KundeHome {

	private HibernateUtil hbn = HibernateUtil.getInstance();

	public KundeHome() {
		hbn.createHibernateSession();
	}

	public Kunde findKunde(String usr, String pwd) {
		Session session = hbn.currentSession();
		Kunde tempKunde = new Kunde();
		tempKunde.setUsername(usr);
		tempKunde.setPasswort(pwd);
		Criteria criteria = session.createCriteria(Kunde.class);

		Example example = Example.create(tempKunde);
		criteria.add(example);

		return (Kunde) criteria.list().get(0);
	}

	public boolean storeNewKunde(Kunde kunde) {
		Session session = hbn.currentSession();
		Transaction trx = session.beginTransaction();
		session.saveOrUpdate(kunde);
		trx.commit();
		return trx.wasCommitted();
	}

	public static void main(String[] args) {
		KundeHome kh = new KundeHome();
		System.out.println(kh.findKunde("max", "123"));
		System.out.println(kh.storeNewKunde(new Kunde("Emil", "Müller",
				"username", "passwort", "email@email.com")));
	}
}
