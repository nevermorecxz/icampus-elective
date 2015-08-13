package com.irengine.campus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irengine.campus.domain.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Long>{

	@Query("select t from Teacher t where t.th=:th and t.course.id=:id")
	List<Teacher> findAllByCourseIdAndTh(@Param("id")Long id, @Param("th")Integer th);

}
