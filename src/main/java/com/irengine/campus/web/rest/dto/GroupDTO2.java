package com.irengine.campus.web.rest.dto;

import com.irengine.campus.domain.Course;
import com.irengine.campus.domain.Teacher;

public class GroupDTO2 {
	//课程
	private Course course;
	
	//等级考/会考
	private boolean selected;
	
	//总数
	private Integer num;
	
	private Teacher teacher;

	public GroupDTO2() {
		super();
	}

	public GroupDTO2(Course course, boolean selected, Integer num,
			Teacher teacher) {
		super();
		this.course = course;
		this.selected = selected;
		this.num = num;
		this.teacher = teacher;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
