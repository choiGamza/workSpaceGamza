package com.spring.login;

import com.spring.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value="/login")
public class loginController{

    @Autowired
    loginService service;

    @PostMapping("/loginStart")
    public Map<String,Object> login(@RequestBody Map<String,Object> map, HttpSession session) throws Exception {
        Map<String,Object> result = service.login(map, session);
        return result;
    }

    //로그인 페이지에서 Find ----- Controller 구문처리부문  //dao 삽입요망
    @PostMapping("/findPassword")
    public ResponseEntity<Map<String,Object>> FindPwSuccess(@RequestBody UserVO vo) throws Exception{

        Map<String,Object> result = service.findPassword(vo);

        return ResponseEntity.ok().body(result);
    }
}
