package com.spring.DAO;

import com.spring.DTO.noticeDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class noticeDaoImpl implements noticeDaoInterface{

	private JdbcTemplate jdbcTemplate;
	private List<noticeDto> list;
	private noticeDto noticedto;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int addNotice(noticeDto dto) {
		String sql = "insert into notice values(0,?,?,?,now(),0,?)";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {dto.getName(), dto.getTitle(), dto.getContent(),dto.getImportant()});
		}
		catch(DataAccessException e) {
			System.out.println("addNotice() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public List<noticeDto> noticeList(){
		String sql = "select * from notice where important = 'true' order by important desc";
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(noticeDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("noticeList() - DataAccessException");  
			e.printStackTrace();
		}
		return list;
	}
	
	public noticeDto noticeView(int bid){
		String sql = "select * from notice where bid = ?";
		try {
			noticedto = (noticeDto) jdbcTemplate.queryForObject(sql, new Object[] {bid}, new BeanPropertyRowMapper(noticeDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("noticeView() - DataAccessException");  
			e.printStackTrace();
		}
		return noticedto;
		
	}
	
	public int noticeHit(int bid) {
		String sql = "update notice set hit = hit + 1 where bid = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql, new Object[] {bid});
		}
		catch(DataAccessException e) {
			System.out.println("noticeHit() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public int noticeDelete(int bid) {
		String sql = "delete from notice where bid = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql,new Object[] {bid});
		}
		catch(DataAccessException e) {
			System.out.println("noticeDelete() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public int noticeUpdate(noticeDto dto) {
		String sql = "update notice set title = ?, content = ? where bid = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql,new Object[] {dto.getTitle(), dto.getContent(), dto.getBid()});
		}
		catch(DataAccessException e) {
			System.out.println("noticeUpdate() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	//--------------------------------------댓글------------------------------
	
	public int addNoticeReply(noticeDto dto) {
		String sql = "insert into noticereply values(?,?,?)";
		int ret = 1;
		try {
//			jdbcTemplate.update(sql, new Object[] {dto.getReplyid(), dto.getReplycontent(),dto.getBid()});
		}
		catch(DataAccessException e) {
			System.out.println("addNoticeReply() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public List<noticeDto> noticeReplyList(int bid){
		String sql = "select * from noticereply where bid = ?";
		try {
			list = jdbcTemplate.query(sql, new Object[] {bid}, new BeanPropertyRowMapper(noticeDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("noticeReplyList() - DataAccessException");  
			e.printStackTrace();
		}
		return list;
	}
	
	public int deleteNoticeReply(String content) {
		String sql = "delete from noticereply where replycontent = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql,new Object[] {content});
		}
		catch(DataAccessException e) {
			System.out.println("deleteNoticeReply() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public List<noticeDto> noticeThreeList(){
		String sql = "select * from notice where important = 'true' order by bid desc limit 3";
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(noticeDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("noticeThreeList() - DataAccessException");  
			e.printStackTrace();
		}
		return list;
	}
	
	public List<noticeDto> boardThreeList(){
		String sql = "select * from notice where important = 'false' order by bid desc limit 3";
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(noticeDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("noticeThreeList() - DataAccessException");  
			e.printStackTrace();
		}
		return list;
	}
	
	public List<noticeDto> ListCount(int page){
		String sql = "select * from notice where important = 'false' limit 10 offset ?";
		
		try {
			list = jdbcTemplate.query(sql,new Object[] {page} ,new BeanPropertyRowMapper(noticeDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("ListCount() - DataAccessException");
			e.getMessage();
			e.printStackTrace();	  
		}
		return list;
	}

	public List<noticeDto> generalList(){
		String sql = "select * from notice where important = 'false'";
		try {
			list = jdbcTemplate.query(sql ,new BeanPropertyRowMapper(noticeDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("ListCount() - DataAccessException");
			e.getMessage();
			e.printStackTrace();	  
		}
		return list;
	}
	
	public List<noticeDto> getSearch(String menu, String search,int page){
		String sql = "select * from notice where "+menu+" like '%"+search+"%' limit 10 offset ?";
		
		try {
			if(menu.equals("title")) {
				list = jdbcTemplate.query(sql,new Object[] {page} ,new BeanPropertyRowMapper(noticeDto.class));
			}
			else if(menu.equals("name")) {
				list = jdbcTemplate.query(sql,new Object[] {page} ,new BeanPropertyRowMapper(noticeDto.class));

			}
		}
		catch(DataAccessException e) {
			System.out.println("getSearch() - DataAccessException");
			e.getMessage();
			e.printStackTrace();	  
		}
		return list;
	}
	
	public List<noticeDto> SearchPage(String menu, String search){
		String sql = "select * from notice where "+menu+" like '%"+search+"%'";

		try {
			if(menu.equals("title")) {
				list = jdbcTemplate.query(sql ,new BeanPropertyRowMapper(noticeDto.class));
			}
			else if(menu.equals("name")) {
				list = jdbcTemplate.query(sql,new BeanPropertyRowMapper(noticeDto.class));

			}
		}
		catch(DataAccessException e) {
			System.out.println("getSearch() - DataAccessException");
			e.getMessage();
			e.printStackTrace();	  
		}
		return list;
	}
	public int deleteAllNotice(String id) {
		String sql = "delete from notice where name = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql,new Object[] {id});
		}
		catch(DataAccessException e) {
			System.out.println("deleteAllNotice() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	public int deleteAllReply(String id) {
		String sql = "delete from noticereply where replyid = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql,new Object[] {id});
		}
		catch(DataAccessException e) {
			System.out.println("deleteAllNotice() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
}

