package com.project.tour.dto;

import lombok.Data;

@Data
public class FoodDTO {
	
	private String seq;
	private String name;
	private String address;
	private String open;
	private String close;
	private String fcseq;
	private String cseq;
	private String image;
	private String lat;
	private String lng;
	
	private String place;
	private String category;
	
	private String likeCnt;
    private String reviewCnt;
    private String reviewAvg;
    private String distrinct;
   

}
