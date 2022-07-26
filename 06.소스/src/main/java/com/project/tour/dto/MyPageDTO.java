package com.project.tour.dto;

import lombok.Data;

@Data()
public class MyPageDTO {
	
	private String seq;
	private String name;
	private String address;
	private String open;
	private String close;
	private String fcseq;
	private String cseq;
	private String image;

	private String likeCnt;
	private String reviewCnt;
	private String reviewAvg;
	
	private String category;
	
	private String placename;
	private String tcseq;

	// user
	private String id;
	private String gender;
	private String tel;
	private String pw;
	private String profile;
	private String regdate;
	private String active;
	
	// plan
	private String readcount;
	private String startdate;
	private String enddate;
	private String title;
	private String content;
	
	// city
	private String distrinct;
	private String mainaddress; 
	
	// like plan
	private String pseq;
	
	
}
