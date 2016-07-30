package com.rest.template.controllers.requests;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class BasicRequest {
	
	private Integer id;
	@NotEmpty
	@Size(max=20)
	private String content;
	
	public BasicRequest(String content) {
		this.id = (int)(Math.random() * 1000);
		this.content = content;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
