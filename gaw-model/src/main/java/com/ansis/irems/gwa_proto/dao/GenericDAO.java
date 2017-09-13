package com.ansis.irems.gwa_proto.dao;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

/**
 * An interface shared by all business data access objects.
 * <p>
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared across all DAO implementations.
 * The current design is for a state-management oriented persistence layer
 * (for example, there is no UPDATE statement function) which provides
 * automatic transactional dirty checking of business objects in persistent
 * state.
 */
public interface GenericDAO<T, ID extends Serializable>
    extends Serializable {

    void joinTransaction();

    T findById(ID id);

    T findById(ID id, LockModeType lockModeType);

    List<T> findAll();

    T makePersistent(T entity);

    void makeTransient(T entity);
}
