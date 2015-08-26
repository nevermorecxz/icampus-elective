package com.irengine.campus.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 排课参数
 * @author huang
 *
 */
@Entity
@Table(name="ele_arrange_course")
public class ArrangeCourse extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 7999186666210418284L;

	//第几届
	private Integer th;
	
	//排课年份
	private Integer year;
	
	//排课上/下学期
	private Integer term;
	
	@Column(length=100)
	//命名(默认为创建时间)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Group> groups = new ArrayList<Group>();
	
	//创建时间
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createdTime;

	public ArrangeCourse() {
		super();
	}

	public ArrangeCourse(Integer th, Integer year, Integer term, String name,
			Date createdTime) {
		super();
		this.th = th;
		this.year = year;
		this.term = term;
		this.name = name;
		this.createdTime = createdTime;
	}

	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Integer getTh() {
		return th;
	}

	public void setTh(Integer th) {
		this.th = th;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
