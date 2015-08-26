package com.irengine.campus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irengine.campus.domain.BaseSyllabus;
import com.irengine.campus.repository.BaseSyllabusDao;

@Service
@Transactional
public class BaseSyllabusService {

	@Autowired
	private BaseSyllabusDao baseSyllabusDao;
	
	public  BaseSyllabus save(BaseSyllabus baseSyllabus){
		baseSyllabus = baseSyllabusDao.save(baseSyllabus);
		return baseSyllabus;
	}
}
