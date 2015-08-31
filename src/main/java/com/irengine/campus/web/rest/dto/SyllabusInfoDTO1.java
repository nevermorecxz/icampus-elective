package com.irengine.campus.web.rest.dto;

import java.util.List;

import com.irengine.campus.domain.ClassHour;
import com.irengine.campus.domain.Classroom;
import com.irengine.campus.domain.Course;
import com.irengine.campus.domain.Teacher;

public class SyllabusInfoDTO1 {

	private Teacher teacher;
	
	private Course course;
	
	private Classroom classroom;
	
	private List<ClassHour> classHours;

	public SyllabusInfoDTO1() {
		super();
	}

	public SyllabusInfoDTO1(Teacher teacher, Course course ,Classroom classroom, List<ClassHour> classHours) {
		super();
		this.teacher = teacher;
		this.course = course;
		this.classroom = classroom;
		this.classHours = classHours;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public List<ClassHour> getClassHours() {
		return classHours;
	}

	public void setClassHours(List<ClassHour> classHours) {
		this.classHours = classHours;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
