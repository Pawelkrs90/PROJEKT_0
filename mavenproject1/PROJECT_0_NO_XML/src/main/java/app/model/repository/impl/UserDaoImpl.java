package app.model.repository.impl;

import app.model.User;
import app.model.repository.UserDao;
import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao{
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
    
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
      
        try{
            transaction.begin();
            session.save(user);
            transaction.commit();
        }
        catch(Exception e){   
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if(session.isOpen())
                session.close();
        }
    }

    @Transactional
    @Override
    public User getUserById(int Id) {
       
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try{
            transaction.begin();
            User user = (User) session.get(User.class, Id);
            transaction.commit();
            
            return user;
        }
        catch(Exception e){
           transaction.rollback();
        } finally {
            if(session.isOpen())
                session.close();
        }
        
        return null;
    }
    
    @Transactional
    @Override
    public User findByUserName(String name){

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try{
            transaction.begin();
            
            User user = (User) sessionFactory.getCurrentSession()
			.createQuery("from USERS where USER_NAME=?")
			.setParameter(0, name).list().get(0);

            transaction.commit();
            
            return user;
        }
        catch(HibernateException e){
           transaction.rollback();
        } finally {
            if(session.isOpen())
                session.close();
        }
        return null;
    
    }
    
    @Transactional
    @Override
    public List<User> getUserList() {
        
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try{
            transaction.begin();
            session.createCriteria(User.class);
            List<User> userList =  session.createCriteria(User.class).list();
            transaction.commit();
            
            return userList;
        }
        catch(Exception e){
           transaction.rollback();
        } finally {
            if(session.isOpen())
                session.close();
        }
        return null;
    }   
}