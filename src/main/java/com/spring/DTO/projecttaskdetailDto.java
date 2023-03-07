package com.spring.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class projecttaskdetailDto {

	private int number;
	private String task;
	private String taskdetail;
	private String progress;
	
}
