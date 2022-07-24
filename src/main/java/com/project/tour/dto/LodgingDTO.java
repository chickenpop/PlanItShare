package com.project.tour.dto;

import lombok.Data;

@Data
public class LodgingDTO {
	
	private String seq;
	private String name;
	private String address;
	private String checkin;
	private String checkout;
	private String lcseq;
	private String cseq;
	private String image;
	private String lat;
	private String lng;
	
	private String place;
	private String category;
	
	private String distrinct;

    private String city;
    private String likecnt;
    private String reviewcnt;
    private String reviewavg;


}
