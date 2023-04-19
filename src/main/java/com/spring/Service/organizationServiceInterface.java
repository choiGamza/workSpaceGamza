package com.spring.Service;

import com.spring.vo.DepartmentVO;
import com.spring.DTO.userVO;

import java.util.List;

public interface organizationServiceInterface {
	//-------------부서 관리--------------------------
	public int addDepartment(DepartmentVO dto);
	public List<DepartmentVO> departmentList();
	public int deleteDepartment(String department);
	public int plusTotalNum(String department);
	public int deleteTotalNum(String department);
	//--------------------------------------------------
	//--------------회원등록/삭제------------------------
	public int MemberRegister(userVO member);
	public List<userVO> MemberList();
	public String Login(String id);
	public userVO userInformation(String id);
	public int changeInfo(userVO member);
	public int AdminUserChangeInfo(userVO member);	//관리자의 권한으로 부서, 직급 변경
	public int AdminUserDeleteInfo(String id);	
	public List<userVO> DepartmentMember(userVO dto);
	public int updateProfileimg(userVO dto);
	
	//-------------------비밀번호 찾기----------------------------
	
	public int FindPwSuccess(userVO dto);		// 사용자의 비밀번호 찾기에서 id, name, email 이 DB에 있는지 확인
	public int FindPwUpdate(userVO dto);
	
	public List<userVO> teamNameList(String department);
}
