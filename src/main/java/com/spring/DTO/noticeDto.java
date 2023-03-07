package com.spring.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
public class noticeDto {

	private int bid;
	private String name;
	private String title;
	private String content;
	private Date rdate;
	private int hit;
	private String replyId;
	private String replyContent;
	private String important;
	
	
}
