package com.nanda.model;

import lombok.Data;

@Data
public class Course {

	private Integer id;
	private String name;

	public Course(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Course() {
		// TODO Auto-generated constructor stub
	}

}
