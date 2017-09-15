package com.ansis.irems.gwa_proto.dao;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.LockModeType.*;

public abstract class GenericDAOImpl<T, ID extends Serializable>
        implements IGenericDAO<T, ID> {

	private static final long serialVersionUID = 1L;
	@PersistenceContext	protected EntityManager em;
    protected final Class<T> entityClass;

    protected GenericDAOImpl( Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public void joinTransaction() {
        if (!em.isJoinedToTransaction())
            em.joinTransaction();
    }

    @Override
    public T findById(ID id) {
        return findById(id, NONE);
    }

    @Override
    public T findById(ID id, LockModeType lockModeType) {
        return em.find(entityClass, id, lockModeType);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> c = em.getCriteriaBuilder().createQuery(entityClass);
        c.select(c.from(entityClass));
        return em.createQuery(c).getResultList();
    }

    @Override
    public void create(T instance) {
        em.persist(instance);
    }
    
    @Override
    public T makePersistent(T instance) {
        // merge() handles transient AND detached instances
        return em.merge(instance);
    }

    @Override
    public void makeTransient(T instance) {
        em.remove(instance);
    }
    // ...
}
