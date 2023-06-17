package com.project.mybasketballforum;

import com.project.mybasketballforum.dto.CategoryDto;
import com.project.mybasketballforum.pojo.*;
import com.project.mybasketballforum.service.CommentService;
import com.project.mybasketballforum.service.UserService;
import com.project.mybasketballforum.service.impl.CategoryServiceImpl;
import com.project.mybasketballforum.service.impl.CommentServiceImpl;
import com.project.mybasketballforum.service.impl.PostServiceImpl;
import com.project.mybasketballforum.service.impl.TipServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MyBasketballForumApplicationTests {

    @Autowired
    private UserService UserService;

    @Autowired
    private CommentServiceImpl commentServiceimpl;

    @Autowired
    private CategoryServiceImpl categoryServiceimpl;

    @Autowired
    private PostServiceImpl postServiceimpl;

    @Autowired
    private TipServiceImpl tipServiceimpl;

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

    //测试查询所有板块
    @Test
    void selectAllCategoryTest(){
        List<CategoryDto> list = categoryServiceimpl.selectAllCategory();
        for (CategoryDto categoryDto : list) {
            System.out.println(categoryDto);
        }
    }

    @Test
    void getTip(){
        Tip tip = tipServiceimpl.getTip();
        System.out.println(tip);
    }

    @Test
    void addPost(){
        String title = "test";
        String content = "test";
        Integer categoryId = 1;
        Integer userId = 1;
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategoryId(categoryId);
        post.setUserId(userId);
        boolean success = postServiceimpl.addPost(post);
        System.out.println(success);
    }
}
