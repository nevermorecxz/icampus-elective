package com.irengine.campus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irengine.campus.domain.ClassHour;
import com.irengine.campus.repository.ClassHourDao;

@Service
@Transactional
public class ClassHourService {

	@Autowired
	private ClassHourDao classHourDao;

	public ClassHour save(ClassHour classHour) {
		return classHour = classHourDao.save(classHour);
	}
	


//	public List<ClassHour> getClassHours(List<String> infos) {
//		List<ClassHour> classHours = new ArrayList<ClassHour>();
//		for(int i=0;i<infos.size();i++){
//		ClassHour classHour = new ClassHour();
//		classHours.set(i,classHour.toField(infos.get(i)));
//		}
//		return classHours;
//	}

//	public boolean compare(ClassHour one, ClassHour another) {
//		if (one.getDay() != another.getDay()) {
//			return true;
//		} else if (Math.abs(one.getHour() - another.getDay()) == 1) {
//			return true;
//		}
//		return false;
//	}
//
//	public boolean compareClassHours(List<ClassHour> classHours) {
//		for (int i = 0; i < classHours.size(); i++) {
//			for (int j = i + 1; j < classHours.size(); j++) {
//				if (!compare(classHours.get(i), classHours.get(j))) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
}
