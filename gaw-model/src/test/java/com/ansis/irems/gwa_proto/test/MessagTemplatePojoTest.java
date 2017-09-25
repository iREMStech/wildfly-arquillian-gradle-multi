package com.ansis.irems.gwa_proto.test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ansis.irems.gwa_proto.model.MessageTemplate;

public class MessagTemplatePojoTest {

	@Test
	public void testConstructorAndGetterAndEquals() {
		  MessageTemplate mt1 = new MessageTemplate("cool", "Life is cool {0}!", 1);
		  assertThat(mt1.getName(), is("cool"));
		  assertThat(mt1.getTemplate(), is("Life is cool {0}!"));
		  assertThat(mt1.getRank(), is(1));
		  System.out.println(mt1);
		  MessageTemplate mt2 = new MessageTemplate("cool", "Life is cool {0}!", 1);
		  assertThat(mt1, is(mt2));
		}
}
