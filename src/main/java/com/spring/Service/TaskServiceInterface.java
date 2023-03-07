package com.spring.Service;

import java.util.List;

import com.spring.DTO.TaskDto;
import com.spring.DTO.projectfileDto;
import com.spring.DTO.projectmemberDto;
import com.spring.DTO.projecttaskDto;
import com.spring.DTO.projecttaskdetailDto;

public interface TaskServiceInterface {

	public int addTask(TaskDto dto);
	public List<TaskDto> TaskList(String id);
	public int updateTask(TaskDto dto);
	public int deleteTask(TaskDto dto);
	public int deleteAllTask(String id);
	//----------------------------------∆¿«¡∑Œ¡ß∆Æ--------------------------------------
	
	public int addProject(String title,String id,int maxGroup);
	public int maxGroup();
	public List<projectmemberDto> projectmemberList(String id);
	public List<projecttaskDto> projecttaskList(int number);
	public int addProjectTask(int number,String task);
	public String projecttitle(int number,String id);
	public int addtaskDetail(String id,int number,String task,String taskdetail);
	public List<projecttaskdetailDto> projecttaskdetailList(int number);   
	public int projecttaskDelete(int number,String task);
	public int taskdetailProgress(String id,int choice, int number,String task,String taskdetail);
	
	public int deleteProjectDetail(int number);
	public int deleteProjecttask(int number);
	public int deleteProject(int number, String title);
	
	public int projectImportant(int important,int number);
	public int getprojectImportant(int number,String id);
	public int addProjectFile(int number, String title, byte[] file);
	public List<projectfileDto> projectFileList(int number);
	public int deleteProjectFile(String title);
}
