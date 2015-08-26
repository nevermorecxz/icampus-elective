package com.irengine.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irengine.campus.domain.ClassHour;

public interface ClassHourDao extends JpaRepository<ClassHour, Long>{
	
}
