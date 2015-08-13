package com.irengine.campus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ele_basic_settings")
public class BasicSettings extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -7113282163484864851L;

	//第几届
	private Integer th;
	
	//考试科目数
	private Integer numOfSubjects;
	
	//分配给每个班的参考人数
	private Integer numOfClass;

	public BasicSettings() {
		super();
	}

	public BasicSettings(Integer th, Integer numOfSubjects, Integer numOfClass) {
		super();
		this.th = th;
		this.numOfSubjects = numOfSubjects;
		this.numOfClass = numOfClass;
	}

	public Integer getTh() {
		return th;
	}

	public void setTh(Integer th) {
		this.th = th;
	}

	public Integer getNumOfSubjects() {
		return numOfSubjects;
	}

	public void setNumOfSubjects(Integer numOfSubjects) {
		this.numOfSubjects = numOfSubjects;
	}

	public Integer getNumOfClass() {
		return numOfClass;
	}

	public void setNumOfClass(Integer numOfClass) {
		this.numOfClass = numOfClass;
	}
	
}
