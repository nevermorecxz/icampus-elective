package com.irengine.campus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irengine.campus.domain.Course;
import com.irengine.campus.domain.Group;
import com.irengine.campus.domain.NaturalClass;
import com.irengine.campus.repository.GroupDao;

@Service
@Transactional
public class GroupService {

	@Autowired
	private GroupDao groupDao;

//	public List<Group> findOneByNaturalClassAndCourseFromList(NaturalClass getnClass,
//			Course course, List<Group> groups) {
//		List<Group> groups2=new ArrayList<Group>();
//		for(Group group:groups){
//			if(group.getNaturalClass().getId()==getnClass.getId()&&group.getCourse().getId()==course.getId()){
//				groups2.add(group);
//			}
//		}
//		return groups2;
//	}

	public List<Group> deleteAllWhereSizeIsZero(List<Group> levelGroups) {
		for(int i=0;i<levelGroups.size();i++){
			if(levelGroups.get(i).getStudentSize()==0){
				levelGroups.remove(i);
				i--;
			}
		}
		return levelGroups;
	}

	public List<Group> findAllByCourseFromList(Long id, List<Group> levelGroups) {
		List<Group> groups=new ArrayList<Group>();
		for(Group group:levelGroups){
			if(group.getCourse().getId()==id){
				groups.add(group);
			}
		}
		return groups;
	}

	public int getTotalNum(List<Group> groups) {
		int num=0;
		for(Group group:groups){
			num+=group.getStudentSize();
		}
		return num;
	}

	public List<Group> save(List<Group> needSaveGroups) {
		return groupDao.save(needSaveGroups);
	}

}
