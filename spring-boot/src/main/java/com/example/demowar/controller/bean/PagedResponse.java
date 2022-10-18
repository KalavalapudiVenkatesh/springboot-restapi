package com.example.demowar.controller.bean;

import java.util.List;



public class PagedResponse<T> {
	
	private List<T> content;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
   



}
