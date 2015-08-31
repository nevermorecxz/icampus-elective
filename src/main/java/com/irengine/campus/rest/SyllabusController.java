package com.irengine.campus.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irengine.campus.domain.ClassHour;
import com.irengine.campus.domain.ClassHourModule;
import com.irengine.campus.domain.Classroom;
import com.irengine.campus.domain.Course;
import com.irengine.campus.domain.Group;
import com.irengine.campus.domain.Preferences;
import com.irengine.campus.domain.Student;
import com.irengine.campus.domain.Teacher;
import com.irengine.campus.service.BaseSyllabusService;
import com.irengine.campus.service.PreferenceService;
import com.irengine.campus.service.StudentService;
import com.irengine.campus.service.TeacherService;
import com.irengine.campus.web.rest.dto.SyllabusInfoDTO;
import com.irengine.campus.web.rest.dto.SyllabusInfoDTO1;
import com.irengine.campus.web.rest.util.CreateExcel;

@RestController
@RequestMapping(value = "/syllabus")
public class SyllabusController {

	private static Logger logger = LoggerFactory.getLogger(SyllabusController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private PreferenceService preferencesService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private BaseSyllabusService baseSyllabusService;

	@RequestMapping(value = "/testExcel")
	public ResponseEntity<?> getExcel(@RequestParam("preferencesId") Long preferencesId,
			@RequestParam("studentId") Long studentId, @RequestParam("nClassId") Long nClassId,
			@RequestParam("teacherId") Long teacherId, @RequestParam("eType") String eType) {

		if (StringUtils.equals("student", eType)) {
			SyllabusInfoDTO syllabusInfo = (SyllabusInfoDTO) getSyllabusInfoDTOByStudent(preferencesId, studentId);
			return new ResponseEntity<>(syllabusInfo, HttpStatus.OK);
		} else if (StringUtils.equals("nClass", eType)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else if (StringUtils.equals("teacher", eType)) {
			SyllabusInfoDTO syllabusInfo = getSyllabusInfoDTOByTeacher(preferencesId, teacherId);
			return new ResponseEntity<>(syllabusInfo, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/excel")
	public ResponseEntity<?> excel(@RequestParam("preferencesId") Long preferencesId,
			@RequestParam("studentId") Long studentId, @RequestParam("nClassId") Long nClassId,
			@RequestParam("teacherId") Long teacherId, @RequestParam("eType") String eType, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.equals("student", eType)) {
			logger.debug("学生个人课表");
			try {
				createSyllabusByStudent(preferencesId, studentId, response);
			} catch (IOException e) {
				e.getStackTrace();
				return new ResponseEntity<>("创建学生课表失败", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else if (StringUtils.equals("teacher", eType)) {
			logger.debug("任课老师课表");
			try {
				createSyllabusByTeacher(preferencesId, teacherId, response);
			} catch (IOException e) {
				e.getStackTrace();
				return new ResponseEntity<>("创建任课老师课表失败", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(" ", HttpStatus.OK);
	}

	private void createSyllabusByTeacher(Long preferencesId, Long teacherId, HttpServletResponse response)
			throws IOException {
		SyllabusInfoDTO syllabusInfo = getSyllabusInfoDTOByTeacher(preferencesId, teacherId);
		CreateExcel.createSyllabusExcelByTeacher(syllabusInfo, response);
	}

	private SyllabusInfoDTO getSyllabusInfoDTOByTeacher(Long preferencesId, Long teacherId) {
		Teacher teacher = teacherService.findOneByTeacherId(teacherId);
		// 1L换成传入preferencesId会抛出空指针异常？
		Preferences preferences = preferencesService.findOneById(1L);
		SyllabusInfoDTO syllabusInfo = new SyllabusInfoDTO();
		// 任课老师课表标题
		syllabusInfo.setTitle(preferences.getName() + teacher.getBaseInfo().getName() + " 老师  课程表");
		List<ClassHourModule> classHourModules = new ArrayList<ClassHourModule>();
		classHourModules = baseSyllabusService.findOneById(1L).getClassHourModules();
		List<SyllabusInfoDTO1> syllabusInfo1s = new ArrayList<SyllabusInfoDTO1>();
		for (int i = 0; i < classHourModules.size(); i++) {
			for (Group group : classHourModules.get(i).getGroups()) {
				if (group.getTeacher().getId() == teacherId) {
					SyllabusInfoDTO1 syllabusInfo1 = new SyllabusInfoDTO1();
					// Classroom classroom = group.
					syllabusInfo1.setClassHours(classHourModules.get(i).getClassHours());
					syllabusInfo1.setCourse(group.getCourse());
					syllabusInfo1s.add(syllabusInfo1);
				}
			}
		}
		syllabusInfo.setSyllabusInfo1s(syllabusInfo1s);
		return syllabusInfo;
	}

	private void createSyllabusByStudent(Long preferencesId, Long studentId, HttpServletResponse response)
			throws IOException {
		SyllabusInfoDTO syllabusInfo = getSyllabusInfoDTOByStudent(preferencesId, studentId);
		CreateExcel.createSyllabusExcelByStudent(syllabusInfo, response);
	}

	private SyllabusInfoDTO getSyllabusInfoDTOByStudent(Long preferencesId, Long studentId) {
		logger.debug("test");
		Student student = studentService.findOneById(studentId);
		// Preferences preferences =
		// preferencesService.findOneById(preferencesId);
		SyllabusInfoDTO syllabusInfo = new SyllabusInfoDTO();
		// 学生个人课表标题
		syllabusInfo.setTitle(student.getnClass().getClassInfo() + "  " + student.getBaseInfo().getName() + " 课程表");
		/* 得到学生上课时间 */
		List<ClassHour> classHours = new ArrayList<ClassHour>();
		List<Group> groups = student.getGroups();
		List<ClassHourModule> classHourModules = new ArrayList<ClassHourModule>();
		classHourModules = baseSyllabusService.findOneById(1L).getClassHourModules();
		List<SyllabusInfoDTO1> syllabusInfo1s = new ArrayList<SyllabusInfoDTO1>();
		/*
		 * 判断该学生排课后的每一门课属于哪个课时块 得到每门课对应的相关信息（上课时间、地点、老师、科目）
		 */
		for (Group group : groups) {
			for (int i = 0; i < classHourModules.size(); i++) {
				SyllabusInfoDTO1 syllabusInfo1 = new SyllabusInfoDTO1();
				if (classHourModules.get(i).getGroups().contains(group)) {
					classHours = classHourModules.get(i).getClassHours();
					Teacher teacher = group.getTeacher();
					Course course = group.getCourse();
					syllabusInfo1.setTeacher(teacher);
					syllabusInfo1.setCourse(course);
					syllabusInfo1.setClassHours(classHours);
					syllabusInfo1s.add(syllabusInfo1);
					break;
				}
			}
		}
		syllabusInfo.setSyllabusInfo1s(syllabusInfo1s);
		return syllabusInfo;
	}

	// test
	@RequestMapping("/test")
	public ResponseEntity<?> test() {
		return new ResponseEntity<>(getSyllabusInfoDTOByTeacher(1L, 1L), HttpStatus.OK);
	}
}
