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
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ansis.irems.gwa_proto.dao.IMessageTemplateDAO;
import com.ansis.irems.gwa_proto.model.MessageTemplate;

@RunWith(Arquillian.class) 
public class DAOTest {
	  @Deployment 
	  public static JavaArchive createArchive() { 
	    JavaArchive ja = ShrinkWrap.create(JavaArchive.class, "irems_test.jar") 
	        .addClass(MessageTemplate.class)
	        .addPackage(IMessageTemplateDAO.class.getPackage())
	        .addAsManifestResource("test-persistence.xml","persistence.xml") 
	        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml"); 
	    return ja; 
	  } 
	  
	  @EJB 
	  private IMessageTemplateDAO mtDAO; 

	  @PersistenceContext(unitName="irems_proto_test") 
	  private EntityManager em; 

	  @Inject 
	  private UserTransaction utx; 

	  @Before 
	  public void setTestData() throws Exception { 
	    utx.begin(); 
	    em.joinTransaction(); 
	    em.createQuery("delete from MessageTemplate mt").executeUpdate(); 
		MessageTemplate mt1 = new MessageTemplate("cool", "Life is cool {0}!", 1);
		MessageTemplate mt2 = new MessageTemplate("hello", "Hello {0}!", 2);
		mtDAO.joinTransaction();
		mtDAO.create(mt1);
		mtDAO.create(mt2);
	    utx.commit(); 
	  }
	  
	  @Test 
	  public void testLoadedTemplates() throws Exception { 
			utx.begin();
			mtDAO.joinTransaction();
			List<MessageTemplate> mts = mtDAO.findAll();
			assertThat(mts.size(), is(2));
			MessageTemplate mt1 = mts.get(1);
			mtDAO.makeTransient(mt1);
			utx.commit();
			
			mts = mtDAO.findAll();
			assertThat(mts.size(), is(1));
			mt1 = mts.get(0);
			mt1.setRank(10);
			
			utx.begin();
			mtDAO.joinTransaction();
			mt1 = mtDAO.makePersistent(mt1);
			UUID id = mt1.getId();
			utx.commit();

			utx.begin();		
			mtDAO.joinTransaction();
			MessageTemplate mt2 = mtDAO.findById(id);	
			assertThat(mt1.getRank(), is(10));
			assertThat(mt1, not(mt2));
			//clean
			mtDAO.makeTransient(mt2);
			utx.commit();
			//check clean
			assertThat(mtDAO.findAll().size(), is(0));
	  }
	  
	  @After
	  public void cleanUpDatabase() {
		  
	  }

}
