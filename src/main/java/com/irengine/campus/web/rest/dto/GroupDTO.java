package com.irengine.campus.web.rest.dto;

import com.irengine.campus.domain.Course;

public class GroupDTO {

	//课程
	private Course course;
	
	//等级考/会考
	private boolean selected;
	
	//总数
	private Integer num;
	
	public GroupDTO() {
		super();
	}

	public GroupDTO(Course course, boolean selected, Integer num) {
		super();
		this.course = course;
		this.selected = selected;
		this.num = num;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
