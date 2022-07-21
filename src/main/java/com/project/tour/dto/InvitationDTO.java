package com.project.tour.dto;

import lombok.Data;

@Data
public class InvitationDTO {
   private String seq;
   private String pseq;
   private String host;
   private String guest;
   private String regdate;
   
   private String name;
}