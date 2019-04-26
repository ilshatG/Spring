package ru.job4j.controller.DAO;

import org.hibernate.*;
import org.springframework.stereotype.Component;
import ru.job4j.models.Ad;
import ru.job4j.models.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Data Access Object
 */
@Component
public class HibernateDao<T> implements Store {

    private final static SessionFactory factory = initFactory();

    @Override
    public List getListFromSQL(String query) {
        return this.tx(session -> {
            SQLQuery sqlQuery = session.createSQLQuery(query);
            sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

            List result = sqlQuery.list();
            return result;
        });
    }

    @Override
    public <T> List<T> getListFromSQL(String query, Class<T> tClass) {
        return this.tx(session -> {
            SQLQuery sqlQuery = session.createSQLQuery(query);

            sqlQuery.addEntity(tClass);
            List<T> result = sqlQuery.list();
            return result;
        });
    }

    private static SessionFactory initFactory() {
        SessionFactory result = null;
        try {
            result = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
        }
        return result;
    }


    public SessionFactory getFactory() {
        return factory;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            //tx.commit();
            if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
                tx.commit();
            }
            session.close();
        }
    }

    @Override
    public <T> boolean add(T t) {
        return this.tx(
                session -> {
                    session.saveOrUpdate(t);
                    return true;
                }
        );
    }

    public <T> T get(T t, long id) {
        return this.tx(
                session -> {
                    return (T)session.get(t.getClass(), id);
                }
        );
    }

    /**
     * Find id by name field.
     */
    public long getId(String name, String beanName) {
        return this.tx(
                session -> {
                    String queryString = String.format("SELECT b.id FROM %s b where b.name=:name", beanName);
                    Query query = session.createQuery(queryString);
                    query.setParameter("name", name);
                    return (Long)query.uniqueResult();
                }
        );
    }

    @Override
    public void update(Ad item) {

    }

    @Override
    public void delete(Ad item) {

    }

    @Override
    public Map<Long, Ad> getAll() {
        return null;
    }

    @Override
    public User currentUser(String login, String password) {
        return null;
    }

}
