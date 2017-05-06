
import Pojo._Ogloszenie;
import Pojo._Trip;
import Pojo._User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtill {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSectionFactory() {

        try {

            SessionFactory factory = null;

            if (sessionFactory == null) {
                Configuration configuration = new Configuration().configure(HibernateUtill.class.getResource("hibernate.cfg.xml"));
     
                  configuration.addAnnotatedClass(_Trip.class);
                  configuration.addAnnotatedClass(_User.class);
                  configuration.addAnnotatedClass(_Ogloszenie.class);

                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
                serviceRegistryBuilder.applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
                factory = configuration.buildSessionFactory(serviceRegistry);
            }

            return factory;
        } catch (Throwable ex) {
            System.err.println("Blad inicjalizacji: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = buildSectionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {

        getSessionFactory().close();
    }
}
