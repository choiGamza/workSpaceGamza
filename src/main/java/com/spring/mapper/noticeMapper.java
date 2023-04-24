package com.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface noticeMapper {

    public List<Map<String,Object>> notice();
}
