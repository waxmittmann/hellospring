package main.persistence;

import java.util.List;

public interface Repository<S, T> {
    public T get(Query query);
    public T get(S key);
    public<U> List<T> getBy(String attributeName, U value);
    public List<T> getAll();
    public void delete(Query query);
    public void delete(S key);
    public int count(Query query);
    void persist(T newHello);
    void merge(T newHello);
}
