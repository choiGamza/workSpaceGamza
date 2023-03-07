package com.spring.Service;

import java.util.List;

import com.spring.DAO.departmentDaoImpl;
import com.spring.DAO.memberDaoImpl;
import com.spring.DTO.departmentDto;
import com.spring.DTO.memberDto;

public class organizationServiceImpl implements organizationServiceInterface{

	private departmentDaoImpl departmentDao;
	private memberDaoImpl memberDao;
	
	
	
	public void setDepartmentDao(departmentDaoImpl departmentDao) {
		this.departmentDao = departmentDao;
	}
	public departmentDaoImpl getDepartmentDao() {
		return departmentDao;
	}
	public void setMemberDao(memberDaoImpl memberDao) {
		this.memberDao = memberDao;
	}
	public memberDaoImpl getMemberDao() {
		return memberDao;
	}
	@Override
	public int addDepartment(departmentDto dto) {
		return departmentDao.addDepartment(dto);
	}
	@Override
	public List<departmentDto> departmentList(){
		return departmentDao.departmentList();
	}
	@Override
	public int deleteDepartment(String department) {
		return departmentDao.deleteDepartment(department);
	}
	@Override
	public int plusTotalNum(String department) {
		return departmentDao.plusTotalNum(department);
	}
	@Override
	public int deleteTotalNum(String department) {
		return departmentDao.deleteTotalNum(department);
	}
	@Override
	public int MemberRegister(memberDto member) {
		return memberDao.MemberRegister(member);
	}
	@Override
	public List<memberDto> MemberList(){
		return memberDao.MemberList();
	}
	@Override
	public String Login(String id) {
		return memberDao.Login(id);
	}
	@Override
	public memberDto userInformation(String id) {
		return memberDao.userInformation(id);
	}
	@Override
	public int changeInfo(memberDto member) {
		return memberDao.changeInfo(member);
	}
	@Override
	public int AdminUserChangeInfo(memberDto member) {
		return memberDao.AdminUserChangeInfo(member);
	}
	@Override
	public int AdminUserDeleteInfo(String id) {
		return memberDao.AdminUserDeleteInfo(id);
	}
	@Override
	public List<memberDto> DepartmentMember(memberDto dto){
		return memberDao.DepartmentMember(dto);
	}
	@Override
	public int FindPwSuccess(memberDto dto) {
		return memberDao.FindPwSuccess(dto);
	}
	@Override
	public int FindPwUpdate(memberDto dto) {
		return memberDao.FindPwUpdate(dto);
	}
	public int updateProfileimg(memberDto dto) {
		return memberDao.updateProfileimg(dto);
	}
	public List<memberDto> teamNameList(String department){
		return memberDao.teamNameList(department);
	}
}
