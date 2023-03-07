package com.spring.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class holidayDto {

	private String id;
	private String name;
	private String department;
	private String holidaytype;
	private String startday;
	private String endday;
	private String content;
	private String position;
	private String result;
	
}
