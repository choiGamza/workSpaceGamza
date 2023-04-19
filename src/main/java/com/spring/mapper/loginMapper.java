package com.spring.mapper;

import com.spring.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface loginMapper {

    UserVO login(Map<String,Object> map);

    Map<String,Object> FindPwSuccess(UserVO vo);

}
