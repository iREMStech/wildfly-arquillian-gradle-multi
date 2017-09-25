package com.ansis.irems.gwa_proto.test.arq;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ansis.irems.gwa_proto.dao.IGuestDAO;
import com.ansis.irems.gwa_proto.dao.IMessageTemplateDAO;
import com.ansis.irems.gwa_proto.model.Guest;
import com.ansis.irems.gwa_proto.model.MessageTemplate;

@RunWith(Arquillian.class) 
public class GuestDAOTest {
	@Deployment 
	public static JavaArchive createArchive() { 
		JavaArchive ja = ShrinkWrap.create(JavaArchive.class, "irems_test.jar") 
				.addPackage(Guest.class.getPackage())
				.addPackage(IGuestDAO.class.getPackage())
				.addAsManifestResource("test-persistence.xml","persistence.xml") 
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml"); 
		return ja; 
	} 

	@EJB 
	private IGuestDAO gDAO; 

	@PersistenceContext(unitName="irems_proto_test") 
	private EntityManager em; 

	@Inject 
	private UserTransaction utx; 

	@Before 
	public void setTestData() throws Exception { 
		utx.begin();
		em.createQuery("delete from Guest g").executeUpdate(); 
		utx.commit();
		Date today = new Date();
		Guest g1 = new Guest("Batman", today );
		Guest g2 = new Guest("Wonder Woman", new Date(today.getTime()-1000));
		gDAO.create(g1);
		gDAO.create(g2);
	}

	@SuppressWarnings("deprecation")
	@Test 
	@InSequence(1)
	public void testLoadedGuests() throws Exception { 
		// we need outer transaction context to keep Guest entities in managed state 
		// after getting them by gDAO.findAll() 	
		utx.begin(); 
		List<Guest> gs = gDAO.findAll();
		assertThat(gs.size(), is(2));
		Guest g1 = gs.get(1);

		gDAO.makeTransient(g1);
		utx.commit();

		gs = gDAO.findAll();
		assertThat(gs.size(), is(1));
		g1 = gs.get(0);
		Date ad = new Date(new Date().getTime()-10000);
		ad = new Date(ad.getYear(),ad.getMonth(),ad.getDate()); //deprecated but efficient, alternative is DateUtil (apache)
		g1.setArrivalDate(ad);

		g1 = gDAO.makePersistent(g1);
		ad = g1.getArrivalDate();
		UUID id = g1.getId();

		utx.begin();
		Guest g2 = gDAO.findById(id);	
		assertThat(g2.getArrivalDate(), is(ad));
		// test equals
		assertThat(g1, is(g2)); 
		//clean
		gDAO.makeTransient(g2);
		utx.commit();
		//check clean
		assertThat(gDAO.findAll().size(), is(0));
	}

	@After
	public void cleanUpDatabase() {

	}

}
