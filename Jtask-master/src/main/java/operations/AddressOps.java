package operations;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Address;

public class AddressOps {

    static Configuration configuration;
	static Session session;

	static {
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Address.class);
		session = configuration.buildSessionFactory().openSession();
	}
	
	public enum columns {
		KEY("key"),
		COUNTRY("country"),
		CITY("city"),
		DISTRICT("district"),
		ADDRESS("addressrest");
		
		private String columnName;
		
		columns(String columnName){
			this.columnName = columnName;
		}
		
		public String getColumnName() {
			return columnName;
		}
	}

	public static boolean getAddresss() {
		List<Address> custResult = session.createQuery("FROM Address", Address.class).list();

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

	public static boolean deleteAddressWithValue(LinkedHashMap<String, Object> params) {
		System.out.println("WORKİNG");
		Object obj2 = params.get("column");
		Object obj3 = params.get("value");
		if (/*obj2 instanceof String &&*/ obj3 instanceof String) {
			String column = (String) obj2;
			String value = (String) obj3;
			session.beginTransaction();
			Query<Address> query = session.createQuery("FROM Address WHERE " + column + " = :" + column + "",
					Address.class);
			query.setParameter(column, value);
			List<Address> custResultRemove = query.list();
			if (custResultRemove != null) {
				for (Address res : custResultRemove)
					session.remove(res);
				session.getTransaction().commit();
				return true;
			} else
			System.out.println(column + " bilgisi " + value + " olan bir kayıt bulunamadı.");
		} else
			System.out.println("Objelerden birisi null...");
		return false;
	}

	public static boolean insertAddress(LinkedHashMap<String, Object> params) {

		Object obj1 = params.get("Address");
		if (obj1 instanceof Address) {
			Address newAddress = (Address) obj1;

			session.beginTransaction();
			session.persist(newAddress);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}
}
