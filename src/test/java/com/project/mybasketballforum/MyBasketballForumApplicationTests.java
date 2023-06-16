package com.project.mybasketballforum;

import com.project.mybasketballforum.controller.CommentController;
import com.project.mybasketballforum.pojo.Comment;
import com.project.mybasketballforum.pojo.User;
import com.project.mybasketballforum.service.CommentService;
import com.project.mybasketballforum.service.UserService;
import com.project.mybasketballforum.service.impl.CommentServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import com.project.mybasketballforum.universal.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MyBasketballForumApplicationTests {

    @Autowired
    private UserService UserService;

    @Autowired
    private CommentServiceImpl commentServiceimpl;

    @Test
    void contextLoads() {
    }
    //单个查询，mybatis-plus中有的方法
    @Test
    void UserServiceTest(){
        System.out.println(UserService.getById(1));
    }

    //自写方法
    @Test
    void UserServiceTest2(){
        List<User> list = UserService.listAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    //测试分页的封装类
    @Test
    void QueryPageParamTest(){
        QueryPageParam query = new QueryPageParam();
        query.setPageNum(1);
        query.setPageSize(2);
        //给空的hashmap添加键值对
        query.setParam(new HashMap());
        query.getParam().put("username","mhc");
        query.getParam().put("password","123456");

        System.out.println("num=="+query.getPageNum());
        System.out.println("size=="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("username");
        System.out.println("name=="+name);
        }

    @Test
    void AddCommentTest() {
        String content = "123test";
        Integer upperId = 0;
        Integer userId = 1;

        boolean success = commentServiceimpl.addComment(content, upperId, userId); // 调用实例方法
        if (success) {
            System.out.println("Comment added successfully.");
        } else {
            System.out.println("Failed to add comment.");
        }
    }

    @Test
    void deleteCommentTest(){
        Integer commentId = 1; // 设置评论的 ID

        boolean success = commentServiceimpl.deleteComment(commentId);
        if (success) {
            System.out.println("Comment deleted successfully.");
        } else {
            System.out.println("Failed to delete comment.");
        }
    }

    @Test
    void deleteCommentsTest() {
        List<Integer> commentIds = Arrays.asList(4 , 5 , 6); // 设置评论的 ID 列表

        boolean success = commentServiceimpl.deleteComments(commentIds);
        if (success) {
            System.out.println("Comments deleted successfully.");
        } else {
            System.out.println("Failed to delete comments.");
        }
    }

}
