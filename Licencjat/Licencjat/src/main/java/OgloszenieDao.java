
import Pojo._Ogloszenie;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class OgloszenieDao implements InterfaceDao<_Ogloszenie, String>{

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction trans;

	public OgloszenieDao() {

		sessionFactory = HibernateUtill.getSessionFactory();
	}

	@Override
	public void save(_Ogloszenie enity) {

		session = sessionFactory.openSession();
		trans = session.getTransaction();

		try {
			trans.begin();
			if (enity.getId() == 0)
				session.persist(enity);
			else
				session.merge(enity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public _Ogloszenie getById(int id) {

		session = sessionFactory.openSession();
		trans = session.getTransaction();

		try {
			trans.begin();
			_Ogloszenie user = (_Ogloszenie) session.get(_Ogloszenie.class, id);
			trans.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public _Ogloszenie getByKey(String key) {

		session = sessionFactory.openSession();
		trans = session.getTransaction();

		try {
			trans.begin();
			Query q = session.createQuery("FROM _Ogloszenie  WHERE login = '"+key+"'");
			trans.commit();

			if(q.list().isEmpty()){
				return null;
			}
			else{
				return (_Ogloszenie) q.list().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<_Ogloszenie> getList() {

		session = sessionFactory.openSession();
		trans = session.getTransaction();
		
		try {
			trans.begin();
            List<_Ogloszenie> lista = session.createQuery("FROM _Ogloszenie").list();
			trans.commit();
                       
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<_Ogloszenie> getListByKey(String key) {
		return null;
	}

	@Override
	public List<_Ogloszenie> getOrderedList(String key) {
		return null;
	}

	@Override
	public void delete(_Ogloszenie enity) {

		session = sessionFactory.openSession();
		trans = session.getTransaction();

		try {
			trans.begin();
			session.delete(enity);
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteAll() {

		session = sessionFactory.openSession();
		trans = session.getTransaction();

		try {
			trans.begin();

			List<_Ogloszenie> lista = session.createQuery("from _Ogloszenie").list();
			for(_Ogloszenie user : lista){  //przechodze liste i usuwam po kolei z elementy z bazy
				session.delete(user);
			}

			trans.commit();

			return ;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
	}

}
