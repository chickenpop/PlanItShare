package com.project.tour.dto;

import lombok.Data;

@Data
public class TourDTO {

	private String seq;
	private String placeName;
	private String address;
	private String open;
	private String close;
	private String image;
	
	private String category;
	private String likeCnt;
	private String reviewCnt;
	private String reviewAvg;
	
}