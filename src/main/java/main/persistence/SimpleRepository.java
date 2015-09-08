package main.persistence;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//static import main.user.User._email;

public class SimpleRepository<S, T> implements Repository<S, T> {
    @PersistenceContext
    EntityManager entityManager;

    private final Class<T> clazz;

    public SimpleRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T get(Query query) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public T get(S key) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public<U> List<T> getBy(String attributeName, U value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        all.where(cb.equal(rootEntry.get(attributeName), value));
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public<U> Optional<T> getFirstBy(String attributeName, U value) {
        List<T> result = getBy(attributeName, value);
        if (CollectionUtils.isEmpty(result)) {
            return Optional.empty();
        } else {
            return Optional.of(result.iterator().next());
        }
    }

    @Override
    public void delete(Query query) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void delete(S key) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int count(Query query) {
        throw new RuntimeException("Not implemented");
    }

    @Transactional
    @Override
    public void persist(T item) {
        entityManager.persist(item);
        entityManager.flush();
    }

    @Override
    public void merge(T item) {
        entityManager.merge(item);
    }

}
