package com.ansis.irems.gwa_proto.test.arq;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

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

import com.ansis.irems.gwa_proto.dao.IMessageTemplateDAO;
import com.ansis.irems.gwa_proto.dao.IWelcomeBoardDAO;
import com.ansis.irems.gwa_proto.model.MessageTemplate;
import com.ansis.irems.gwa_proto.model.WelcomeBoard;

@RunWith(Arquillian.class) 
public class WelcomeBoardDAOTest {
	@Deployment 
	public static JavaArchive createArchive() { 
		JavaArchive ja = ShrinkWrap.create(JavaArchive.class, "irems_test.jar") 
				.addPackage(WelcomeBoard.class.getPackage())
				.addPackage(IWelcomeBoardDAO.class.getPackage())
				.addAsManifestResource("test-persistence.xml","persistence.xml") 
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml"); 
		return ja; 
	} 

	@EJB 
	private IWelcomeBoardDAO wbDAO; 

	@EJB 
	private IMessageTemplateDAO mtDAO; 

	@PersistenceContext(unitName="irems_proto_test") 
	private EntityManager em; 

	@Inject 
	private UserTransaction utx; 

	@Before 
	public void setTestData() throws Exception { 
		utx.begin();
		em.createQuery("delete from WelcomeBoard wb").executeUpdate(); 
		utx.commit();
		WelcomeBoard wb1 = new WelcomeBoard("Cool Party");
		WelcomeBoard wb2 = new WelcomeBoard("Basic Event");
		MessageTemplate mt1 = new MessageTemplate("cool", "Life is cool {0}!", 1);
		wb1.setTemplate(mt1);
		MessageTemplate mt2 = new MessageTemplate("hello", "Hello {0}!", 2);
		wb2.setTemplate(mt2);
		wbDAO.create(wb1);
		wbDAO.create(wb2);
	}

	@Test 
	@InSequence(1)
	public void testLoadedBoards()  { 
		List<WelcomeBoard> wbs = wbDAO.findAll();
		assertThat(wbs.size(), is(2));
		List<MessageTemplate> mts = mtDAO.findAll();
		assertThat(mts.size(), is(2));
	}
	
	@Test
	@InSequence(2)
	public void testOperations() throws Exception {
		utx.begin();
		List<WelcomeBoard> wbs = wbDAO.findAll();
		wbDAO.makeTransient(wbs.get(0));
		utx.commit();

		wbs = wbDAO.findAll();
		assertThat(wbs.size(), is(1));
/*		mt1 = wbs.get(0);
		mt1.setRank(10);

		mt1 = mtDAO.makePersistent(mt1);
		UUID id = mt1.getId();

		utx.begin();
		MessageTemplate mt2 = mtDAO.findById(id);	
		assertThat(mt2.getRank(), is(10));
		// test equals
		assertThat(mt1, is(mt2)); 
		//clean
		mtDAO.makeTransient(mt2);
		utx.commit();
		//check clean
		assertThat(mtDAO.findAll().size(), is(0));
*/	}

	@After
	public void cleanUpDatabase() throws Exception {
		utx.begin();
		em.createQuery("delete from WelcomeBoard wb").executeUpdate(); 
		em.createQuery("delete from MessageTemplate mt").executeUpdate(); 
		utx.commit();

	}

}
