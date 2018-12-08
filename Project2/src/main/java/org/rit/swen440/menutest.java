package org.rit.swen440;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.presentation.menumgr;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class menutest
{
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration().configure();
        Properties properties = configuration.getProperties();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static void main(String[] args)
    {
        configureSessionFactory();
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            System.out.println(session.createQuery(" from Product").list());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            System.getProperties().load(new FileInputStream("orderSys.properties"));
            System.out.println("Hello");
            menumgr mgr = new menumgr();
            int currentLevel = 0;
            boolean done = false;
            do {
                done = mgr.loadLevel(currentLevel);
            } while (!done);

            System.out.println("Thank you for shopping at Hippolyta.com!");

        } catch(IOException e) {
            System.err.println("orderSys.properties not found.");
        }

    }
}