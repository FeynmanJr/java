package operations;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Phone;

public class PhoneOps {
    public enum columns {
        KEY("key"),
        Phone_ID("PhoneId"),
        NAME("name"),
        PHONE("phone");

        private String columnName;

        columns(String columnName){
            this.columnName = columnName;
        }

        public String getColumnName() {
            return columnName;
        }
    }

    static Configuration configuration;
	static Session session;

	static {
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Phone.class);
		session = configuration.buildSessionFactory().openSession();
	}

	public static boolean getPhones() {
		List<Phone> custResult = session.createQuery("FROM Phone", Phone.class).list();

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

	public static boolean deletePhoneWithValue(LinkedHashMap<String, Object> params) {
		System.out.println("WORKİNG");
		Object obj2 = params.get("column");
		Object obj3 = params.get("value");
		if (/*obj2 instanceof String &&*/ obj3 instanceof String) {
			String column = (String) obj2;
			String value = (String) obj3;
			session.beginTransaction();
			Query<Phone> query = session.createQuery("FROM Phone WHERE " + column + " = :" + column + "",
					Phone.class);
			query.setParameter(column, value);
			List<Phone> custResultRemove = query.list();
			if (custResultRemove != null) {
				for (Phone res : custResultRemove)
					session.remove(res);
				session.getTransaction().commit();
				return true;
			} else
			System.out.println(column + " bilgisi " + value + " olan bir kayıt bulunamadı.");
		} else
			System.out.println("Objelerden birisi null...");
		return false;
	}

	public static boolean insertPhone(LinkedHashMap<String, Object> params) {

		Object obj1 = params.get("Phone");
		if (obj1 instanceof Phone) {
			Phone newPhone = (Phone) obj1;

			session.beginTransaction();
			session.persist(newPhone);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}
}
