package com.irengine.campus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ELE_CLASSROOM")
public class Classroom extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 6549547605402353730L;
	
	private String address;

	public Classroom() {
		super();
	}

	public Classroom(String address) {
		super();
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
