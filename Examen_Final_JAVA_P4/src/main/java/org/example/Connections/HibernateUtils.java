package org.example.Connections;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    public static final SessionFactory sessionfactory;
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
}
