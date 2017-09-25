package com.ansis.irems.gwa_proto.test.arq;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.arquillian.ape.rdbms.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ansis.irems.gwa_proto.dao.IMessageTemplateDAO;
import com.ansis.irems.gwa_proto.model.MessageTemplate;

//@RunWith(Arquillian.class) 
public class DAOAPETest {
/*	@Deployment 
	public static JavaArchive createArchive() { 
		JavaArchive ja = ShrinkWrap.create(JavaArchive.class, "irems_test.jar") 
				.addClass(MessageTemplate.class)
				.addPackage(IMessageTemplateDAO.class.getPackage())
				.addClass(org.postgresql.util.PGobject.class)
				.addAsManifestResource("test-persistence.xml","persistence.xml") 
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml"); 
		return ja; 
	} 

	@EJB 
	private IMessageTemplateDAO mtDAO; 

	@Test 
	@UsingDataSet("MessageTemplates.xml")
	public void shouldFindAllMessageTemplates() throws Exception { 
		//when
		List<MessageTemplate> mts = mtDAO.findAll();
		//then
		assertThat(mts.size(), is(2));
	}

	@After
	public void cleanUpDatabase() {

	}
*/
}
