package com.irengine.campus.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.irengine.campus.domain.Teacher;

public class TeacherAndGroups implements Comparable<TeacherAndGroups>{

	private Teacher teacher;
	
	private List<GroupDTO> groupDTOs=new ArrayList<GroupDTO>();

	public TeacherAndGroups() {
		super();
	}

	public TeacherAndGroups(Teacher teacher, List<GroupDTO> groupDTOs) {
		super();
		this.teacher = teacher;
		this.groupDTOs = groupDTOs;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<GroupDTO> getGroupDTOs() {
		return groupDTOs;
	}

	public void setGroupDTOs(List<GroupDTO> groupDTOs) {
		this.groupDTOs = groupDTOs;
	}

	@Override
	public int compareTo(TeacherAndGroups o) {
		int levelPeriod=teacher.getCourse().getLevelPeriod();
		int unifiedPeriod=teacher.getCourse().getUnifiedPeriod();
		int oLevelPeriod=o.getTeacher().getCourse().getLevelPeriod();
		int oUnifiedPeriod=o.getTeacher().getCourse().getUnifiedPeriod();
		return (oLevelPeriod>oUnifiedPeriod?oLevelPeriod:oUnifiedPeriod)-(levelPeriod>unifiedPeriod?levelPeriod:unifiedPeriod);
	}
	
}
