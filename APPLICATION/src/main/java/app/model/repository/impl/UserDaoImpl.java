package app.model.repository.impl;

import app.exceptions.LoginNotFoundException;
import app.model.User;
import app.model.repository.UserDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao{
    
    private Logger logger = Logger.getLogger(getClass().getName());
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
        
        logger.info("findUserByName");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try{
            transaction.begin();
        
            session.createCriteria(User.class);     
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", name));
             
            User user = (User) criteria.list().get(0);
            
            transaction.commit();
            
            return user;
        }
        catch(HibernateException e){
            logger.info("rollback");
            transaction.rollback();
         
	    throw new LoginNotFoundException(name);
          
        } finally {
            if(session.isOpen())
                session.close();
        }
       
    }
    
    @Transactional
    @Override
    public List<User> getUserList() {
        
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try{
            transaction.begin();
           
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