package com.irengine.campus.domain;

import java.util.ArrayList;
import java.util.List;

public class ClassHourModule implements Comparable<ClassHourModule>{
	
	private Integer hours;
	
	private List<Group> groups=new ArrayList<Group>();

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
