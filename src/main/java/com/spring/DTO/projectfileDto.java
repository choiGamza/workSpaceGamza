package com.spring.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class projectfileDto {
	
	private int number;
	private String projectname;
	private byte[] projectdata;


}
