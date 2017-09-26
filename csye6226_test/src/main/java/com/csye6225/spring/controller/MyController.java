package com.csye6225.spring.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyController {
	private static Configuration cfg = new Configuration();
	private static SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();

	public MyController() {

	}

	public static Session getSession() {
		Session session = sf.openSession();
		return session;
	}
}
