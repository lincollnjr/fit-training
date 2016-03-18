package persistencia.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessao;
	
	private static SessionFactory buildSessionFactory() {
		try {

			Configuration configuration = new Configuration();

			configuration.configure();
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

			serviceRegistryBuilder.applySettings(configuration.getProperties());

			ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

			return configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable e) {
			throw new ExceptionInInitializerError("Criacao do objeto falhou: "
					+ e);
		}
	}

	public static SessionFactory getSessionFactory() {
		sessao = buildSessionFactory();
		return sessao;
		
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
