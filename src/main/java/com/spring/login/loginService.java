package com.spring.login;

import com.spring.mapper.loginMapper;
import com.spring.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class loginService {

    @Autowired
    loginMapper mapper;

    @Autowired
    private JavaMailSender mailSenderObj;

    public Map<String,Object> login(Map<String,Object> map, HttpSession session) {
        ModelAndView model = new ModelAndView();

        Map<String,Object> result = new HashMap<String,Object>();

        UserVO ret = mapper.login(map);

        if(ret != null) {
            session.setAttribute("userInfo", ret);
            result.put("Success", "true");
        }else{
            session.setAttribute("userInfo", "");
            result.put("Success", "false");
        }
        return result;
    }

    public Map<String,Object> findPassword(UserVO vo) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = mapper.FindPwSuccess(vo);

        result.put("userInfo", map);

        return result;

//        if(ret == 1) {
//
//            organizationService.FindPwUpdate(dto);		// 바뀐 비밀번호
//
//            emailSubject = dto.getName()+" 님 TheJoen 초기화된 비밀 번호 입니다.";
//            emailMessage = "변경된 비밀번호 : " + bwf + " 정보변경에서 비밀번호를 변경해주세요.";
//            emailToRecipient = dto.getEmail();
//
//            System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
//
//            SimpleMailMessage simpleEmail = new SimpleMailMessage();
//            simpleEmail.setTo(emailToRecipient);
//            simpleEmail.setSubject(emailSubject);
//            simpleEmail.setText(emailMessage);
//
//            mailSenderObj.send(simpleEmail);			// email 전송
//
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('새로운 비밀번호를 email로 보내드렸습니다.');history.back();</script>");
//            out.close();
//
//            return "Main/login";
//        }
//        else {
//            System.out.println("id 또는 name 또는 email 불일치");
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('id 또는 name 또는 email 불일치.');history.back();</script>");
//            out.close();
//            return "Main/login";
//        }

    }
}
