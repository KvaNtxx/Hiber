import org.hibernate.*;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import HiberEntity.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by n.litvyak on 14.01.2016.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;
    static {
        try {
            System.setProperty("org.jboss.logging.provider","log4j");

            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        HiberEntityGetters hiberEntityGetters = new HiberEntityGetters();
  //      HiberEntity hiberEntity = hiberEntityGetters.getHiberById("CCC71");
  //      System.out.println(hiberEntity.getAge());
  //      saveHiber();
        List<HiberEntity> entityList= hiberEntityGetters.getAllLike("CCC%");
        for(HiberEntity entity: entityList)
        {
            System.out.println(entity.toString());
        }
    }

    public static void PrintAllEntities()
    {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
    public static boolean saveHiber()
    {
        final Session session = getSession();
        try {
            HiberEntity hiberEntity = new HiberEntity();
            hiberEntity.setAge(new Double(Math.random()*100).intValue()+"");
            hiberEntity.setCount(new Double(Math.random()*100).intValue());
            hiberEntity.setName("CCC"+hiberEntity.getCount());
            Transaction tr1 = session.beginTransaction();
            session.save(hiberEntity);
            session.flush();
            tr1.commit();
        } finally {
            session.close();
        }
        return true;
    }
}
