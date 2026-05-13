package com.radosti.app.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try{
            return new Configuration().configure().buildSessionFactory();
            } catch (Exception e){
            throw new RuntimeException("The problem in building SessionFactory " + e.getMessage() );
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
