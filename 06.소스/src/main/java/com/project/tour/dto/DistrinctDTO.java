package com.project.tour.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class DistrinctDTO {
	private String distrinct;
	private ArrayList<CityDTO> clist;
}
