package com.project.tour.dto;

import lombok.Data;

@Data
public class ChartDTO {

	private String cnt;
	private String regdate;
	
	private int mCnt;
	private int fCnt;

	private double mPercent;
	private double fPercent;
}
