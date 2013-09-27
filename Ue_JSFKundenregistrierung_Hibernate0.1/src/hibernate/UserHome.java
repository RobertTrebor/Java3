package hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

// Generated 26.09.2013 13:05:05 by Hibernate Tools 4.0.0

/**
 * Home object for domain model class User.
 * 
 * @see hibernate.User
 * @author Hibernate Tools
 */
public class UserHome {

	private HibernateUtil hbn = HibernateUtil.getInstance();

	public UserHome() {
		hbn.createHibernateSession();
	}

	/*
	 * natives SQL (Datenbankabhängig) / nur Objekte
	 */
	public List<User> findAllUser() {
		Query q = null;

		Session session = hbn.currentSession();

		q = session.createSQLQuery("SELECT * FROM user");
		return q.list();
	}

	/*
	 * 
	 */
	public List<User> findAllUser2() {
		Query q = null;
		Session session = hbn.currentSession();
		q = session.createQuery("From User");
		return q.list();

	}

	/*
	 * By Criteria / Example
	 */
	public List<User> findByExample(User userTemplate) {
		Session session = hbn.currentSession();
		Criteria criteria = session.createCriteria(User.class);
		Example example = Example.create(userTemplate);
		criteria.add(example);

		return criteria.list();
	}

	public List<User> findUserById(int id) {
		Session session = hbn.currentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.idEq(id));
		return criteria.list();

	}

	public List<User> findUserByUsernameAndPassword(String username,
			String password) {
		Session session = hbn.currentSession();
		Criteria criteria = session.createCriteria(User.class);
		User tmpUser = new User();
		tmpUser.setUsername(username);
		tmpUser.setPasswort(password);
		Example example = Example.create(tmpUser);
		criteria.add(example);

		return criteria.list();
	}

	public List<User> findUserByUsernameAndPassword2(String username,
			String password) {
		Session session = hbn.currentSession();
		Criteria criteria = session.createCriteria(User.class);

		criteria.add(Restrictions.eq("passwort", password));
		criteria.add(Restrictions.eq("username", username));

		return criteria.list();
	}

	public void deleteUserById(int id) {
		Session session = hbn.currentSession();
		Transaction t = session.beginTransaction();
		User user = (User) session.load(User.class, id);
		session.delete(user);
		t.commit();
		System.out.println("wasCommitted" + t.wasCommitted());
	}

	public void updateEMail(int id, String newEMail) {
		Session session = hbn.currentSession();
		Transaction t = session.beginTransaction();
		User user = (User) session.load(User.class, id);
		user.setEmail(newEMail);
		session.update(user);
		t.commit();
	}

	public void updateUsername(int id, String newUsername) {
		Session session = hbn.currentSession();
		String hqlQuery = "update User set username :newUsername where id = :id";
		Query query = session.createQuery(hqlQuery);
		query.setString("newUsername", newUsername);
		query.setInteger("id", id);
		int rowCount = query.executeUpdate();
		System.out.println(rowCount);
	}

	public void attachUser(User user) {
		Session session = hbn.currentSession();
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(user);
		t.commit();
	}

	public static void main(String[] args) {

		UserHome uh = new UserHome();
		System.out.println(uh.findAllUser());
		System.out.println(uh.findAllUser2());

		User user1 = new User(); // Template
		user1.setVorname("Max");

		System.out.println(uh.findByExample(user1));

		User user2 = new User();

		// / Achtung !!!!!
		user2.setId(2);// nicht möglich, da unveränderbares Feld in der DB
		System.out.println(uh.findByExample(user2));// liefert aller User

		System.out.println("findUserById: " + uh.findUserById(2));

		System.out.println("findUserByUsernameAndPassword: "
				+ uh.findUserByUsernameAndPassword("Max", "123"));
		System.out.println("findUserByUsernameAndPassword2: "
				+ uh.findUserByUsernameAndPassword2("Max", "123"));

		// uh.deleteUserById(3);
		uh.updateEMail(1, "testNeu@test.com");
	}
}
