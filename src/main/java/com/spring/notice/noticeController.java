package com.spring.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value="/notice")
public class noticeController {

    @Autowired
    noticeService service;

    @PostMapping("/noticeLoad")
    public Map<String,Object> notice() {
        Map<String,Object> result = service.notice();

        return result;
    }
}
