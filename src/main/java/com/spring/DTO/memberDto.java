package com.spring.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class memberDto {

	private String id;
	private String pw;
	private String department;
	private String sex;
	private String name;
	private String position;
	private String registernum;
	private String phonenum;
	private String address;
	private String email;
	private String profileimg;
	
}
