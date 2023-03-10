package com.spring.bo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.DTO.TaskDto;
import com.spring.DTO.departmentDto;
import com.spring.DTO.holidayDto;
import com.spring.DTO.memberDto;
import com.spring.DTO.noticeDto;
import com.spring.DTO.projectfileDto;
import com.spring.DTO.projecttaskDto;
import com.spring.DTO.projecttaskdetailDto;
import com.spring.Service.TaskServiceInterface;
import com.spring.Service.communitySerivceInterface;
import com.spring.Service.holidayServiceInterface;
import com.spring.Service.organizationServiceInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final String FILE_EXT = ".jpg";
	
	// 11/12 16:32 HomeController Mapping ????????????
	// 11/19 15:30 HomeController ?????? ??????/?????? ?????? (?????????)
	// 11/19 15:31 Login????????? HomeController ?????? ????????? ????????? ?????????  Dto ???????????? ?????? memberDto
	private int certCharLength = 6;
	private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
             'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
             'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };	// ?????? ???????????? ????????????
	static String emailToRecipient, emailSubject, emailMessage;		//email ??????,??????,?????? email

	private List<departmentDto> departmentList;
	private List<memberDto> memberList;
	private List<holidayDto> holidayList;
	private List<noticeDto> noticeList;
	private List<noticeDto> boardList;
	private List<TaskDto> taskList;
	private List<projecttaskDto> TeamtaskList;
	private List<projecttaskdetailDto> taskdetailList;	
	private List<projectfileDto> taskfileList;
	private memberDto memberdto;
	private noticeDto noticedto;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private organizationServiceInterface organizationService;
	@Autowired
	private holidayServiceInterface holidayservice;
	@Autowired
	private communitySerivceInterface communityservice;
	@Autowired
	private TaskServiceInterface taskservice;
	@Autowired
	private JavaMailSender mailSenderObj;
	
	
	
	
	//11/19
	holidayDto holidaydto = new holidayDto();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		return "Main/login";
	}
	
	//?????????????????? Controller????????????
	//??????????????? ????????? ????????? dao?????? ???????????? ???????????? ????????? ??????
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView Login(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws Exception{
		String viewPage = "Main/home";
		ModelAndView model = new ModelAndView(viewPage);
		
		String id = request.getParameter("ID");				//???????????? ????????? id ???
		String pw = request.getParameter("PW");				//???????????? ????????? pw ???
		
		String LoginPw = organizationService.Login(id);			//???????????? ????????? id ?????? sql????????? ?????????????????? pw ??????
		boolean ret = false;

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
		ret = bcryptPasswordEncoder.matches(pw,LoginPw);
		//--------------------------------------------------------------------------------------
		
		//-------------------------------------------- ??????/????????? ??????--------------------------------------------
		
			if(ret == true) 
			{
				viewPage = "Main/home";
				memberdto = organizationService.userInformation(id);
				session.setAttribute("user", memberdto);
				noticeList = communityservice.noticeThreeList();		// home ????????? 3?????? ???????????? (????????????)
				boardList = communityservice.boardThreeList();			// home ????????? 3?????? ???????????? (?????????)
				taskList = taskservice.TaskList(id);
				model.addObject("noticeList",noticeList);
				model.addObject("boardList",boardList);
				model.addObject("taskList",taskList);
				model.setViewName(viewPage);
				return model;
			}
			else
			{
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('????????? ?????? ??????????????? ???????????????.');history.back();</script>");
				out.close();
				viewPage ="Main/login";
				model.setViewName(viewPage);
				return model;
			}

	}
//????????? ??????????????? Find ----- Controller ??????????????????  //dao ???????????? 	
	@RequestMapping(value = "/Find", method = RequestMethod.POST)
	public String FindPwSuccess(HttpServletResponse response ,Model model, memberDto dto) throws Exception{
		int ret = organizationService.FindPwSuccess(dto);
		
		if(ret == 1) {
			Random random = new Random(System.currentTimeMillis());
			int tableLength = characterTable.length;
			
			StringBuffer buf = new StringBuffer();
			
			for(int i = 0; i < certCharLength; i++) {
				buf.append(characterTable[random.nextInt(tableLength)]);
			}
			String bwf = buf.substring(0);
			
			String hashedpassword = bcryptPasswordEncoder.encode(bwf);
			System.out.println("hashed pw   "+hashedpassword);
			dto.setPw(hashedpassword);
			organizationService.FindPwUpdate(dto);		// ?????? ???????????? 
			
			emailSubject = dto.getName()+" ??? TheJoen ???????????? ?????? ?????? ?????????.";
			emailMessage = "????????? ???????????? : " + bwf + " ?????????????????? ??????????????? ??????????????????.";
			emailToRecipient = dto.getEmail();
			
			System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");

			SimpleMailMessage simpleEmail = new SimpleMailMessage();
			simpleEmail.setTo(emailToRecipient);
			simpleEmail.setSubject(emailSubject);
			simpleEmail.setText(emailMessage);
					
			mailSenderObj.send(simpleEmail);			// email ??????
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('????????? ??????????????? email??? ?????????????????????.');history.back();</script>");
			out.close();
			
			return "Main/login";
		}
		else {
			System.out.println("id ?????? name ?????? email ?????????");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('id ?????? name ?????? email ?????????.');history.back();</script>");
			out.close();
			return "Main/login";
		}
	}

	//-----------------------------????????????(????????? ?????? Mapping)----------------------------
	@RequestMapping(value="/adminRegister") // ????????????/??????
		public String adminRegister(Model model) 
	{

		return "redirect:/memberList";
	}
	@RequestMapping(value="/adminOrganization") // ????????????
	public String adminOrganization() 
	{
	return "redirect:/departmentList";
	}	
	
	@RequestMapping(value = "/userDetailInfo")
	public String userDetailInfo(HttpServletRequest request,Model model) {
		
		String id = request.getParameter("id");
		memberdto = organizationService.userInformation(id);
		model.addAttribute("DetailUser",memberdto);
		departmentList = organizationService.departmentList();
		
		model.addAttribute("Detaildepartment",departmentList);
		return "Organization/Admin/adminDetailInfomation";
	}
	//-------------------------------????????????(????????? ?????? Mapping)---------------------------------------
	@RequestMapping(value="/userOrganization")  //???????????????(?????????)
	public String userOrganization(Model model)
	{
		departmentList = organizationService.departmentList();
		model.addAttribute("departmentList",departmentList);
		return "Organization/User/userOrganization";
	}
	
	
	
	
	//-------------------------------????????????(????????? ?????? Mapping)---------------------------------
	@RequestMapping(value="/userTeamSchedule") //????????? userTeamSchedule
   public ModelAndView userTeamSchedule(HttpSession session) 
   {
      ModelAndView model = new ModelAndView("Task/userTeamSchedule");
      memberdto=(memberDto)session.getAttribute("user");
      memberList=organizationService.teamNameList(memberdto.getDepartment());
      session.setAttribute("projectmemberlist",taskservice.projectmemberList(memberdto.getId()));
      model.addObject("memberList", memberList);           
      return model;
   }
   @RequestMapping(value="/userTeamScheduleDetale", method = RequestMethod.GET)
   public String userTeamScheduleDetale(HttpSession session,HttpServletRequest request,Model model)
   {
       
	   String stringNum=request.getParameter("number");
		int number = Integer.parseInt(stringNum);
	   
	   session.setAttribute("projectNumber", Integer.parseInt(stringNum));
	   
	   memberdto=(memberDto)session.getAttribute("user");
	   
	   TeamtaskList=taskservice.projecttaskList(Integer.parseInt(stringNum));
	   taskfileList = taskservice.projectFileList(number);
	   taskdetailList = taskservice.projecttaskdetailList(Integer.parseInt(stringNum));
	   
	   session.setAttribute("projectname", taskservice.projecttitle(Integer.parseInt(stringNum), memberdto.getId()));
	   session.setAttribute("projectimportant", taskservice.getprojectImportant(Integer.parseInt(stringNum), memberdto.getId()));
	   model.addAttribute("taskList",TeamtaskList);
	   model.addAttribute("taskdetailList",taskdetailList);
		model.addAttribute("taskfileList",taskfileList);
		model.addAttribute("number",stringNum);
	   
	   
	   return "Task/userTeamScheduleDetale";
   }
   @RequestMapping(value="/userTeamSchedulDetaleContinue")
   public String userTeamSchedulDetaleContinue(HttpSession session,Model model,HttpServletRequest request)
   {
	   int number=(Integer)session.getAttribute("projectNumber");
	   
	   TeamtaskList=taskservice.projecttaskList(number);
	   taskfileList = taskservice.projectFileList(number);
	   taskdetailList = taskservice.projecttaskdetailList(number);
	   
	   session.setAttribute("projectimportant", taskservice.getprojectImportant(number, memberdto.getId()));
	   model.addAttribute("taskList",TeamtaskList);
	   
	   model.addAttribute("taskdetailList",taskdetailList);
	   model.addAttribute("number",number);
		model.addAttribute("taskfileList",taskfileList);
	   return "Task/userTeamScheduleDetale";
   }
	@RequestMapping(value="/userTask") //????????? ????????????
	public String userTask(RedirectAttributes redirectAttributes,HttpServletRequest request) 
	{
		String id = request.getParameter("id");
		redirectAttributes.addAttribute("id",id);
		return "redirect:/taskList";
	}
	
	//-------------------------------??????(????????? ?????? Mapping)----------------------------	
	@RequestMapping(value = "/adminHoliday", method = RequestMethod.GET) //????????????(?????????)
	public String adminHoliday(Model model) 
	{
			holidayList = holidayservice.holidayList();
			model.addAttribute("holidaylist", holidayList);
			return "Holiday/Admin/adminHoliday";
	}
	
	@RequestMapping(value = "/adminHolidayDetail", method = RequestMethod.GET) //????????????(?????????)
	public String adminHolidayDetail(Model model,HttpServletRequest request) 
	{
			String id=request.getParameter("id");
			String holidaytype=request.getParameter("holidaytype");
			holidaydto=holidayservice.showDetail(id,holidaytype);
			model.addAttribute("holidaydto",holidaydto);
		//holidaydto??? ???????????? ?????? ???????????? model??? object??? ???????????????
			return "Holiday/Admin/adminHolidayDetail";
	}
	
	
	
	
	//-------------------------------??????(????????? ?????? Mapping)----------------------------
	
	@RequestMapping(value = "/userHolidayApply", method = RequestMethod.GET) //????????????
	public String userHolidayApply(HttpSession session) { 
		
			return "Holiday/User/userHolidayApply";

	}
	
	@RequestMapping(value ="/userHolidayCheck", method = RequestMethod.GET) //????????????
	public String userHolidayCheck(HttpSession session,Model model) 
	{
		memberdto = (memberDto)session.getAttribute("user");
		String id=memberdto.getId();
		holidayList=holidayservice.checkMyHoliday(id);
		
		model.addAttribute("holidaylist", holidayList);
		
		return "Holiday/User/userHolidayCheck";
	}
	
	
	//-----------------------------------????????????-------------------------------------------
	@RequestMapping(value="/Notice")  //????????????
	public String Notice(RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		return "redirect:/noticeListView";
	}
	
	@RequestMapping(value="/chatting")
	public String chatting()
	{
		
		return "messinger";
	}
	//------------------------- MainMenu home?????? ????????????------------------
	@RequestMapping(value="/adminMain")
	public ModelAndView adminHome(HttpServletRequest request) 
	{
		String id = request.getParameter("id");
		ModelAndView model = new ModelAndView("Main/home");
		noticeList = communityservice.noticeThreeList();		// home ????????? 3?????? ???????????? (????????????)
		boardList = communityservice.boardThreeList();			// home ????????? 3?????? ???????????? (?????????)
		taskList = taskservice.TaskList(id);
		model.addObject("noticeList",noticeList);
		model.addObject("boardList",boardList);
		model.addObject("taskList",taskList);
		return model;
	}
	//???????????? ????????? ?????????????????? ?????? 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("admin"); //????????? ?????? ?????? 
		return "Main/login";
	}
	
	
	//=======================================================================================================
	//=======================================================================================================
    //=======================================================================================================
	//============================================??????========================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	
	
	
	//---------------------------????????????----------------------------------
	
	
	@RequestMapping(value="/addDepartment", method = RequestMethod.POST)
	public String addDepartment(departmentDto dto) {
		
		organizationService.addDepartment(dto);
		
		return "redirect:/departmentList";
	}
	
	@RequestMapping(value="/departmentList")
	public String departmentList(Model model) {
		
		departmentList = organizationService.departmentList();
		
		model.addAttribute("departmentList",departmentList);
		return "Organization/Admin/adminOrganization";
	}
	
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.GET)
	public String deleteDepartment(HttpServletRequest request) {
		String department = (String)request.getParameter("department");
		System.out.println(department);
		organizationService.deleteDepartment(department);

		return "redirect:/departmentList";
	}
	//------------------------------------------------------------------
	
	
	//------------------------------????????????--------------------------------
	
	
	@RequestMapping(value="/addMember", method = RequestMethod.POST)
	public String addMember(memberDto dto, HttpServletRequest request) {
		//----------------------?????? ???????????? ?????? ????????? -------------------------------------------
		Random random = new Random(System.currentTimeMillis());
		int tableLength = characterTable.length;
		
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < certCharLength; i++) {
			buf.append(characterTable[random.nextInt(tableLength)]);
		}
		String bwf = buf.substring(0);

		System.out.println(dto.getDepartment());
		
		//-----------------------?????????------------------------------------------------------------
		
		String hashedpassword = bcryptPasswordEncoder.encode(bwf);
		System.out.println("hashed pw   "+hashedpassword);
		dto.setPw(hashedpassword);
		organizationService.MemberRegister(dto);			// ?????? ??????DB??? ??????	--> ?????? ???????????? ??????????????? ?????? --> ???????????? ?????? ????????? ??????????????? ?????? ??????
	
		//-----------------------------------------------------------------
		//------------------------------?????????????????? ????????? 1??? ??????---------------------

		organizationService.plusTotalNum(dto.getDepartment());
		
		//---------------------------------------------------------------------
		//-----------------------?????? ?????? ????????? ????????? ???????????? email ????????? ??????--------------
		// 1. creates a simple e-mail object
		
		emailSubject = dto.getName()+" ??? TheJoen ????????? ??????????????????.";
		emailMessage = "?????? ID : "+dto.getId() + " ?????????????????? : " + bwf;
		emailToRecipient = request.getParameter("email");
		
		System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");

		SimpleMailMessage simpleEmail = new SimpleMailMessage();
		simpleEmail.setTo(emailToRecipient);
		simpleEmail.setSubject(emailSubject);
		simpleEmail.setText(emailMessage);
				
		mailSenderObj.send(simpleEmail);			// email ??????
		
		return "redirect:/memberList";
	}
	
	@RequestMapping(value="/memberList")		// ?????? ??????
	public String memberList(Model model)
	{
		departmentList = organizationService.departmentList();
		model.addAttribute("departmentList",departmentList);			
		memberList = organizationService.MemberList();
		
		model.addAttribute("memberList",memberList);
		return "Organization/Admin/adminRegister";
		
	}
	
	//------------------------------------------------------------------------------------------------
	
	//-----------------------------?????? ??????----------------------------------------------------------------
	
	@RequestMapping(value = "/writeHoliday", method = RequestMethod.POST)
	public String writeHoliday(HttpSession session,HttpServletRequest request,Model model) { //???????????? ??????
		memberdto = (memberDto)session.getAttribute("user");
		holidayDto holidaydto = new holidayDto();
		//-------------  ???????????? ?????? DAO ???????????? ????????? ------------------
		String id =memberdto.getId();
		String name =memberdto.getName();
		String department = memberdto.getDepartment();
		String holidaytype = request.getParameter("holidaytype");
		String startday = request.getParameter("startday");
		String endday = request.getParameter("endday");
		String content = request.getParameter("content");
		String position = memberdto.getPosition();
		String result = "??????";
		if(holidaytype.equals("vacation")) { ///??????
			holidaytype="??????";
	      }
	      else if(holidaytype.equals("vacation2")) { //??????
	    	  holidaytype="??????";
	      }
	      else { //????????????
	    	  holidaytype="????????????";
	      }
		holidaydto.setId(id);
		holidaydto.setName(name);
		holidaydto.setDepartment(department);
		holidaydto.setHolidaytype(holidaytype);
		holidaydto.setStartday(startday);
		holidaydto.setEndday(endday);
		holidaydto.setContent(content);
     	holidaydto.setPosition(position);
		holidaydto.setResult(result);
		//holidaydto??? ???????????? ??????????????? ?????????????????? id??? type??? ???????????? ?????? ???????????? ????????? ????????? ???????????? ????????? ??????
		if(holidayservice.showDetail(holidaydto.getId(), holidaydto.getHolidaytype())==null)
		{
			
			holidayservice.applyHoliday(holidaydto);
		}
		model.addAttribute("alert", true); //dao??? ?????? ??? ??????????????? ?????? ???????????? ???????????? ?????????????????? ???????????? ?????? ??????
		return "Holiday/User/userHolidayApply";
	}
	
	@RequestMapping(value ="/deleteHoliday", method=RequestMethod.GET)
	public String deleteHoliday(HttpServletRequest request)
	{
		String id=request.getParameter("id");
		String holidaytype=request.getParameter("holidaytype");
		holidayservice.deleteHoliday(id, holidaytype);
		return "redirect:/userHolidayCheck";

	}
	
	//--------------------------------------------------------------------------------------------
	
	//--------------------------------????????????-------------------------------------------------------
	private String genSaveFileName(String start,String extName) {
	    String fileName = "";
	     
	    fileName = start + extName;
	    System.out.println("genSaveFileName() : "+fileName); 
	    return fileName;
	  }
	
	@RequestMapping(value = "/changeInfo")				// ???????????? ?????? ????????????
	public String changeInfo(Model model, memberDto dto,HttpServletRequest request ,HttpServletResponse response, @RequestParam("file") MultipartFile file) throws Exception{
		
		if(file.isEmpty()==true) {					// ???????????? ?????????
			System.out.println("????????? ??????");
			if(dto.getPw().equals("")) {			// ??????????????? ????????? 
				dto.setPw(request.getParameter("pw2"));
				organizationService.changeInfo(dto);
				
			}
			else {									// ???????????? ??????
				String hashedpassword = bcryptPasswordEncoder.encode(dto.getPw());
				dto.setPw(hashedpassword);
				
				organizationService.changeInfo(dto);
				
				
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>alert('??????????????? ?????????????????????..');history.back();</script>");
				
				out.close();
				
			}
			noticeList = communityservice.noticeThreeList();
			
			boardList = communityservice.boardThreeList();
			
			model.addAttribute("noticeList",noticeList);
			
			model.addAttribute("boardList",boardList);
			
			
			return "Main/home";
		}
		else {										//????????? ??????
			String fileName = genSaveFileName(dto.getId(),FILE_EXT);
			
			String path2 = "C:\\apache-tomcat-8.5.32\\wtpwebapps\\Test1018\\resources\\profileimg";
			
	
		   BufferedOutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(new File(path2,fileName)));
		   
					   
		   		outputStream2.write(file.getBytes());
		   
		   		outputStream2.flush();
		   
		   		outputStream2.close();
		   
		   		
		   		
			   dto.setProfileimg("true");
			
			  organizationService.updateProfileimg(dto);
			
		
			  if(dto.getPw().equals("")) {
				dto.setPw(request.getParameter("pw2"));
				organizationService.changeInfo(dto);
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('???????????? ?????????????????????..');history.back();</script>");
				out.close();
			  }
			  else {
				String hashedpassword = bcryptPasswordEncoder.encode(dto.getPw());
				dto.setPw(hashedpassword);
				organizationService.changeInfo(dto);
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('???????????? ??????????????? ?????????????????????..');history.back();</script>");
				out.close();
				
			  }
			noticeList = communityservice.noticeThreeList();
			boardList = communityservice.boardThreeList();
			model.addAttribute("noticeList",noticeList);
			model.addAttribute("boardList",boardList);
		
			return "Main/home";
		}
		
	}
	
	@RequestMapping(value = "/userInfoUpdate")
	public String userInfoUpdate(HttpServletRequest request, memberDto dto,Model model) {
		String title = request.getParameter("AdminUpdate");				//???????????? ???????????? ???????????? ??????
		String id = request.getParameter("id");						
		String nowDepartment = request.getParameter("nowDepartment");	//??????????????? ???????????? department

		if(title!=null) {
			organizationService.AdminUserChangeInfo(dto);		// ???????????? ????????? ?????????
			organizationService.plusTotalNum(dto.getDepartment());	//????????? ???????????? ????????? ????????? ????????? ?????? +1
			organizationService.deleteTotalNum(nowDepartment);	// ????????? ???????????? ???????????? ????????? ????????? -1
			noticeList = communityservice.noticeThreeList();
			boardList = communityservice.boardThreeList();
			model.addAttribute("noticeList",noticeList);
			model.addAttribute("boardList",boardList);
			return "Main/home";
			
		}
		else {
			organizationService.AdminUserDeleteInfo(id);
			organizationService.deleteTotalNum(nowDepartment);	// ???????????? ???????????? ?????? ?????? ???????????? ????????? ????????? -1
			communityservice.deleteAllNotice(id);
			communityservice.deleteAllReply(id);
			taskservice.deleteAllTask(id);
			noticeList = communityservice.noticeThreeList();
			boardList = communityservice.boardThreeList();
			model.addAttribute("noticeList",noticeList);
			model.addAttribute("boardList",boardList);
			return "Main/home";
			
		}
	}
  
   
   
	//---------------------------------------------------------------------------------------------------
	
	//----------------------------------------????????????/?????????--------------------------------------------------
	
	@RequestMapping(value = "/noticeAdd")			// ???????????? or ????????? ??????
	public String noticeAdd(noticeDto dto,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		
		String important = request.getParameter("important");		//?????? ??????
		String content = request.getParameter("content");
		content = content.replace("\r\n", "<br>");					// ?????? ????????? ??? ???????????? ??? ?????????
		String name = request.getParameter("name");
		System.out.println(important + ", " + name);
		if(name.equals("admin")) {
			dto.setContent(content);
			communityservice.addNotice(dto);
		}
		else {
			dto.setImportant("false");
			dto.setContent(content);
			communityservice.addNotice(dto);
		}
		return "redirect:/noticeListView";
	}
	
	@RequestMapping(value = "/noticeListView")		// ???????????? ?????????
	public String noticeListView(Model model,HttpServletRequest request) {
		String page = request.getParameter("pagenum");
		int pagenum = Integer.parseInt(page);
		int a = (pagenum*10)-10;
		
		noticeList = communityservice.noticeList();
		
		List<noticeDto> listCount = communityservice.ListCount(a);
		List<noticeDto> generalList = communityservice.generalList();
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("listCount",listCount);
		model.addAttribute("generalList",generalList);
		return "Community/Notice";
	}
	
	@RequestMapping(value = "/noticeView")			// ????????? ???????????? ??? 
	public String noticeView(Model model,HttpServletRequest request) {
		String bidnotice = request.getParameter("bid");
		int bid = Integer.parseInt(bidnotice);
		
		communityservice.noticeHit(bid);
		noticedto = communityservice.noticeView(bid);
		noticeList = communityservice.noticeReplyList(bid);
		model.addAttribute("noticedto",noticedto);
		model.addAttribute("noticeReplyList",noticeList);
		return "Community/NoticeContentView";
	}
	
	@RequestMapping(value = "/deleteNotice")		// ???????????? ??????
	public String noticeDelete(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		String bidString = request.getParameter("bid");
		int bid = Integer.parseInt(bidString);
		
		communityservice.noticeDelete(bid);
		
		return "redirect:/noticeListView";
	}
	
	@RequestMapping(value = "/noticeUpdate")		// ???????????? ??????
	public String noticeUpdate(noticeDto dto,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String content = request.getParameter("content");
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		content = content.replace("\r\n", "<br>");
		dto.setContent(content);
		communityservice.noticeUpdate(dto);
		return "redirect:/noticeListView";
	}
	
	@RequestMapping(value = "/noticeReplyAdd", method=RequestMethod.POST)	// ?????? ??????
	public String noticeReplyAdd(noticeDto dto,HttpServletRequest request,RedirectAttributes redirectAttributes) {	
		String replycontent = request.getParameter("replycontent");
		replycontent = replycontent.replace("\r\n", "<br>");
		dto.setReplycontent(replycontent);
		
		communityservice.addNoticeReply(dto);
		redirectAttributes.addAttribute("bid", dto.getBid());
		return "redirect:/noticeReplyList";
	}
	
	@RequestMapping(value = "/noticeReplyList")		// ?????? ????????? 
	public String noticeReplyList(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String bidRequest = request.getParameter("bid");
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		redirectAttributes.addAttribute("bid",bidRequest);
		return "redirect:/noticeView";
	}
	
	@RequestMapping(value = "/deleteReply", method = RequestMethod.GET)		//?????? ??????
	public String deleteReply(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String bidRequest = request.getParameter("bid");
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		String replycontent = request.getParameter("replycontent");
		redirectAttributes.addAttribute("bid",bidRequest);
		communityservice.deleteNoticeReply(replycontent);
		return "redirect:/noticeView";
	}
	
	@RequestMapping(value = "/Search", method = RequestMethod.POST)	
	public String getSearch(Model model,HttpServletRequest request,HttpSession session) {
		String page = request.getParameter("pagenum");
		int pagenum = Integer.parseInt(page);
		int a = (pagenum*10)-10;
		String menu = request.getParameter("Search");
		String sear = request.getParameter("Sear");
		List<noticeDto> searchList = communityservice.getSearch(menu, sear,a);	
		List<noticeDto> searchPageList = communityservice.SearchPage(menu, sear);
		List<noticeDto> generalList = communityservice.generalList();
		
		noticeList = communityservice.noticeList();

		
		model.addAttribute("noticeList",noticeList);

		model.addAttribute("generalList",generalList);
		model.addAttribute("searchList",searchList);
		session.setAttribute("searchPageList",searchPageList);
		
		session.setAttribute("menu", menu);
		session.setAttribute("sear", sear);
		
		return "Community/NoticeSearch";
	}
	
	@RequestMapping(value = "/SearchPage", method = RequestMethod.GET)
	public String SearchPage(Model model,HttpServletRequest request) {
		String page = request.getParameter("pagenum");
		int pagenum = Integer.parseInt(page);
		int a = (pagenum*10)-10;
		String menu = request.getParameter("Search");
		String sear = request.getParameter("Sear");
		List<noticeDto> searchList = communityservice.getSearch(menu, sear,a);	
		noticeList = communityservice.noticeList();

		List<noticeDto> generalList = communityservice.generalList();
		model.addAttribute("noticeList",noticeList);

		model.addAttribute("generalList",generalList);
		model.addAttribute("searchList",searchList);
		return "Community/NoticeSearch";
	}
	//------------------------------------------------------------------------------------------------

	//-----------------------------------------?????????????????? ??????------------------------------------------
	@RequestMapping(value = "/teamDetail", method = RequestMethod.GET)
	public String teamDetail(memberDto dto,Model model) {
		memberList = organizationService.DepartmentMember(dto);
		model.addAttribute("memberList",memberList);
		departmentList = organizationService.departmentList();
		model.addAttribute("departmentList",departmentList);
		return "Organization/User/userOrganizationDetail";
		
	}
	
	//===========================================????????????=======================================================
	
	@RequestMapping(value="/TaskAdd", method = RequestMethod.POST)
	public String TaskAdd(TaskDto dto,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		taskservice.addTask(dto);
		String id = request.getParameter("id");
		redirectAttributes.addAttribute("id",id);
		return "redirect:/taskList";
	}
	@RequestMapping(value="/taskList")
	public String taskList(HttpServletRequest request, Model model)
	{
		String id = request.getParameter("id");
		taskList = taskservice.TaskList(id);
		model.addAttribute("taskList",taskList);
		return "Task/userTask";
	}
	
	@RequestMapping(value = "/updateTask")
	public String updateTask(TaskDto dto,RedirectAttributes redirectAttributes,HttpServletRequest request)
	{
		String updatebol = request.getParameter("taskUpdate");
		if(updatebol!=null) {
			taskservice.updateTask(dto);
			String id = request.getParameter("id");
			redirectAttributes.addAttribute("id",id);
			return "redirect:/taskList";
		}
		else {
			taskservice.deleteTask(dto);
			String id = request.getParameter("id");
			redirectAttributes.addAttribute("id",id);
			return "redirect:/taskList";
		}
	}
	//----------------------------------------------------------------------
	//----------------------------????????????------------------------------------
	 @RequestMapping(value="/newProject") //???????????? ??????
   public String newProject(HttpServletRequest request)
   {
	   String[] idlist=request.getParameterValues("id");
	   String title=request.getParameter("title");
	   int maxGroup=taskservice.maxGroup()+1;
	   for(int i=0;i<idlist.length;i++)
	   {
		 taskservice.addProject(title, idlist[i],maxGroup);
	   }
	   //maxGroup??? ???????????? ?????????????????? ???????????? ?????????
	   return "redirect:/userTeamSchedule";
   }
   
   @RequestMapping(value="/newTask")
   public String newTask(HttpSession session,HttpServletRequest request,Model model,HttpServletResponse response) throws Exception
   {
	   
	   int number=(Integer)session.getAttribute("projectNumber");
	   
	   String task = request.getParameter("task");
	   
	   int result = taskservice.addProjectTask(number, task);
	   
	   if(result==-1)
	   {
		
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('?????????????????? ????????????????????????.');history.back();</script>");
			out.close();

		   
	   }
	
	 
	   
	   return "redirect:/userTeamSchedulDetaleContinue";
   }
   @RequestMapping(value="/newtaskDetail")
   public String newtaskDetail(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   String taskdetail = request.getParameter("taskdetail");
	   String task = request.getParameter("task");
	   int number=(Integer)session.getAttribute("projectNumber");
	   memberdto=(memberDto)session.getAttribute("user");
	   String id=memberdto.getId();
	   
	   int result = taskservice.addtaskDetail(id,number, task, taskdetail);
	   
	   if(result==-1)
	   {
		
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('?????? ??????????????? ?????? ????????? ????????????????????????.');history.back();</script>");
			out.close();

	}
	   
	   
	   return "redirect:/userTeamSchedulDetaleContinue";
   }
   @RequestMapping(value="/taskDelete", method=RequestMethod.GET)
   public String taskDelete(HttpSession session,HttpServletRequest request)
   {
	   int number=(Integer)session.getAttribute("projectNumber");
	   String task=request.getParameter("task");
	   taskservice.projecttaskDelete(number, task);
	   return "redirect:/userTeamSchedulDetaleContinue";
   }
   
   @RequestMapping(value="/taskdetailModify", method=RequestMethod.GET)
   public String taskdetailModify(HttpSession session,HttpServletRequest request)
   {
	   int number=(Integer)session.getAttribute("projectNumber"); 
	   String stringchoice=request.getParameter("choice");
	   int choice = Integer.parseInt(stringchoice);
	   String task = request.getParameter("task");
	   String taskdetail = request.getParameter("taskdetail");
	   memberdto=(memberDto)session.getAttribute("user");
	   String id=memberdto.getId();
	   
	   taskservice.taskdetailProgress(id,choice, number, task, taskdetail);
	   
	   return "redirect:/userTeamSchedulDetaleContinue";
	   
   }
	   @RequestMapping(value="/projectImportant", method=RequestMethod.GET)
   public String projectImportant(HttpServletRequest request,HttpSession session)
   {
	   int number=(Integer)session.getAttribute("projectNumber");
	   String stringimportant = request.getParameter("important");
	   int important = Integer.parseInt(stringimportant);
	   taskservice.projectImportant(important, number);
	   
	   return "redirect:/userTeamSchedulDetaleContinue";
   }
	   @RequestMapping(value="/projectDelete", method=RequestMethod.GET)
	   public String projectDelete(HttpSession session,HttpServletRequest request)
	   {
		   String delnum = request.getParameter("number");
		   String title = request.getParameter("title");
		   int number = Integer.parseInt(delnum);
		   
		   taskservice.deleteProjectDetail(number);
		   taskservice.deleteProjecttask(number);
		   taskservice.deleteProject(number, title);
		   return "redirect:/userTeamSchedule";
	}
	@RequestMapping(value="/projectFileupload", method=RequestMethod.POST)
	public String projectFileupload(RedirectAttributes redirectAttributes,HttpServletRequest request, @RequestParam("file2") MultipartFile file) throws Exception
	{
		String Strnumber = request.getParameter("number");
		int number = Integer.parseInt(Strnumber);
		
		String fileName = file.getOriginalFilename();
		String path2 = "C:\\apache-tomcat-8.5.32\\wtpwebapps\\Test1018\\resources\\filelist";
		byte[] files = file.getBytes();
		
		System.out.println(number);
		
		taskservice.addProjectFile(number, fileName, files);
		BufferedOutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(new File(path2,fileName)));

		outputStream2.write(file.getBytes());
		outputStream2.flush();
		outputStream2.close();
		redirectAttributes.addAttribute("number",number);
		return "redirect:/userTeamSchedulDetaleContinue";

	}
	@RequestMapping(value="/fileDownload", method=RequestMethod.GET)
	public String fileDownload(RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
		String path =  "C:\\apache-tomcat-8.5.32\\wtpwebapps\\Test1018\\resources\\filelist";
        
        String filename = request.getParameter("name");
 
        String realPath = "";
        
         
        try {
            String browser = request.getHeader("User-Agent"); 
            //?????? ????????? 
            if (browser.contains("MSIE") || browser.contains("Trident")
                    || browser.contains("Chrome")) {
                filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+",
                        "%20");
            } else {
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException");
        }
        realPath = path +"/"+filename;
        System.out.println(realPath);
       //File file1 = new File(realPath);
        
        // ????????? ??????        
        response.setContentType("application/octer-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        try {
        	
            OutputStream os = response.getOutputStream();
            
            FileInputStream fis = new FileInputStream(new File(realPath));
            
            int ncount = 0;
            byte[] bytes = new byte[1024];
 
            while ((ncount = fis.read(bytes)) != -1 ) {
                os.write(bytes, 0, ncount);
            }
            fis.close();
            os.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        } catch (IllegalStateException ex) {
        	System.out.println("IllegalStateException");
        }
        
		return null;

	}
	@RequestMapping(value="/fileDelete", method=RequestMethod.GET)
	public String fileDelete(HttpServletRequest request,RedirectAttributes redirectAttributes)
	{
		String Strnumber = request.getParameter("number");
		int number = Integer.parseInt(Strnumber);
		
		String title = request.getParameter("name");
		taskservice.deleteProjectFile(title);
		redirectAttributes.addAttribute("number",number);
		return "redirect:/userTeamSchedulDetaleContinue";

	}
	
	//-----------------------????????? ??????-------------------------------------
	@RequestMapping(value = "/processHoliday", method =RequestMethod.POST)
	public String processHoliday(HttpServletRequest request)
	{
		
		String process=request.getParameter("process");
		String id=request.getParameter("id");
		String holidaytype=request.getParameter("holidaytype");
		holidayservice.processHoliday(id, process,holidaytype);
		
		return "redirect:/adminHoliday";
	}
	
	
}
