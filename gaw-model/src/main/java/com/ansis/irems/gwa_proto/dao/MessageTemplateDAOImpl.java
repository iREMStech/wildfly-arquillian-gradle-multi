package com.ansis.irems.gwa_proto.dao;

import java.util.UUID;

import javax.ejb.Stateless;

import com.ansis.irems.gwa_proto.MessageTemplate;

@Stateless
public class MessageTemplateDAOImpl extends GenericDAOImpl<MessageTemplate, UUID>{
	
	public MessageTemplateDAOImpl() {
		super(MessageTemplate.class);
	}

}
