package com.spring.DTO;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;

@Data
@Builder
public class TaskDto {

	private int bid;
	private String id;
	private String task;
	private Date rdate;

}
