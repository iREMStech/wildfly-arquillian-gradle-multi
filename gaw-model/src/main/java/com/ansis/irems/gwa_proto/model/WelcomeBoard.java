package com.ansis.irems.gwa_proto.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

//import java.text.MessageFormat;

/**
 * POJO class for defining welcome messages for guests
 */
@Entity
@Table(name="WELCOME_BOARD")
public class WelcomeBoard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	    name = "UUID",
	    strategy = "org.hibernate.id.UUIDGenerator"
	)
	protected UUID id;
	  
	@NotNull
	@Column(nullable = false)
	@Size(max = 100)
	protected String name;
		
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "MESSAGE_TEMPLATE_ID", nullable = true)
	protected MessageTemplate template;
	
	public WelcomeBoard() {}
	
	public WelcomeBoard(String name) {
		this.name = name;
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

	public MessageTemplate getTemplate() {
		return template;
	}

	public void setTemplate(MessageTemplate template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "WelcomeBoard [id=" + id + ", name=" + name + ", template=" + template + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((template == null) ? 0 : template.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WelcomeBoard other = (WelcomeBoard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		return true;
	}
}