package org.example.Connections;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    public static final SessionFactory sessionfactory;
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration=new Configuration().configure();
            sessionfactory= configuration.buildSessionFactory();
        } catch (Exception exception){
            throw exception;
        }
    }
    public static Session getSession(){
        return sessionfactory.openSession();
    }
    // 🔑 ESTE método faltaba
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
