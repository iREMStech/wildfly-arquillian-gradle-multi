package com.ansis.irems.gwa_proto.test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import com.ansis.irems.gwa_proto.model.Guest;

public class GuestPojoTest {

	@Test
	public void testConstructorAndGetterAndEquals() {
		Date today = new Date();
		Guest g1 = new Guest("Batman", today);
		assertThat(g1.getName(), is("Batman"));
		assertThat(g1.getArrivalDate(), is(today));
		System.out.println(g1);
		//check equals
		Guest g2 = new Guest("Batman", today);
		assertThat(g2, is(g1));
	}
}
