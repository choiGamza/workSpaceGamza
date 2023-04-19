package com.spring.DAO;

import com.spring.DTO.userVO;

import java.util.List;

public interface memberDaoInterface {
	//리턴 값 1 정상 ---- 1이외 확인절차
	//id 추가하기 전 id가 똑같은게 있는지 먼저 검사하고
	//똑같은게 있으면 1 이외값 return 없으면 정상 진행

	public int MemberRegister(userVO member);	//회원등록
	public List<userVO> MemberList();			//회원등록한 리스트
	public String Login(String id);					//로그인
	public userVO userInformation(String id);	//로그인 인증된 사용자 정보 불러오기
	public int changeInfo(userVO member);		//사용자의 자신의 비밀번호 or 주소 or 폰번호 or 이메일 변경
	public int AdminUserChangeInfo(userVO member);	//관리자의 권한으로 부서, 직급 변경
	public int AdminUserDeleteInfo(String id);			//관리자의 권한으로 사용자 계정 삭제
	public List<userVO> DepartmentMember(userVO dto);
	public int updateProfileimg(userVO dto);
	
	//----------------------------------------------------------------------
	
	public int FindPwSuccess(userVO dto);		// 사용자의 비밀번호 찾기에서 id, name, email 이 DB에 있는지 확인
	public int FindPwUpdate(userVO dto);			// 비밀번호 찾기에서 사용자의 데이터가 DB에 있으면 자동적으로 pw 바꾸고 다시 email로 보내주기
	public List<userVO> teamNameList(String department);
}										


