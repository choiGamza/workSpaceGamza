package com.spring.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    int seq;
    String userId;
    String userPw;
    String userName;
    Date createDate;
    int deleteFlag;

}
