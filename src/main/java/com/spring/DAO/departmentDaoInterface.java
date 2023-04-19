package com.spring.DAO;

import java.util.List;

import com.spring.vo.DepartmentVO;

public interface departmentDaoInterface {

	public int addDepartment(DepartmentVO dto);
	public List<DepartmentVO> departmentList();
	public int deleteDepartment(String department);
	public int plusTotalNum(String department);
	public int deleteTotalNum(String department);
}
