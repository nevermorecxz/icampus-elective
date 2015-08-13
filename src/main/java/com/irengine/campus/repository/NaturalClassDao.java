package com.irengine.campus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irengine.campus.domain.NaturalClass;

public interface NaturalClassDao extends JpaRepository<NaturalClass, Long> {

	@Query("select n from NaturalClass n where n.attendance=:th")
	List<NaturalClass> findAllByTh(@Param("th") Integer th);

}
