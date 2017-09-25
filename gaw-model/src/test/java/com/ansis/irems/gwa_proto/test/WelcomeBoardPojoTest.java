package com.ansis.irems.gwa_proto.test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ansis.irems.gwa_proto.model.MessageTemplate;
import com.ansis.irems.gwa_proto.model.WelcomeBoard;

public class WelcomeBoardPojoTest {

	@Test
	public void testConstructorAndGetterAndEquals() {
		  WelcomeBoard wb = new WelcomeBoard("Cool Party");
		  assertThat(wb.getName(), is("Cool Party"));
		  MessageTemplate mt1 = new MessageTemplate("cool", "Life is cool {0}!", 1);
		  wb.setTemplate(mt1);
		  assertThat(wb.getTemplate().getName(), is("cool"));
		  System.out.println(wb);
		  MessageTemplate mt2 = new MessageTemplate("cool", "Life is cool {0}!", 1);
		  WelcomeBoard wb2 = new WelcomeBoard("Cool Party");
		  wb2.setTemplate(mt2);
		  assertThat(wb, is(wb2));
		}
}
