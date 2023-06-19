package com.project.mybasketballforum.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.mybasketballforum.dto.UserDto;
import com.project.mybasketballforum.exception.HaveDisabledException;
import com.project.mybasketballforum.exception.PasswordWrongException;
import com.project.mybasketballforum.pojo.User;
import com.project.mybasketballforum.service.UserService;
import com.project.mybasketballforum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.project.mybasketballforum.utils.RedisConstants.LOGIN_USER_KEY;
import static com.project.mybasketballforum.utils.RedisConstants.LOGIN_USER_TTL;

/**
* @author MHC
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-06-11 14:47:39
*/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //将 HTTP 请求体中的 JSON 或 XML 格式的数据绑定到一个 Map<String, String> 类型的变量
    public UserDto login(@RequestBody Map<String,String> map, HttpSession session){
        String userName = map.get("userName");
        String password = map.get("password");
        //查看该用户是否为新用户
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName,userName);
        User user = getOne(userLambdaQueryWrapper);
        if(user==null){
            //是新用户,自动注册
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setStatus(1);
            user.setRoleId(1);
            //默认为男性(1代表男性,0代表女性)
            user.setGender(1);
            //默认邮箱为空
            user.setEmail("");
            save(user);

            //调试用
            //System.out.println(user);


            //将用户的信息存到session中，这样可以通过过滤器
            //随机生成token作为登录令牌
            String token = UUID.randomUUID().toString();
            UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setToken(token);
            userDto.setUserName(user.getUserName());
            userDto.setPassword(user.getPassword());
            userDto.setEmail(user.getEmail());
            userDto.setGender(user.getGender());
            userDto.setStatus(user.getStatus());
            userDto.setRoleId(user.getRoleId());
            userDto.setSign(user.getSign());

            //System.out.println(userDto);

            Map<String, Object> userMap = BeanUtil.beanToMap(userDto, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            //在setFieldValueEditor中也需要判空
                            .setFieldValueEditor((fieldName,fieldValue) -> {
                                if (fieldValue == null){
                                    fieldValue = "0";
                                }else {
                                    fieldValue = fieldValue + "";
                                }
                                return fieldValue;
                            }));
            //存储
            stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
            //设置有效期
            stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
            return userDto;
        }else{
            if(user.getStatus()==0){
                throw new HaveDisabledException("用户已被禁用");
            }else if(!Objects.equals(user.getPassword(), password)){
                throw new PasswordWrongException("密码错误");
            }else{
                //将用户的信息存到session中，这样可以通过过滤器
                //随机生成token作为登录令牌
                String token = UUID.randomUUID().toString();
                session.setAttribute(token,user);
                UserDto userDto = new UserDto();
                userDto.setUserId(user.getUserId());
                userDto.setToken(token);
                userDto.setUserName(user.getUserName());
                userDto.setEmail(user.getEmail());
                userDto.setGender(user.getGender());
                userDto.setPassword(user.getPassword());
                userDto.setStatus(user.getStatus());
                userDto.setRoleId(user.getRoleId());
                userDto.setSign(user.getSign());
                Map<String, Object> userMap = BeanUtil.beanToMap(userDto, new HashMap<>(),
                        CopyOptions.create()
                                .setIgnoreNullValue(true)
                                //在setFieldValueEditor中也需要判空
                                .setFieldValueEditor((fieldName,fieldValue) -> {
                                    if (fieldValue == null){
                                        fieldValue = "0";
                                    }else {
                                        fieldValue = fieldValue + "";
                                    }
                                    return fieldValue;
                                }));
                //存储
                stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
                //设置有效期
                stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);


                //调试用
                System.out.println(userDto);

                return userDto;
            }
        }
    }

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }

    //根据主键id查询
    public String selectUserById(Integer userId){
        User user = userMapper.selectById(userId);
        return user.getUserName();
    }

}





