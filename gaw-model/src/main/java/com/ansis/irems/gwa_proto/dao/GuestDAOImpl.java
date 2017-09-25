package com.ansis.irems.gwa_proto.dao;

import java.util.UUID;

import javax.ejb.Stateless;

import com.ansis.irems.gwa_proto.model.Guest;

@Stateless
public class GuestDAOImpl extends GenericDAOImpl<Guest, UUID>
	implements IGuestDAO {
	
	private static final long serialVersionUID = 1L;

	public GuestDAOImpl() {
		super(Guest.class);
	}

}
