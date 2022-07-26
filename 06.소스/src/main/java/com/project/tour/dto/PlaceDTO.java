package com.project.tour.dto;

import lombok.Data;

@Data
public class PlaceDTO {
	private String seq;
	private String name; // TourDTO의 placename 포함
	private String address;
	private String open; // LodgingDTO의 checkin 포함
	private String close; // LodgingDTO의 checkout 포함
	private String image;
	private String lat;
	private String lng;
	private String category;
	
	private String regdate;
	private String day;
}
