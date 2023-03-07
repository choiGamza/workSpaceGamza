package com.spring.Service;

import java.util.List;

import com.spring.DAO.TaskDaoImpl;
import com.spring.DTO.TaskDto;
import com.spring.DTO.projectfileDto;
import com.spring.DTO.projectmemberDto;
import com.spring.DTO.projecttaskDto;
import com.spring.DTO.projecttaskdetailDto;

public class TaskServiceImpl implements TaskServiceInterface{
   
   private TaskDaoImpl taskdao;
   
   public TaskDaoImpl getTaskdao() {
      return taskdao;
   }
   public void setTaskdao(TaskDaoImpl taskdao) {
      this.taskdao = taskdao;
   }
   public int addTask(TaskDto dto) {
      return taskdao.addTask(dto);
   }
   public List<TaskDto> TaskList(String id){
      return taskdao.TaskList(id);
   }
   public int updateTask(TaskDto dto) {
      return taskdao.updateTask(dto);
   }
   public int deleteTask(TaskDto dto) {
      return taskdao.deleteTask(dto);
   }
   public int deleteAllTask(String id) {
	   return taskdao.deleteAllTask(id);
   }
   public int addProject(String title,String id,int maxGroup)
   {
	   return taskdao.addProject(title, id,maxGroup);
   }
   public int maxGroup()
   {
	   return taskdao.maxGroup();
   }
   public List<projectmemberDto> projectmemberList(String id)
   {
	   return taskdao.projectmemberList(id);
   }
   public List<projecttaskDto> projecttaskList(int number)
   {
	   return taskdao.projecttaskList(number);
   }
   public int addProjectTask(int number,String task)
   {
	   if(taskdao.projectTaskoverlap(number, task)==1)
	   {
		   return taskdao.addProjectTask(number, task);
	   }
	   else
	   {
		   return -1;
	   }
   }
   
   public String projecttitle(int number,String id)
   {
	   return taskdao.projecttitle(number, id);
   }
   
   public int addtaskDetail(String id,int number,String task,String taskdetail)
   {
	   if(taskdao.projectTaskdetailoverlap(number, task,taskdetail)==1)
	   {
		   taskdao.modifyProgress(3, number);
		   taskdao.updateProgress(taskdao.calcProgress(id,number), number);
		   return taskdao.addtaskDetail(number, task, taskdetail); 
	   }
	   else
	   {
		   return -1;
	   }
	   
   }
   public List<projecttaskdetailDto> projecttaskdetailList(int number)
   {
	   return taskdao.projecttaskdetailList(number);
   }
   public int projecttaskDelete(int number,String task)
   {
	   taskdao.taskdetailDelete(number, task);
	   return taskdao.taskDelete(number, task);
   }
   public int taskdetailProgress(String id,int choice, int number,String task,String taskdetail)
   {
	   taskdao.modifyProgress(choice, number);
	   taskdao.updateProgress(taskdao.calcProgress(id,number), number);
	   return taskdao.taskdetailProgress(choice, number, task, taskdetail);
   }
   
   
   public int deleteProjectDetail(int number) {
	   return taskdao.deleteProjectDetail(number);
   }
   public int deleteProjecttask(int number) {
	   return taskdao.deleteProjecttask(number);
   }
   public int deleteProject(int number, String title) {
	   return taskdao.deleteProject(number, title);
   }
   public int projectImportant(int important,int number)
   {
	   return taskdao.projectImportant(important, number);
   }
   public int getprojectImportant(int number,String id)
   {
	   return taskdao.getprojectImportant(number, id);
   }
   public int addProjectFile(int number, String title, byte[] file) {
	   return taskdao.addProjectFile(number, title, file);
   }
   public List<projectfileDto> projectFileList(int number){
	   return taskdao.projectFileList(number);
   }
   public int deleteProjectFile(String title) {
	   return taskdao.deleteProjectFile(title);
   }
}
