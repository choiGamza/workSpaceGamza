package com.spring.Service;

import java.util.List;

import com.spring.DTO.departmentDto;
import com.spring.DTO.memberDto;

public interface organizationServiceInterface {
	//-------------부서 관리--------------------------
	public int addDepartment(departmentDto dto);
	public List<departmentDto> departmentList();
	public int deleteDepartment(String department);
	public int plusTotalNum(String department);
	public int deleteTotalNum(String department);
	//--------------------------------------------------
	//--------------회원등록/삭제------------------------
	public int MemberRegister(memberDto member);		
	public List<memberDto> MemberList();
	public String Login(String id);
	public memberDto userInformation(String id);
	public int changeInfo(memberDto member);
	public int AdminUserChangeInfo(memberDto member);	//관리자의 권한으로 부서, 직급 변경
	public int AdminUserDeleteInfo(String id);	
	public List<memberDto> DepartmentMember(memberDto dto);
	public int updateProfileimg(memberDto dto);
	
	//-------------------비밀번호 찾기----------------------------
	
	public int FindPwSuccess(memberDto dto);		// 사용자의 비밀번호 찾기에서 id, name, email 이 DB에 있는지 확인
	public int FindPwUpdate(memberDto dto);
	
	public List<memberDto> teamNameList(String department);
}
