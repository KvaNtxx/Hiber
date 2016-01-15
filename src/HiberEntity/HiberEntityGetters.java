package HiberEntity;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n.litvyak on 14.01.2016.
 */
public class HiberEntityGetters {
    public HiberEntity getHiberById(String name) {
        Session session = null;
        HiberEntity hiberEntity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            hiberEntity =(HiberEntity)  session.get(HiberEntity.class,name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return hiberEntity;
    }

    public List<HiberEntity> getAllLike(String name)
    {
        Session session = null;
        List<HiberEntity> hiberEntitys = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from HiberEntity where name like :code ");
            query.setParameter("code", name);
            hiberEntitys = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return hiberEntitys;
    }
}
