package com.spring.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.DTO.TaskDto;
import com.spring.DTO.noticeDto;
import com.spring.DTO.projectfileDto;
import com.spring.DTO.projectmemberDto;
import com.spring.DTO.projecttaskDto;
import com.spring.DTO.projecttaskdetailDto;

public class TaskDaoImpl implements TaskDaoInterface{

	private JdbcTemplate jdbcTemplate;
	private List<TaskDto> list;
	private List<projectmemberDto> projectmemberList;
    private List<projectfileDto> projectfileList;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addTask(TaskDto dto) {
		String sql = "insert into task values(0,?,?,now())";
		int ret = 1;

		try {
			jdbcTemplate.update(sql,new Object[] {dto.getId(),dto.getTask()});

		}
		catch(DataAccessException e) {
			System.out.println("addTask() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	public List<TaskDto> TaskList(String id){
		String sql = "select * from task where id = ?";

		try {
			list = jdbcTemplate.query(sql, new Object[] {id},new BeanPropertyRowMapper(TaskDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("TaskList() - DataAccessException");  
			e.printStackTrace();
		}
		return list;
	}

	public int updateTask(TaskDto dto) {
		String sql = "update task set task = ? where bid = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql,new Object[] {dto.getTask(),dto.getBid()});
		}
		catch(DataAccessException e) {
			System.out.println("updateTask() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public int deleteTask(TaskDto dto) {
		String sql = "delete from task where bid = ?";
		int ret = 1;
		try {
			jdbcTemplate.update(sql,new Object[] {dto.getBid()});
		}
		catch(DataAccessException e) {
			System.out.println("deleteTask() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	public int deleteAllTask(String id) {
	   String sql = "delete from task where id = ?";
	      int ret = 1;
	      try {
	         jdbcTemplate.update(sql,new Object[] {id});
	      }
	      catch(DataAccessException e) {
	         System.out.println("deleteAllTask() - DataAccessException");  
	         e.printStackTrace();
	         ret = -1;
	      }
	      return ret;
   }
	//---------------------------------------------------------------------------

	public int addProject(String title,String id,int maxGroup)
	{

		String sql = "insert into projectmember values(?,?,?,now(),0,0,0,0)";
		int ret;

		try {
			jdbcTemplate.update(sql,new Object[] {maxGroup,title,id});
			ret = 1;
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			return -1;
		}
		return ret;

	}

	public int maxGroup()
	{
		String sql = "select max(number) from projectmember";
		try
		{
			if(jdbcTemplate.queryForObject(sql, Integer.class)==null)
				return 0;
			else
				return jdbcTemplate.queryForObject(sql, Integer.class);
		}
		catch(DataAccessException e)
		{
			System.out.println("maxGroup dataException");
			return 0;
		}
	}

	public List<projectmemberDto> projectmemberList(String id)
	{
		String sql = "select * from projectmember where id = ?";

		try {
			return jdbcTemplate.query(sql, new Object[] {id},new BeanPropertyRowMapper(projectmemberDto.class));
		}
		catch(DataAccessException e) {
			System.out.println("projectmemberList() - DataAccessException");  
			e.printStackTrace();
			return null;
		}

	}

	public List<projecttaskDto> projecttaskList(int number)
	{

		String sql = "select * from projecttask where number = ?";

		try
		{
			return jdbcTemplate.query(sql, new Object[] {number},new BeanPropertyRowMapper(projecttaskDto.class));
		}
		catch(DataAccessException e)
		{
			System.out.println("projecttaskList() - DataAccessException");
			return null;
		}
	}

	public int projectTaskoverlap(int number,String task)
	{
		String sql = "select count(task) from projecttask where task=? and number=?";
		
		
		try {
			if(jdbcTemplate.queryForObject(sql,new Object[] {task,number},Integer.class)==1)
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
			return 2;
		}
	}
	public int addProjectTask(int number,String task)
	{
		String sql = "insert into projecttask values(?,?)";
		int ret = 1;

		try {
			jdbcTemplate.update(sql,new Object[] {number,task});

		}
		catch(DataAccessException e) {
			System.out.println("addProjectTask() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public String projecttitle(int number,String id)
	{
		String sql = "select title from projectmember where number=? and id=?";

		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {number,id},String.class);
		}
		catch(DataAccessException e) {
			System.out.println("projecttitle() - DataAccessException");  
			e.printStackTrace();
			return null;
		}

	}

	public int getprojectImportant(int number,String id)
	{
		String sql = "select important from projectmember where number=? and id=?";

		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {number,id},Integer.class);
		}
		catch(DataAccessException e) {
			System.out.println("getprojectImportant() - DataAccessException");  
			e.printStackTrace();
			return 0;
		}

	}

	public int projectTaskdetailoverlap(int number,String task,String taskdetail)
	{
		String sql = "select count(taskdetail) from projecttaskdetail where task=? and number=? and taskdetail=?";
		
		
		try {
			if(jdbcTemplate.queryForObject(sql,new Object[] {task,number,taskdetail},Integer.class)==1)
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
			return 2;
		}
	}
	
	
	public int addtaskDetail(int number,String task,String taskdetail)
	{
		String sql = "insert into projecttaskdetail values(?,?,?,'process')";
		int ret = 1;

		try {
			jdbcTemplate.update(sql,new Object[] {number,task,taskdetail});

		}
		catch(DataAccessException e) {
			System.out.println("addtaskDetail() - DataAccessException");  
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public List<projecttaskdetailDto> projecttaskdetailList(int number)
	{
		String sql = "select * from projecttaskdetail where number = ? order by progress desc ";

		try
		{
			return jdbcTemplate.query(sql, new Object[] {number},new BeanPropertyRowMapper(projecttaskdetailDto.class));
		}
		catch(DataAccessException e)
		{
			System.out.println("projecttaskdetailList() - DataAccessException");
			return null;
		}
	}

	public int taskdetailDelete(int number,String task)
	{
		String sql = "delete from projecttaskdetail where number=? and task=?";
		int ret= 1;
		try
		{
			return jdbcTemplate.update(sql, new Object[] {number,task});
		}
		catch(DataAccessException e)
		{
			System.out.println("taskdetailDelete() - DataAccessException");
			return -1;
		}
	}

	public int taskDelete(int number,String task)
	{
		String sql = "delete from projecttask where number=? and task=?";
		int ret= 1;
		try
		{
			return jdbcTemplate.update(sql, new Object[] {number,task});
		}
		catch(DataAccessException e)
		{
			System.out.println("taskDelete() - DataAccessException");
			return -1;
		}
	} 

	public int taskdetailProgress(int choice, int number,String task,String taskdetail)
	{
		String sql=null;
		int ret= 1;
		String progress=null;
		if(choice==1)
		{
			sql = "update projecttaskdetail set progress=? where number=? and task=? and taskdetail=?";
			progress="complete";
		}
		else if(choice==2)
		{
			sql = "update projecttaskdetail set progress=? where number=? and task=? and taskdetail=?";
			progress="process";
		}
		else
		{
			sql = "delete from projecttaskdetail where number=? and task=? and taskdetail=?";
		}
		try
		{
			if(choice==1||choice==2)
				return jdbcTemplate.update(sql, new Object[] {progress,number,task,taskdetail});
			else
				return jdbcTemplate.update(sql, new Object[] {number,task,taskdetail});
		}
		catch(DataAccessException e)
		{
			System.out.println("taskdetailProgress() - DataAccessException");
			e.printStackTrace();
			return -1;
		}
	}
	public int deleteProjectDetail(int number) {
		String sql = "delete from projecttaskdetail where number = ?";
		int ret = 1;
		try
		{
			jdbcTemplate.update(sql, new Object[] {number});
		}
		catch(DataAccessException e)
		{
			System.out.println("taskdetailDelete() - DataAccessException");
			ret = -1;
		}
		return ret;

	}
	public int deleteProjecttask(int number) {
		String sql = "delete from projecttask where number = ?";
		int ret = 1;
		try
		{
			jdbcTemplate.update(sql, new Object[] {number});
		}
		catch(DataAccessException e)
		{
			System.out.println("taskdetailDelete() - DataAccessException");
			ret = -1;
		}
		return ret;
	}
	public int deleteProject(int number, String title) {
		String sql = "delete from projectmember where number = ? and title = ?";
		int ret = 1;
		try
		{
			jdbcTemplate.update(sql, new Object[] {number,title});
		}
		catch(DataAccessException e)
		{
			System.out.println("taskdetailDelete() - DataAccessException");
			ret = -1;
		}
		return ret;
	}
	public int modifyProgress(int choice,int number)
	{
		String sql=null;
		int ret=1;
		if(choice==1)//占쏙옙占쏙옙微占� success占쏙옙占쏙옙 占쏙옙占�
		{
			sql = "update projectmember set sucess=sucess+1 where number=?";
		}
		else if(choice==2)//占쏙옙占쏙옙微占� success占쏙옙 풀占쏙옙占쏙옙 占쏙옙占�
		{
			sql = "update projectmember set sucess=sucess-1 where number=?";
		}
		else if(choice==3)//占쏙옙占쏙옙微占� 占쏙옙占쏙옙占쏙옙 占쌩곤옙占쏙옙占쏙옙占쏙옙占�
		{
			sql = "update projectmember set totalprogress=totalprogress+1 where number=?";
		}
		else//占쏙옙占쏙옙微占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占�
		{
			sql = "update projectmember set totalprogress=totalprogress-1 where number=?";
		}

		try
		{   
			return jdbcTemplate.update(sql, new Object[] {number});  
		}
		catch(DataAccessException e)
		{
			System.out.println("modifyProgress() - DataAccessException");
			e.printStackTrace();
			return -1;
		}
	}

	public int calcProgress(String id,int number)
	{
		String sql=null;
		double totalprogress;
		double sucess;

		try
		{
			sql="select totalprogress from projectmember where number=? and id=?";
			totalprogress=jdbcTemplate.queryForObject(sql, new Object[] {number,id},Integer.class);
			if(totalprogress==0)
				return 0;
			sql="select sucess from projectmember where number=? and id=?";
			sucess=jdbcTemplate.queryForObject(sql, new Object[] {number,id},Integer.class);
			if(sucess==0)
				return 0;
			int progress = (int)(sucess/totalprogress*100);
			return progress;

		}
		catch(DataAccessException e)
		{
			System.out.println("calcProgress() - DataAccessException");
			e.printStackTrace();
			return -1;
		}
	}

	public int updateProgress(int progress,int number)
	{
		String sql= "update projectmember set progress=? where number=?";

		try
		{   
			return jdbcTemplate.update(sql, new Object[] {progress,number});  
		}
		catch(DataAccessException e)
		{
			System.out.println("updateProgress() - DataAccessException");
			e.printStackTrace();
			return -1;
		}
	}
	public int projectImportant(int important,int number)
	{
		String sql = "update projectmember set important=? where number=?";

		try
		{
			return jdbcTemplate.update(sql,new Object[] {important,number});
		}
		catch(DataAccessException e)
		{
			System.out.println("projectImportant() - DataAccessException");
			e.printStackTrace();
			return -1;
		}

	}
	public int addProjectFile(int number, String title, byte[] file) {
	   String sql = "insert into projectfile values(?,?,?)";
	   int ret = 1;
	   try {
	         jdbcTemplate.update(sql,new Object[] {number,title,file});
	         
	      }
	      catch(DataAccessException e) {
	         System.out.println("addTask() - DataAccessException");  
	         e.printStackTrace();
	         ret = -1;
	      }
	      return ret;
   }
   public List<projectfileDto> projectFileList(int number){
	   String sql = "select * from projectfile where number = ?";
	   
	   try
	   {
		   projectfileList = jdbcTemplate.query(sql, new Object[] {number}, new BeanPropertyRowMapper(projectfileDto.class) );
	   }
	   catch(DataAccessException e)
	   {
		   System.out.println("projectFileList() - DataAccessException");
		  
	   }
	   return projectfileList;
   }
   public int deleteProjectFile(String title) {
	   String sql = "delete from projectfile where projectname = ?";
	   int ret = 1;
	   try {
	         jdbcTemplate.update(sql,new Object[] {title});
	         
	      }
	      catch(DataAccessException e) {
	         System.out.println("addTask() - DataAccessException");  
	         e.printStackTrace();
	         ret = -1;
	      }
	      return ret;
   }
}
