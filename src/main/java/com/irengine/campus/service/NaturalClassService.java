package com.irengine.campus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irengine.campus.domain.NaturalClass;
import com.irengine.campus.repository.NaturalClassDao;

@Service
@Transactional
public class NaturalClassService {

	@Autowired
	private NaturalClassDao naturalClassDao;

	public NaturalClass findOneById(Long nclassId) {
		return naturalClassDao.findOne(nclassId);
	}

	public List<NaturalClass> findAllByTh(Integer th) {
		return naturalClassDao.findAllByTh(th);
	}
	
}
