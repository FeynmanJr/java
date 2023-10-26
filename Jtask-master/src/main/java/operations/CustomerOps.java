package operations;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import model.Customer;

public class CustomerOps {
	static Configuration configuration;
	static Session session;

	static {
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Customer.class);
		session = configuration.buildSessionFactory().openSession();
	}

	public enum columns {
		ID("id"), TCKN("tckn"), NAME("name"), AGE("age");

		private String columnName;

		columns(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	public static boolean getCustomers() {
		List<Customer> custResult = session.createQuery("FROM Customer", Customer.class).list();

		if (custResult != null) {
			for (int i = 0; i < custResult.size(); i++) {
				if (i != custResult.size() - 1)
					System.out.println(custResult.get(i).toString() + ", ");
				else
					System.out.println(custResult.get(i).toString());
			}
			return true;
		}
		return false;
	}

	public static boolean deleteCustomerWithValue(LinkedHashMap<String, Object> params) {
		System.out.println("WORKİNG");
		Object obj2 = params.get("column");
		Object obj3 = params.get("value");
		if (/*obj2 instanceof String &&*/ obj3 instanceof String) {
			String column = (String) obj2;
			String value = (String) obj3;
			session.beginTransaction();
			Query<Customer> query = session.createQuery("FROM Customer WHERE " + column + " = :" + column + "",
					Customer.class);
			query.setParameter(column, value);
			List<Customer> custResultRemove = query.list();
			if (custResultRemove != null) {
				for (Customer res : custResultRemove)
					session.remove(res);
				session.getTransaction().commit();
				return true;
			} else
			System.out.println(column + " bilgisi " + value + " olan bir kayıt bulunamadı.");
		} else
			System.out.println("Objelerden birisi null...");
		return false;
	}

	public static boolean insertCustomer(LinkedHashMap<String, Object> params) {

		Object obj1 = params.get("customer");
		if (obj1 instanceof Customer) {
			Customer newCustomer = (Customer) obj1;

			session.beginTransaction();
			session.persist(newCustomer);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}
}
