package com.spring.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.spring.DTO.departmentDto;

public class departmentDaoImpl implements departmentDaoInterface{

	private JdbcTemplate jdbcTemplate;
	private List<departmentDto> list;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int addDepartment(departmentDto dto) {
		String sql = "insert into department values(?,?)";
		
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {dto.getDepartment(), dto.getTotalnum()});
		}
		catch(DataAccessException e) {
			System.out.println("addDepartment() - DataAccessException");  
			ret = -1;
		}
		return ret;
		
	}
	public List<departmentDto> departmentList(){
		String sql = "select * from department";
		
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(departmentDto.class));
		}
		catch(DataAccessException e){
			System.out.println("departmentList() - DataAccessException");
		}
		return list;
	}
	
	public int deleteDepartment(String department) {
		String sql = "delete from department where department = ? and totalnum = 0";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql,new Object[] {department});
		}
		catch(DataAccessException e) {
			System.out.println("deleteDepartment() - DataAccessException");  
			ret = -1;
		}
		return ret;
	}
	
	public int plusTotalNum(String department) {
		String sql = "update department set totalnum = totalnum+1 where department = ?";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql,new Object[] {department});
		}
		catch(DataAccessException e) {
			System.out.println("plusTotalNum() - DataAccessException");  
			ret = -1;
		}
		return ret;
	}
	
	public int deleteTotalNum(String department) {
		String sql = "update department set totalnum = if(totalnum=0,totalnum=0 ,totalnum-1) where department = ?";
		
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql,new Object[] {department});
		}
		catch(DataAccessException e) {
			System.out.println("sql문에 if 문때문에 exception 날수 있음  deleteTotalNum() - DataAccessException"); 
			ret = -1;
		}
		return ret;
	}
}
