package com.project.tour.dto;

import lombok.Data;

@Data
public class TourDTO {
	
	private String seq;
	private String placename;
	private String address;
	private String open;
	private String close;
	private String tcseq;
	private String cseq;
	private String image;
	private String lat;
	private String lng;
	
	private String place;
	private String category;
	
    private String placeName;
   
    private String likeCnt;
    private String reviewCnt;
    private String reviewAvg;
   
   
    private String keyword;
   
    private String distrinct;

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
   

   
   

}
