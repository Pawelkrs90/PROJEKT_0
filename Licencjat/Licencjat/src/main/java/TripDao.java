import Pojo._Trip;
import Pojo._User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class TripDao implements InterfaceDao<_Trip, String> {

	private SessionFactory sessionFactory;

	public TripDao() {

		sessionFactory = HibernateUtill.getSessionFactory();
	}

	@Override
	public void save(_Trip entity) {

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();
			if (entity.getId() == 0)
				session.persist(entity);
			else
				session.merge(entity);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public _Trip getById(int id) {

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();
			_Trip user = (_Trip) session.get(_Trip.class, id);
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
	public _Trip getByKey(String key) {

		//aktualnie brak metody, jest zbędna

		return null;
	}

	@Override
	public List<_Trip> getList() {

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();
			List<_Trip> lista = session.createQuery("FROM _Trip").list();
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

	public Collection<_User> getUczestnicy(int id) {

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {

			trans.begin();
			_Trip user = (_Trip) session.get(_Trip.class, id);

			Collection<_User> lista = user.getUczestnicy();

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
	public List<_Trip> getListByKey(String key) {  //typowy search

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();
			Query query = session.createQuery("FROM _Trip t WHERE t.nazwa like '%"+key+"%' or t.mscDocelowe like '%"+key+"%' or t.celPodrozy like '%"+key+"%' ");  //HQL
			List<_Trip> lista = query.list();
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
	public List<_Trip> getOrderedList(String key) {

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			Query query = session.createQuery("FROM _Trip t ORDER BY t."+key+" ASC");  //HQL
			//SQLQuery query = session.createSQLQuery("SELECT * FROM _Trip t ORDER BY t.koszt ASC");
			//query.addEntity(_Trip.class);
			List<_Trip> lista = query.list();

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

	public List<_Trip> getFreeSpaceList(){  //lista wycieczek z wolnymi miesjcami

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			Query query = session.createQuery("FROM _Trip t WHERE t.zapisaneOsoby>0  ORDER BY t.zapisaneOsoby DESC");
			List<_Trip> lista = query.list();

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
	public void delete(_Trip entity) {

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();
			session.delete(entity);
			trans.commit();

			return ;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteAll() {

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			List<_Trip> lista = session.createQuery("FROM _Trip").list();
			for(_Trip trip : lista){  //przechodze liste i usuwam po kolei z elementy z bazy
				session.delete(trip);
			}
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			session.close();
		}
	}

}
