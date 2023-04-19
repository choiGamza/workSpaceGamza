package com.spring.Service;

import com.spring.DAO.departmentDaoImpl;
import com.spring.DAO.memberDaoImpl;
import com.spring.DTO.userVO;
import com.spring.vo.DepartmentVO;

import java.util.List;

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
	public int addDepartment(DepartmentVO dto) {
		return departmentDao.addDepartment(dto);
	}
	@Override
	public List<DepartmentVO> departmentList(){
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
	public int MemberRegister(userVO member) {
		return memberDao.MemberRegister(member);
	}
	@Override
	public List<userVO> MemberList(){
		return memberDao.MemberList();
	}
	@Override
	public String Login(String id) {
		return memberDao.Login(id);
	}
	@Override
	public userVO userInformation(String id) {
		return memberDao.userInformation(id);
	}
	@Override
	public int changeInfo(userVO member) {
		return memberDao.changeInfo(member);
	}
	@Override
	public int AdminUserChangeInfo(userVO member) {
		return memberDao.AdminUserChangeInfo(member);
	}
	@Override
	public int AdminUserDeleteInfo(String id) {
		return memberDao.AdminUserDeleteInfo(id);
	}
	@Override
	public List<userVO> DepartmentMember(userVO dto){
		return memberDao.DepartmentMember(dto);
	}
	@Override
	public int FindPwSuccess(userVO dto) {
		return memberDao.FindPwSuccess(dto);
	}
	@Override
	public int FindPwUpdate(userVO dto) {
		return memberDao.FindPwUpdate(dto);
	}
	public int updateProfileimg(userVO dto) {
		return memberDao.updateProfileimg(dto);
	}
	public List<userVO> teamNameList(String department){
		return memberDao.teamNameList(department);
	}
}
