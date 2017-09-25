package com.ansis.irems.gwa_proto.dao;

import java.util.UUID;

import javax.ejb.Stateless;

import com.ansis.irems.gwa_proto.model.WelcomeBoard;

@Stateless
public class WelcomeBoardDAOImpl extends GenericDAOImpl<WelcomeBoard, UUID>
	implements IWelcomeBoardDAO {
	
	private static final long serialVersionUID = 1L;

	public WelcomeBoardDAOImpl() {
		super(WelcomeBoard.class);
	}

}
