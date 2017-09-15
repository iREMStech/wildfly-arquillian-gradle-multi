package com.ansis.irems.gwa_proto;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.UUID;

import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.ansis.irems.gwa_proto.dao.IMessageTemplateDAO;
import com.ansis.irems.gwa_proto.dao.MessageTemplateDAOImpl;


public class PersistenceTest {

	private EntityManager em;
	MessageTemplateDAOImpl mtDAO;

	@Before
	public void setUp() {
		em = JPAUtil.getEntityManagerFactory().createEntityManager();
		mtDAO =  new MessageTemplateDAOImpl();
		mtDAO.setEntityManager(em);
	}
	
	@Test
	public void testCreate() throws InstantiationException, IllegalAccessException, ClassNotFoundException {			
		MessageTemplate mt = new MessageTemplate("cool", "Life is cool {0}!", 1);
		em.getTransaction().begin();
		// check empty id
		assertThat(mt.getId(), nullValue(UUID.class));
		mtDAO.create(mt);
		// check id generation by create
		UUID id = mt.getId();
		assertThat(mt.getId(), notNullValue(UUID.class));
		em.getTransaction().rollback();
		//check merge after rollback
		em.getTransaction().begin();
		MessageTemplate mt2 = mtDAO.makePersistent(mt);
		assertThat(id, not(mt2.getId()));
		assertThat(mt2, not(mt));	
		em.getTransaction().commit();
		assertThat(mtDAO.findAll().size(), is(1));
		//clean
		em.getTransaction().begin();		
		mtDAO.makeTransient(mt2);
		em.getTransaction().commit();
		//check clean
		assertThat(mtDAO.findAll().size(), is(0));
	}

	@After
	public void tearDown() {
	    em.close();
	    JPAUtil.shutdown();
	}
}
