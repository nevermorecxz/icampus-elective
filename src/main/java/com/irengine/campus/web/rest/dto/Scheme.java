package com.irengine.campus.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生排课方案
 * @author huang
 *
 */
public class Scheme {
	
	private int scores;
	
	private List<GroupDTO3> groupDTO3s=new ArrayList<GroupDTO3>();

	public Scheme() {
		super();
	}

	public Scheme(int scores, List<GroupDTO3> groupDTO3s) {
		super();
		this.scores = scores;
		this.groupDTO3s = groupDTO3s;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public List<GroupDTO3> getGroupDTO3s() {
		return groupDTO3s;
	}

	public void setGroupDTO3s(List<GroupDTO3> groupDTO3s) {
		this.groupDTO3s = groupDTO3s;
	}

}
