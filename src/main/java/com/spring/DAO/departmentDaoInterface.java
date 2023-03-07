package com.spring.DAO;

import java.util.List;

import com.spring.DTO.departmentDto;

public interface departmentDaoInterface {

	public int addDepartment(departmentDto dto);
	public List<departmentDto> departmentList();
	public int deleteDepartment(String department);
	public int plusTotalNum(String department);
	public int deleteTotalNum(String department);
}
