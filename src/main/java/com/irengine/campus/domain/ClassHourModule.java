package com.irengine.campus.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ELE_CLASS_HOUR_MODULE")
public class ClassHourModule extends BaseEntity implements Comparable<ClassHourModule>{
	
	private Integer hours;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="class_hour_module_id")
	private List<Group> groups=new ArrayList<Group>();

	@OneToMany
	private List<ClassHour> classHours = new ArrayList<ClassHour>();
	
	public List<ClassHour> getClassHours() {
		return classHours;
	}

	public void setClassHours(List<ClassHour> classHours) {
		this.classHours = classHours;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	@Override
	public int compareTo(ClassHourModule o) {
		return o.getHours()-hours;
	}

}
