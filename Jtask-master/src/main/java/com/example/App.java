package com.example;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Accounts;
import model.Address;
import model.Command;
import model.Customer;
import operations.CommandExecutor;
import operations.CustomerOps;
import operations.PhoneOps;
import operations.AccountsOps;
import operations.AddressOps;

public class App {
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(Address.class);
		configuration.addAnnotatedClass(Command.class);
		configuration.addAnnotatedClass(Accounts.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();
//
		CustomerOps.getCustomers();
//
//		Address address = new Address("a", "b", "c", "d");
//
//		session.beginTransaction();
//		session.persist(address);
//		session.getTransaction().commit();
//
//		Method[] methods;
//		methods = AddressOps.class.getDeclaredMethods();
//		session.beginTransaction();
//		for (Method mtd : methods){
//			Command cmd = new Command(AddressOps.class, mtd.getName());
//			session.persist(cmd);
//		}
//		session.getTransaction().commit();
		
		LinkedHashMap<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put("customer", new Customer("Onur DML", 28, "11111111111"));
		System.out
				.println("\nCommand true: " + CommandExecutor.executeCommand("customerops-insertCustomer", parameters));
		
		LinkedHashMap<String, Object> parametersDel = new LinkedHashMap<String, Object>();
		parametersDel.put("column", CustomerOps.columns.NAME.getColumnName());
		parametersDel.put("value", "Tolgahan Yıldırım");
		System.out
				.println("\nCommand true: " + CommandExecutor.executeCommand("customerops-deleteCustomerWithValue", parametersDel));
////
//		Hasher hasher = BCrypt.withDefaults();
//		byte[] hashedPassword = hasher.hash(12, "asdf123.edfrsae".toCharArray());
//		Accounts acc = new Accounts("11111111", hashedPassword.toString(), "Haydar Salatalık", "test@deneme.com", LocalDate.now().toString());
//
//		session.beginTransaction();
//		session.persist(acc);
//		session.getTransaction().commit();
//
		session.close();
		sessionFactory.close();
	}
}
