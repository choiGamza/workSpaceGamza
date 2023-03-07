package com.spring.DAO;

import java.util.List;

import com.spring.DTO.noticeDto;

public interface noticeDaoInterface {

	public int addNotice(noticeDto dto);		// 공지사항 및 게시글 작성
	public List<noticeDto> noticeList();		// important 가 true 인글
	public List<noticeDto> generalList();		// 전체 리스트
	public noticeDto noticeView(int bid);		// 클릭한 공지사항 및 게시글 내용 보기
	public int noticeHit(int bid);				// 클릭한 글 조회수 증가
	public int noticeDelete(int bid);			// 클릭한 글 삭제
	public int noticeUpdate(noticeDto dto);		// 클릭한 글 수정
	public List<noticeDto> noticeThreeList();	// home 페이지에 공지사항 글 3개씩 보여주기
	public List<noticeDto> boardThreeList();	// home 페이지에 게시글 3개씩 보여주기
	public List<noticeDto> ListCount(int page); // important 가 false 인글
	//-------------------------댓글-----------------------------
	
	public int addNoticeReply(noticeDto dto);	// 댓글 달기
	public List<noticeDto> noticeReplyList(int bid);	// 댓글 단거 리스트 보여주기
	public int deleteNoticeReply(String content);		// 댓글 단거 삭제
	
	public List<noticeDto> getSearch(String menu, String search,int page);
	public List<noticeDto> SearchPage(String menu, String search);
	public int deleteAllNotice(String id);
	public int deleteAllReply(String id);
}
