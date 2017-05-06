import Pojo._User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao implements InterfaceDao<_User, String> {

    private SessionFactory sessionFactory;

    public UserDao() {

        sessionFactory = HibernateUtill.getSessionFactory();
    }

    @Override
    public void save(_User entity) {

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
    public _User getById(int id) {

        Session session = sessionFactory.openSession();
        Transaction trans = session.getTransaction();

        try {
            trans.begin();
            _User user = (_User) session.get(_User.class, id);
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
    public _User getByKey(String key) {

        Session session = sessionFactory.openSession();
        Transaction trans = session.getTransaction();

        try {
            trans.begin();
            Query q = session.createQuery("FROM _User  WHERE login = '"+key+"'");

            trans.commit();

            if(q.list().isEmpty()){
                return null;
            }
            else{
                return (_User) q.list().get(0);
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
    public List<_User> getList() {

        Session session = sessionFactory.openSession();
        Transaction trans = session.getTransaction();

        try {
            trans.begin();
            List<_User> lista = session.createQuery("FROM _User").list();
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
    public List<_User> getListByKey(String key) {

        return null;
    }

    @Override
    public List<_User> getOrderedList(String key) {

        return null;
    }

    @Override
    public void delete(_User entity) {

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

            List<_User> lista = session.createQuery("from _User").list();
            for(_User user : lista){  //przechodze liste i usuwam po kolei z elementy z bazy
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