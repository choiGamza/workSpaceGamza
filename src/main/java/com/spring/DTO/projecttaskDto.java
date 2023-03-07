package com.spring.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class projecttaskDto {

	private int number;
	private String task;
	
}
