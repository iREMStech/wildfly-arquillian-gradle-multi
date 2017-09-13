package com.ansis.irems.gwa_proto;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PojoTest {

	@Test
	public void testConstructorAndGetter() {
		  MessageTemplate underTest = new MessageTemplate("cool", "Life is cool {0}!", 1);
		  assertThat(underTest.getName(), is("cool"));
		  assertThat(underTest.getTemplate(), is("Life is cool {0}!"));
		  assertThat(underTest.getRank(), is(1));
		}
}
