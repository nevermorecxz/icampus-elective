package com.irengine.campus.service;

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

}
