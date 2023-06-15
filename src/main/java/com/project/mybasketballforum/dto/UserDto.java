package com.project.mybasketballforum.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.project.mybasketballforum.pojo.User;


/************************
 * Manage-System
 * com.project.mybasketballforum.dto
 * MHC
 * author : mhc
 * date:  2023/6/13 17:41
 * description : 用来添加token的UserDto
 ************************/

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends User {
    String token;
}
