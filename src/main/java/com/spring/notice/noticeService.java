package com.spring.notice;

import com.spring.mapper.noticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class noticeService {

    @Autowired
    noticeMapper mapper;

    public Map<String,Object> notice() {
        Map<String,Object> result = new HashMap<String,Object>();

        List<Map<String,Object>> listMap = mapper.notice();

        result.put("data", listMap);

        return result;
    }
}
