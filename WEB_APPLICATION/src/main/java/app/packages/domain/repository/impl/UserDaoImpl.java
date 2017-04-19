/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.packages.domain.repository.impl;

import app.packages.domain.User;
import app.packages.domain.repository.UserDao;
import java.util.List;
import org.apache.log4j.Logger;
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
    public void addUser(User user) {
    
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        //session.persist(user);
        
         Logger.getLogger(this.getClass().getName()).info("ok??");
         
         //session.persist(user);
         //session.save(user);
         
        try{
            transaction.begin();
            
           // session.persist(user);
            session.save(user);
           
           
            transaction.commit();
            Logger.getLogger(this.getClass().getName()).info("OKOK");
            
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
            
            //List<User> userList = session.createQuery("from User").list();
          
            User user = (User) session.get(User.class, Id);

            transaction.commit();
            
            return user;
        }
        catch(Exception e){
            
           e.printStackTrace();
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
            
            //List<User> userList = session.createQuery("from User").list();
          
            session.createCriteria(User.class);
            List<User> userList =  session.createCriteria(User.class).list();

            transaction.commit();
            
            return userList;
        }
        catch(Exception e){
            
           e.printStackTrace();
           transaction.rollback();
        } finally {
            if(session.isOpen())
                session.close();
        }
        return null;
    }
    
}