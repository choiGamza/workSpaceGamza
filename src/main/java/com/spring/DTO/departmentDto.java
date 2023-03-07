package com.spring.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class departmentDto {

	private String department;
	private int totalnum;
	
}
