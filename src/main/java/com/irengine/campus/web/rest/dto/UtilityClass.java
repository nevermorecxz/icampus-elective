package com.irengine.campus.web.rest.dto;

/**
 * 判断该课时区可容下的最大课时数,最少需要多少个课时区
 * @author huang
 *
 */
public class UtilityClass implements Comparable<UtilityClass>{

	//可容下最大课时数
	private int maxClassHours;
	
	//个数
	private int num;

	public UtilityClass() {
		super();
	}

	public UtilityClass(int maxClassHours, int num) {
		super();
		this.maxClassHours = maxClassHours;
		this.num = num;
	}

	public int getMaxClassHours() {
		return maxClassHours;
	}

	public void setMaxClassHours(int maxClassHours) {
		this.maxClassHours = maxClassHours;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "UtilityClass [maxClassHours=" + maxClassHours + ", num=" + num
				+ "]";
	}

	@Override
	public int compareTo(UtilityClass o) {
		return maxClassHours-o.getMaxClassHours();
	}
	
}
