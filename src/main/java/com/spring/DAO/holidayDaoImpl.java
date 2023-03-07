package com.spring.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.DTO.departmentDto;
import com.spring.DTO.holidayDto;

public class holidayDaoImpl implements holidayDaoInterface{

	private List<holidayDto> holidayList;
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int applyHoliday(holidayDto holidaydto) {
		String sql = "insert into holiday values(?,?,?,?,?,?,?,?,?)";
		
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {holidaydto.getId(),holidaydto.getName(),holidaydto.getDepartment(),holidaydto.getHolidaytype(),holidaydto.getStartday(),holidaydto.getEndday(),holidaydto.getContent(),holidaydto.getPosition(),holidaydto.getResult()});
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			System.out.println("applyHoliday() - DataAccessException");  
			ret = -1;
		}
		return ret;
		
	}
	
	public List<holidayDto> holidayList(){
		String sql = "select * from holiday where result='대기'";
		
		try {
			holidayList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(holidayDto.class));
		}
		catch(DataAccessException e){
			System.out.println("holidayList() - DataAccessException");
			return null;
		}
		return holidayList;
	}
	
	public int processHoliday(String id,String process,String holidaytype)
	{
		String sql = "update holiday set result =? where id=? and holidaytype=?";
		
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {process,id,holidaytype});
		}
		catch(DataAccessException e) {
			System.out.println("processHoliday() - DataAccessException");  
			ret = -1;
		}
		return ret;
	}
	
	public holidayDto showDetail(String id,String holidaytype)
	{
		String sql = "select * from holiday where id=? and holidaytype=?";
		try {
			return (holidayDto) jdbcTemplate.queryForObject(sql,new Object[] {id,holidaytype}, new BeanPropertyRowMapper(holidayDto.class));
			
		}
		catch(DataAccessException e){
			System.out.println("중복없음");
			return null;
		}
	}
	
	public List<holidayDto> checkMyHoliday(String id)
	{
		String sql = "select * from holiday where id=?";
		
		try {
			holidayList = jdbcTemplate.query(sql,new Object[] {id},new BeanPropertyRowMapper(holidayDto.class));
		}
		catch(DataAccessException e){
			e.printStackTrace();
			System.out.println("checkMyHoliday() - DataAccessException");
			return null;
		}
		return holidayList;
	}
	
	public int deleteHoliday(String id,String holidaytype)
	{
		String sql = "delete from holiday where id=? and holidaytype=?";
		
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {id,holidaytype});
		}
		catch(DataAccessException e) {
			System.out.println("deleteHoliday() - DataAccessException");  
			ret = -1;
		}
		return ret;
	}
	
}
