package app.model.repository.impl;

import app.model.User;
import app.model.UserRole;
import app.model.repository.UserRoleDao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class UserRoleDaoImpl implements UserRoleDao{
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void saveUserRole(UserRole userRole) {
    
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
         
        try{
            transaction.begin();
            session.save(userRole);
            transaction.commit(); 
        }
        catch(Exception e){
           e.printStackTrace();
           transaction.rollback();
        } 
        finally {
            if(session.isOpen())
                session.close();
        }
    }
    
    @Transactional
    @Override
    public void deleteUserRole(UserRole userRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public void deletUserRoleByUser(User user){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public List<UserRole> getUserRoleList() {
  
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try{
            transaction.begin();
            List<UserRole> userRoleList = session.createCriteria(UserRole.class).list();
            transaction.commit();
            
            return userRoleList;
        }
        catch(HibernateException e){         
            e.printStackTrace();
            transaction.rollback();        
        } finally {
            if(session.isOpen())
                session.close();
        }
        return null; 
    }
  
}