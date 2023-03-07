package com.spring.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.spring.DTO.memberDto;

public class memberDaoImpl implements memberDaoInterface{
	
	private JdbcTemplate jdbcTemplate;
	private List<memberDto> list;
	private memberDto dto;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int MemberRegister(memberDto member) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,'false')";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {member.getId(), member.getPw(), member.getDepartment(), member.getSex(), member.getName(), member.getPosition(), member.getRegisternum(), member.getPhonenum(), member.getAddress(), member.getEmail()});
		}
		catch(DataAccessException e) {
			System.out.println("MemberRegister() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public List<memberDto> MemberList(){
		String sql = "select * from member";
		
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(memberDto.class));
		}
		catch(DataAccessException e){
			System.out.println("MemberList() - DataAccessException");
		}
		return list;
	}
	
	public String Login(String id) {
		
		String sql = "select pw from member where id = ?";
		String pw;
		
		try {
			pw = jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
		}
		catch(DataAccessException e){
			System.out.println("Login() - DataAccessException");
			pw = null;
		}
		return pw;
	}
	public memberDto userInformation(String id) {
		String sql = "select * from member where id = ?";
		
		try {
			dto = (memberDto) jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper(memberDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("userInformation() - DataAccessException");
		}
		return dto;
	}
	
	public int updateProfileimg(memberDto dto) {
		String sql = "update member set profileimg = ? where id = ?";
		
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {dto.getProfileimg(), dto.getId()});
		}
		catch(DataAccessException e) {
			System.out.println("changeInfo() - DataAccessException");
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public int changeInfo(memberDto member) {
		String sql = "update member set pw = ?, phonenum = ?, address = ?, email = ? where id = ?";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {member.getPw(), member.getPhonenum(), member.getAddress(), member.getEmail(), member.getId()});
		}
		catch(DataAccessException e) {
			System.out.println("changeInfo() - DataAccessException");
			ret = -1;
		}
		return ret;
	}
	
	public int AdminUserChangeInfo(memberDto member) {
		String sql = "update member set department = ?, position = ? where id = ?";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {member.getDepartment(), member.getPosition(), member.getId()});
		}
		catch(DataAccessException e) {
			System.out.println("AdminUserChangeInfo() - DataAccessException");
			ret = -1;
		}
		return ret;
	}
	
	public int AdminUserDeleteInfo(String id) {
		String sql = "delete from member where id = ?";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql,new Object[] {id});
		}
		catch(DataAccessException e) {
			System.out.println("AdminUserDeleteInfo() - DataAccessException");
			ret = -1;
		}
		return ret;
	}
	
	public List<memberDto> DepartmentMember(memberDto dto){
		String sql = "select * from member where department = ?";
		try {
			list = jdbcTemplate.query(sql,new Object[] {dto.getDepartment()} ,new BeanPropertyRowMapper(memberDto.class));
		}
		catch(DataAccessException e){
			System.out.println("DepartmentMember() - DataAccessException");
		}
		return list;
	}
	
	public int FindPwSuccess(memberDto dto) {
		String sql = "select * from member where id = ? and name = ? and email = ?";
		int ret = 1;
		
		try {
			jdbcTemplate.queryForObject(sql, new Object[] {dto.getId(),dto.getName(),dto.getEmail()}, new BeanPropertyRowMapper(memberDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("FindPwSuccess() - DataAccessException");
			ret = -1;
		}
		return ret;
	}
	
	public int FindPwUpdate(memberDto dto) {
		String sql = "update member set pw = ? where id = ?";
		int ret = 1;
		
		try {
			jdbcTemplate.update(sql, new Object[] {dto.getPw(),dto.getId()});
		}
		catch(DataAccessException e) {
			System.out.println("FindPwUpdate() - DataAccessException");
			ret = -1;
		}
		return ret;
	}
	public List<memberDto> teamNameList(String department)
	{
		String sql = "select * from member where department=?";
		
		
		try {
			return jdbcTemplate.query(sql,new Object[] {department} ,new BeanPropertyRowMapper(memberDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("teamNameList() - DataAccessException");
			return null;
		}
	}
}
