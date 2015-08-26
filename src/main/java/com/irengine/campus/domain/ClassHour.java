package com.irengine.campus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 上课具体时间，周几第几节课
 * 
 * @author wujing
 */

@Entity
@Table(name = "ele_class_hour")
public class ClassHour extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2256905413550196907L;

	private Integer day;

	private Integer hour;

	public ClassHour() {
		super();
	}

	public ClassHour(Integer day, Integer hour) {
		super();
		this.day = day;
		this.hour = hour;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public String toString() {
		return day.toString() + hour.toString();
	}
	
	public ClassHour toClass(String info){
		ClassHour classHour = new ClassHour();
		classHour.day = Integer.valueOf(String.valueOf(info.charAt(0)));
		classHour.hour = Integer.valueOf(String.valueOf(info.charAt(2)));
		return classHour;
	}
}
