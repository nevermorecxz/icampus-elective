package com.irengine.campus.web.rest.dto;

import java.util.List;


public class SyllabusInfoDTO {

	private String title;
	
	private List<SyllabusInfoDTO1> syllabusInfo1s;

	public SyllabusInfoDTO() {
		super();
	}

	public SyllabusInfoDTO(String title, List<SyllabusInfoDTO1> syllabusInfo1s) {
		super();
		this.title = title;
		this.syllabusInfo1s = syllabusInfo1s;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SyllabusInfoDTO1> getSyllabusInfo1s() {
		return syllabusInfo1s;
	}

	public void setSyllabusInfo1s(List<SyllabusInfoDTO1> syllabusInfo1s) {
		this.syllabusInfo1s = syllabusInfo1s;
	}
	
}