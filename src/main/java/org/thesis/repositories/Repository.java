package org.thesis.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public class Repository<T, ID> {

    @Inject
    EntityManager em;

    Class<T> clazz;

    protected Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Transactional
    public T findById(ID primaryKey) {
        return em.find(clazz, primaryKey);
    }

    @Transactional
    public List<T> findByField(String fieldName, Object fieldValue) {
        String query = "select t from " + clazz.getSimpleName() + " t where " + fieldName + " =? 1";
        System.out.println(query);
        return em.createQuery(query, clazz)
                .setParameter(1, fieldValue)
                .getResultList();
    }

    @Transactional
    public List<T> findAll() {
        String query = "select t from " + clazz.getSimpleName() + " t";
        return em.createQuery(query, clazz).getResultList();
    }

    @Transactional
    public T save(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Transactional
    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Transactional
    public boolean existsById(ID primaryKey) {
        return null != findById(primaryKey);
    }

    @Transactional
    public boolean existsByField(String fieldName, Object fieldValue) {
        Collection<T> byField = findByField(fieldName, fieldValue);
        return null != byField && !byField.isEmpty();
    }
}
