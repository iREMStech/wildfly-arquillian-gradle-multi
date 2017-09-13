package com.ansis.irems.gwa_proto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

//import java.text.MessageFormat;

/**
 * POJO class for defining message template
 *  for example ("hello", "Hello, {0}!")
 */
@Entity
@Table(name="MESSAGE_TEMPLATE")
public class MessageTemplate {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	    name = "UUID",
	    strategy = "org.hibernate.id.UUIDGenerator"
	)
	protected UUID id;
	  
	@NotNull
	@Column(nullable = false)
	@Size(max = 12)
	protected String name;
	
	@Column
	protected String template;
	
	@Column
	private Integer rank;
		
	public MessageTemplate(String name, String template, Integer rank) {
		this.name = name;
		this.template = template;
		this.rank = rank;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
}