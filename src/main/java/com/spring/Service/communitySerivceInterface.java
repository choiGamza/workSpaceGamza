package com.spring.Service;

import java.util.List;

import com.spring.DTO.noticeDto;

public interface communitySerivceInterface {

	public int addNotice(noticeDto dto);
	public List<noticeDto> noticeList();
	public noticeDto noticeView(int bid);
	public int noticeHit(int bid);
	public int noticeDelete(int bid);
	public int noticeUpdate(noticeDto dto);
	public List<noticeDto> noticeThreeList();
	public List<noticeDto> boardThreeList();
	public List<noticeDto> ListCount(int page);
	public List<noticeDto> generalList();
	
	public int addNoticeReply(noticeDto dto);
	public List<noticeDto> noticeReplyList(int bid);
	public int deleteNoticeReply(String content);
	
	public List<noticeDto> getSearch(String menu, String search,int page);
	public List<noticeDto> SearchPage(String menu, String search);
	public int deleteAllNotice(String id);
	public int deleteAllReply(String id);
}
