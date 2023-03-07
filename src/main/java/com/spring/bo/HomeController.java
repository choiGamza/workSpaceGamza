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
	
	// 11/12 16:32 HomeController Mapping 전면수정
	// 11/19 15:30 HomeController 회원 등록/삭제 생성 (최진성)
	// 11/19 15:31 Login했을때 HomeController 에서 인증이 끝나면 세션에  Dto 오브젝트 이름 memberDto
	private int certCharLength = 6;
	private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
             'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
             'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };	// 초기 비밀번호 랜덤함수
	static String emailToRecipient, emailSubject, emailMessage;		//email 제목,내용,상대 email

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
	
	//로그인했을때 Controller처리부문
	//로그인할때 사용자 정보는 dao에서 받아와서 세션으로 객체로 저장
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView Login(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws Exception{
		String viewPage = "Main/home";
		ModelAndView model = new ModelAndView(viewPage);
		
		String id = request.getParameter("ID");				//사용자가 입력한 id 값
		String pw = request.getParameter("PW");				//사용자가 입력한 pw 값
		
		String LoginPw = organizationService.Login(id);			//사용자가 입력한 id 값을 sql문으로 저장되어있는 pw 찾기
		boolean ret = false;

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
		ret = bcryptPasswordEncoder.matches(pw,LoginPw);
		//--------------------------------------------------------------------------------------
		
		//-------------------------------------------- 회원/관리자 인증--------------------------------------------
		
			if(ret == true) 
			{
				viewPage = "Main/home";
				memberdto = organizationService.userInformation(id);
				session.setAttribute("user", memberdto);
				noticeList = communityservice.noticeThreeList();		// home 페이지 3개씩 보여주기 (공지사항)
				boardList = communityservice.boardThreeList();			// home 페이지 3개씩 보여주기 (게시글)
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
				out.println("<script>alert('아이디 또는 비밀번호가 틀렸습니다.');history.back();</script>");
				out.close();
				viewPage ="Main/login";
				model.setViewName(viewPage);
				return model;
			}

	}
//로그인 페이지에서 Find ----- Controller 구문처리부문  //dao 삽입요망 	
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
			organizationService.FindPwUpdate(dto);		// 바뀐 비밀번호 
			
			emailSubject = dto.getName()+" 님 TheJoen 초기화된 비밀 번호 입니다.";
			emailMessage = "변경된 비밀번호 : " + bwf + " 정보변경에서 비밀번호를 변경해주세요.";
			emailToRecipient = dto.getEmail();
			
			System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");

			SimpleMailMessage simpleEmail = new SimpleMailMessage();
			simpleEmail.setTo(emailToRecipient);
			simpleEmail.setSubject(emailSubject);
			simpleEmail.setText(emailMessage);
					
			mailSenderObj.send(simpleEmail);			// email 전송
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('새로운 비밀번호를 email로 보내드렸습니다.');history.back();</script>");
			out.close();
			
			return "Main/login";
		}
		else {
			System.out.println("id 또는 name 또는 email 불일치");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('id 또는 name 또는 email 불일치.');history.back();</script>");
			out.close();
			return "Main/login";
		}
	}

	//-----------------------------조직관리(관리자 메뉴 Mapping)----------------------------
	@RequestMapping(value="/adminRegister") // 회원등록/삭제
		public String adminRegister(Model model) 
	{

		return "redirect:/memberList";
	}
	@RequestMapping(value="/adminOrganization") // 부서관리
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
	//-------------------------------조직관리(사용자 메뉴 Mapping)---------------------------------------
	@RequestMapping(value="/userOrganization")  //회사조직도(사용자)
	public String userOrganization(Model model)
	{
		departmentList = organizationService.departmentList();
		model.addAttribute("departmentList",departmentList);
		return "Organization/User/userOrganization";
	}
	
	
	
	
	//-------------------------------업무관리(사용자 메뉴 Mapping)---------------------------------
	@RequestMapping(value="/userTeamSchedule") //팀일정 userTeamSchedule
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
	@RequestMapping(value="/userTask") //자신의 업무일지
	public String userTask(RedirectAttributes redirectAttributes,HttpServletRequest request) 
	{
		String id = request.getParameter("id");
		redirectAttributes.addAttribute("id",id);
		return "redirect:/taskList";
	}
	
	//-------------------------------휴가(관리자 메뉴 Mapping)----------------------------	
	@RequestMapping(value = "/adminHoliday", method = RequestMethod.GET) //휴가관리(관리자)
	public String adminHoliday(Model model) 
	{
			holidayList = holidayservice.holidayList();
			model.addAttribute("holidaylist", holidayList);
			return "Holiday/Admin/adminHoliday";
	}
	
	@RequestMapping(value = "/adminHolidayDetail", method = RequestMethod.GET) //휴가관리(관리자)
	public String adminHolidayDetail(Model model,HttpServletRequest request) 
	{
			String id=request.getParameter("id");
			String holidaytype=request.getParameter("holidaytype");
			holidaydto=holidayservice.showDetail(id,holidaytype);
			model.addAttribute("holidaydto",holidaydto);
		//holidaydto를 아이디를 통해 가져와서 model에 object를 추가시켜줌
			return "Holiday/Admin/adminHolidayDetail";
	}
	
	
	
	
	//-------------------------------휴가(사용자 메뉴 Mapping)----------------------------
	
	@RequestMapping(value = "/userHolidayApply", method = RequestMethod.GET) //휴가신청
	public String userHolidayApply(HttpSession session) { 
		
			return "Holiday/User/userHolidayApply";

	}
	
	@RequestMapping(value ="/userHolidayCheck", method = RequestMethod.GET) //휴가조회
	public String userHolidayCheck(HttpSession session,Model model) 
	{
		memberdto = (memberDto)session.getAttribute("user");
		String id=memberdto.getId();
		holidayList=holidayservice.checkMyHoliday(id);
		
		model.addAttribute("holidaylist", holidayList);
		
		return "Holiday/User/userHolidayCheck";
	}
	
	
	//-----------------------------------커뮤니티-------------------------------------------
	@RequestMapping(value="/Notice")  //공지사항
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
	//------------------------- MainMenu home으로 다시이동------------------
	@RequestMapping(value="/adminMain")
	public ModelAndView adminHome(HttpServletRequest request) 
	{
		String id = request.getParameter("id");
		ModelAndView model = new ModelAndView("Main/home");
		noticeList = communityservice.noticeThreeList();		// home 페이지 3개씩 보여주기 (공지사항)
		boardList = communityservice.boardThreeList();			// home 페이지 3개씩 보여주기 (게시글)
		taskList = taskservice.TaskList(id);
		model.addObject("noticeList",noticeList);
		model.addObject("boardList",boardList);
		model.addObject("taskList",taskList);
		return model;
	}
	//로그아웃 했을때 메인페이지로 이동 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("admin"); //관리자 권한 삭제 
		return "Main/login";
	}
	
	
	//=======================================================================================================
	//=======================================================================================================
    //=======================================================================================================
	//============================================기능========================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	
	
	
	//---------------------------부서등록----------------------------------
	
	
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
	
	
	//------------------------------회원등록--------------------------------
	
	
	@RequestMapping(value="/addMember", method = RequestMethod.POST)
	public String addMember(memberDto dto, HttpServletRequest request) {
		//----------------------초기 비밀번호 랜덤 함수값 -------------------------------------------
		Random random = new Random(System.currentTimeMillis());
		int tableLength = characterTable.length;
		
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < certCharLength; i++) {
			buf.append(characterTable[random.nextInt(tableLength)]);
		}
		String bwf = buf.substring(0);

		System.out.println(dto.getDepartment());
		
		//-----------------------암호화------------------------------------------------------------
		
		String hashedpassword = bcryptPasswordEncoder.encode(bwf);
		System.out.println("hashed pw   "+hashedpassword);
		dto.setPw(hashedpassword);
		organizationService.MemberRegister(dto);			// 회원 정보DB에 저장	--> 초기 비밀번호 암호화해서 저장 --> 비밀번호 변경 할때도 암호화해서 다시 저장
	
		//-----------------------------------------------------------------
		//------------------------------회원등록하면 부서에 1명 추가---------------------

		organizationService.plusTotalNum(dto.getDepartment());
		
		//---------------------------------------------------------------------
		//-----------------------등록 버튼 누르면 여기서 자동으로 email 보내는 함수--------------
		// 1. creates a simple e-mail object
		
		emailSubject = dto.getName()+" 님 TheJoen 입사에 축하드립니다.";
		emailMessage = "사원 ID : "+dto.getId() + " 초기비밀번호 : " + bwf;
		emailToRecipient = request.getParameter("email");
		
		System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");

		SimpleMailMessage simpleEmail = new SimpleMailMessage();
		simpleEmail.setTo(emailToRecipient);
		simpleEmail.setSubject(emailSubject);
		simpleEmail.setText(emailMessage);
				
		mailSenderObj.send(simpleEmail);			// email 전송
		
		return "redirect:/memberList";
	}
	
	@RequestMapping(value="/memberList")		// 회원 목록
	public String memberList(Model model)
	{
		departmentList = organizationService.departmentList();
		model.addAttribute("departmentList",departmentList);			
		memberList = organizationService.MemberList();
		
		model.addAttribute("memberList",memberList);
		return "Organization/Admin/adminRegister";
		
	}
	
	//------------------------------------------------------------------------------------------------
	
	//-----------------------------휴가 등록----------------------------------------------------------------
	
	@RequestMapping(value = "/writeHoliday", method = RequestMethod.POST)
	public String writeHoliday(HttpSession session,HttpServletRequest request,Model model) { //휴가신청 기능
		memberdto = (memberDto)session.getAttribute("user");
		holidayDto holidaydto = new holidayDto();
		//-------------  이부분은 이제 DAO 추가하면 바뀔것 ------------------
		String id =memberdto.getId();
		String name =memberdto.getName();
		String department = memberdto.getDepartment();
		String holidaytype = request.getParameter("holidaytype");
		String startday = request.getParameter("startday");
		String endday = request.getParameter("endday");
		String content = request.getParameter("content");
		String position = memberdto.getPosition();
		String result = "대기";
		if(holidaytype.equals("vacation")) { ///연차
			holidaytype="연차";
	      }
	      else if(holidaytype.equals("vacation2")) { //반차
	    	  holidaytype="반차";
	      }
	      else { //특별사유
	    	  holidaytype="특별사유";
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
		//holidaydto를 가져오는 메소드인데 추가를하기전 id와 type을 넣어보고 이미 리스트에 있으면 휴가를 신청할수 없도록 설정
		if(holidayservice.showDetail(holidaydto.getId(), holidaydto.getHolidaytype())==null)
		{
			
			holidayservice.applyHoliday(holidaydto);
		}
		model.addAttribute("alert", true); //dao에 값을 다 집어넣으면 다시 휴가신청 돌아가서 신청되었다고 알림뜨게 하는 모델
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
	
	//--------------------------------정보변경-------------------------------------------------------
	private String genSaveFileName(String start,String extName) {
	    String fileName = "";
	     
	    fileName = start + extName;
	    System.out.println("genSaveFileName() : "+fileName); 
	    return fileName;
	  }
	
	@RequestMapping(value = "/changeInfo")				// 사용자가 직접 변경한다
	public String changeInfo(Model model, memberDto dto,HttpServletRequest request ,HttpServletResponse response, @RequestParam("file") MultipartFile file) throws Exception{
		
		if(file.isEmpty()==true) {					// 이미지를 안넣음
			System.out.println("이미지 없음");
			if(dto.getPw().equals("")) {			// 비밀번호도 안넣음 
				dto.setPw(request.getParameter("pw2"));
				organizationService.changeInfo(dto);
				
			}
			else {									// 비밀번호 변경
				String hashedpassword = bcryptPasswordEncoder.encode(dto.getPw());
				dto.setPw(hashedpassword);
				
				organizationService.changeInfo(dto);
				
				
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>alert('비밀번호가 변경되었습니다..');history.back();</script>");
				
				out.close();
				
			}
			noticeList = communityservice.noticeThreeList();
			
			boardList = communityservice.boardThreeList();
			
			model.addAttribute("noticeList",noticeList);
			
			model.addAttribute("boardList",boardList);
			
			
			return "Main/home";
		}
		else {										//이미지 넣음
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
				out.println("<script>alert('프로필이 변경되었습니다..');history.back();</script>");
				out.close();
			  }
			  else {
				String hashedpassword = bcryptPasswordEncoder.encode(dto.getPw());
				dto.setPw(hashedpassword);
				organizationService.changeInfo(dto);
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('프로필과 비밀번호가 변경되었습니다..');history.back();</script>");
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
		String title = request.getParameter("AdminUpdate");				//수정인지 삭제인지 알아보는 이름
		String id = request.getParameter("id");						
		String nowDepartment = request.getParameter("nowDepartment");	//수정하기전 사용자의 department

		if(title!=null) {
			organizationService.AdminUserChangeInfo(dto);		// 관리자가 수정한 메소드
			organizationService.plusTotalNum(dto.getDepartment());	//부서가 옮겨지면 새롭게 옮겨진 부서의 인원 +1
			organizationService.deleteTotalNum(nowDepartment);	// 부서가 옮겨지면 원래있던 부서의 인원은 -1
			noticeList = communityservice.noticeThreeList();
			boardList = communityservice.boardThreeList();
			model.addAttribute("noticeList",noticeList);
			model.addAttribute("boardList",boardList);
			return "Main/home";
			
		}
		else {
			organizationService.AdminUserDeleteInfo(id);
			organizationService.deleteTotalNum(nowDepartment);	// 사용자의 데이터가 삭제 되면 원래있던 부서의 인원은 -1
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
	
	//----------------------------------------공지사항/게시글--------------------------------------------------
	
	@RequestMapping(value = "/noticeAdd")			// 공지사항 or 게시글 생성
	public String noticeAdd(noticeDto dto,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		
		String important = request.getParameter("important");		//중요 체크
		String content = request.getParameter("content");
		content = content.replace("\r\n", "<br>");					// 내가 쓴대로 글 보여주는 것 줄바꿈
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
	
	@RequestMapping(value = "/noticeListView")		// 공지사항 리스트
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
	
	@RequestMapping(value = "/noticeView")			// 클릭한 공지사항 글 
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
	
	@RequestMapping(value = "/deleteNotice")		// 공지사항 삭제
	public String noticeDelete(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		String bidString = request.getParameter("bid");
		int bid = Integer.parseInt(bidString);
		
		communityservice.noticeDelete(bid);
		
		return "redirect:/noticeListView";
	}
	
	@RequestMapping(value = "/noticeUpdate")		// 공지사항 수정
	public String noticeUpdate(noticeDto dto,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String content = request.getParameter("content");
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		content = content.replace("\r\n", "<br>");
		dto.setContent(content);
		communityservice.noticeUpdate(dto);
		return "redirect:/noticeListView";
	}
	
	@RequestMapping(value = "/noticeReplyAdd", method=RequestMethod.POST)	// 댓글 달기
	public String noticeReplyAdd(noticeDto dto,HttpServletRequest request,RedirectAttributes redirectAttributes) {	
		String replycontent = request.getParameter("replycontent");
		replycontent = replycontent.replace("\r\n", "<br>");
		dto.setReplycontent(replycontent);
		
		communityservice.addNoticeReply(dto);
		redirectAttributes.addAttribute("bid", dto.getBid());
		return "redirect:/noticeReplyList";
	}
	
	@RequestMapping(value = "/noticeReplyList")		// 댓글 리스트 
	public String noticeReplyList(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String bidRequest = request.getParameter("bid");
		redirectAttributes.addAttribute("pagenum",new Integer(1));
		redirectAttributes.addAttribute("bid",bidRequest);
		return "redirect:/noticeView";
	}
	
	@RequestMapping(value = "/deleteReply", method = RequestMethod.GET)		//댓글 삭제
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

	//-----------------------------------------사용자조직도 확인------------------------------------------
	@RequestMapping(value = "/teamDetail", method = RequestMethod.GET)
	public String teamDetail(memberDto dto,Model model) {
		memberList = organizationService.DepartmentMember(dto);
		model.addAttribute("memberList",memberList);
		departmentList = organizationService.departmentList();
		model.addAttribute("departmentList",departmentList);
		return "Organization/User/userOrganizationDetail";
		
	}
	
	//===========================================업무일지=======================================================
	
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
	//----------------------------프로젝트------------------------------------
	 @RequestMapping(value="/newProject") //프로젝트 생성
   public String newProject(HttpServletRequest request)
   {
	   String[] idlist=request.getParameterValues("id");
	   String title=request.getParameter("title");
	   int maxGroup=taskservice.maxGroup()+1;
	   for(int i=0;i<idlist.length;i++)
	   {
		 taskservice.addProject(title, idlist[i],maxGroup);
	   }
	   //maxGroup은 그룹값을 분간하기위해 최대값을 넣는것
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
			out.println("<script>alert('리스트이름이 중복되어있습니다.');history.back();</script>");
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
			out.println("<script>alert('같은 리스트안에 업무 내용이 중복되어있습니다.');history.back();</script>");
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
            //파일 인코딩 
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
        
        // 파일명 지정        
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
	
	//-----------------------관리자 기능-------------------------------------
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
