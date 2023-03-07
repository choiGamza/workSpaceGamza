package com.spring.Service;

import java.util.List;

import com.spring.DAO.noticeDaoImpl;
import com.spring.DTO.noticeDto;

public class communityServiceImpl implements communitySerivceInterface{

	private noticeDaoImpl noticedao;

	public noticeDaoImpl getNoticedao() {
		return noticedao;
	}
	public void setNoticedao(noticeDaoImpl noticedao) {
		this.noticedao = noticedao;
	}
	@Override
	public int addNotice(noticeDto dto) {
		return noticedao.addNotice(dto);
	}
	@Override
	public List<noticeDto> noticeList(){
		return noticedao.noticeList();
	}
	@Override
	public noticeDto noticeView(int bid) {
		return noticedao.noticeView(bid);
	}
	@Override
	public int noticeDelete(int bid) {
		return noticedao.noticeDelete(bid);
	}
	@Override
	public int noticeUpdate(noticeDto dto) {
		return noticedao.noticeUpdate(dto);
	}
	@Override
	public int noticeHit(int bid) {
		return noticedao.noticeHit(bid);
	}
	@Override
	public List<noticeDto> noticeThreeList(){
		return noticedao.noticeThreeList();
	}
	@Override
	public List<noticeDto> boardThreeList(){
		return noticedao.boardThreeList();
	}
	//--------------------댓글-------------------------------
	@Override
	public int addNoticeReply(noticeDto dto) {
		return noticedao.addNoticeReply(dto);
	}
	@Override
	public List<noticeDto> noticeReplyList(int bid){
		return noticedao.noticeReplyList(bid);
	}
	@Override
	public int deleteNoticeReply(String content) {
		return noticedao.deleteNoticeReply(content);
	}
	public List<noticeDto> ListCount(int page){
		return noticedao.ListCount(page);
	}
	public List<noticeDto> generalList(){
		return noticedao.generalList();
	}
	public List<noticeDto> getSearch(String menu, String search,int page){
		return noticedao.getSearch(menu, search,page);
	}
	public List<noticeDto> SearchPage(String menu, String search){
		return noticedao.SearchPage(menu, search);
	}
	public int deleteAllNotice(String id) {
		return noticedao.deleteAllNotice(id);
	}
	public int deleteAllReply(String id) {
		return noticedao.deleteAllReply(id);
	}
}
