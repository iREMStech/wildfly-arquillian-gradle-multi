package com.ansis.irems.gwa_proto.dao;

import java.util.UUID;

import javax.ejb.Stateless;

import com.ansis.irems.gwa_proto.model.MessageTemplate;

@Stateless
public class MessageTemplateDAOImpl extends GenericDAOImpl<MessageTemplate, UUID>
	implements IMessageTemplateDAO {
	
	private static final long serialVersionUID = 1L;

	public MessageTemplateDAOImpl() {
		super(MessageTemplate.class);
	}

}
