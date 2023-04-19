package com.spring.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentVO {

	private String department;
	private int totalnum;
	
}
