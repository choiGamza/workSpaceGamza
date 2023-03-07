package com.spring.DAO;

import java.util.List;

import com.spring.DTO.TaskDto;
import com.spring.DTO.projectfileDto;
import com.spring.DTO.projectmemberDto;
import com.spring.DTO.projecttaskDto;
import com.spring.DTO.projecttaskdetailDto;

public interface TaskDaoInterface {

	public int addTask(TaskDto dto);
	public List<TaskDto> TaskList(String id);
	public int updateTask(TaskDto dto);
	public int deleteTask(TaskDto dto);
	public int deleteAllTask(String id);
//----------------------------∆¿«¡∑Œ¡ß∆Æ---------------------------------
   public int addProject(String title,String id,int maxGroup);
   public int maxGroup();
   public List<projectmemberDto> projectmemberList(String id);
   public List<projecttaskDto> projecttaskList(int number);
   public int addProjectTask(int number,String task);
   public String projecttitle(int number,String id);
   public int addtaskDetail(int number,String task,String taskdetail);
   public List<projecttaskdetailDto> projecttaskdetailList(int number);
   public int taskdetailDelete(int number,String task);   
   public int taskDelete(int number,String task);
   public int taskdetailProgress(int choice, int number,String task,String taskdetail);
   
   public int deleteProjectDetail(int number);
   public int deleteProjecttask(int number);
   public int deleteProject(int number, String title);
   
   public int modifyProgress(int choice,int number);
   public int calcProgress(String id,int number);
   public int updateProgress(int progress,int number);
   public int projectImportant(int important,int number);
   public int getprojectImportant(int number,String id);
   public int projectTaskoverlap(int number,String task);
   public int projectTaskdetailoverlap(int number,String task,String taskdetail);
   
   public int addProjectFile(int number, String title, byte[] file);
   public List<projectfileDto> projectFileList(int number);
   public int deleteProjectFile(String title);
}
