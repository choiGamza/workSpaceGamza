package com.spring.DTO;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class projectmemberDto implements Serializable{

	private int number;
	private String title;
	private String id;
	private String date;
	private int progress;
	private int sucess;
	private int totalprogress;
	private int important;
	
}
