package com.project.mybasketballforum;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.dto.*;
import com.project.mybasketballforum.mapper.PostMapper;
import com.project.mybasketballforum.pojo.*;
import com.project.mybasketballforum.service.UserService;
import com.project.mybasketballforum.service.impl.*;
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

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private TagServiceImpl tagServiceimpl;

    @Autowired
    private FavorServiceImpl favorServiceimpl;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostcardServiceImpl postcardServiceimpl;

    @Autowired
    private ThumbServiceImpl thumbServiceimpl;

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
        String userName = "";

        boolean success = commentServiceimpl.addComment(content, upperId, userName); // 调用实例方法
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
        //post.setCategoryName(categoryName);
        post.setUserId(userId);
        boolean success = postServiceimpl.addPost(post);
        System.out.println(success);
    }

    //测试查找最后一条帖子
    @Test
    void selectLastPost(){
        Integer id = postServiceimpl.selectLastPostId();
        System.out.println(id);
    }

    //根据postId查找标签
    @Test
    void getTagsByPostId() {
        Integer postId = 1;
        List<TagDto> tagList = tagServiceimpl.getTagsByPostId(postId);
        for (TagDto tag : tagList) {
            System.out.println(tag);
        }
    }

    //测试返回一条帖子卡片的消息
    @Test
    void getPostCard(){
        PostCardDto post = postServiceimpl.getOnePost();
        System.out.println(post);
    }

    //测试返回帖子列表
    @Test
    void getPostList(){
        List<PostCardListDto> list = postServiceimpl.getPostList();
        for (PostCardListDto postCardDto : list) {
            System.out.println(postCardDto);
        }
    }

    @Test
    void getPostListByCategoryId() {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getTitle, null);
        List<Post> list = postServiceimpl.list(wrapper);
        if (list.isEmpty()) {
            System.out.println("null");
        }
    }

    //测试新建的数据库表
    @Test
    void testNewTable() {
        List<Post> postList = postMapper.selectList(null);
        postcardServiceimpl.postToPostcard(postList);
    }

    //测试返回文章详情
    @Test
    void getPostDetail() {
        Integer postId = 1;
        PostViewDto post = postServiceimpl.getPostViewById(postId);
        System.out.println(post);
    }

    //测试浏览量的增加
    @Test
    void updateViewCountTest() {
        Integer postId = 1; // 假设要更新的帖子ID为1

        // 调用更新浏览量的方法
        boolean success = postServiceimpl.updateViewCount(postId);

        if (success) {
            System.out.println("帖子浏览量更新成功");
        } else {
            System.out.println("帖子浏览量更新失败");
        }
    }

    @Test
    void addFavorTest(){
        Integer userId = 2;
        Integer postId = 1;

        boolean success = favorServiceimpl.addFavor(userId, postId); // 调用实例方法
        if (success) {
            System.out.println("添加收藏成功.");
        } else {
            System.out.println("添加收藏失败.");
        }
    }

    @Test
    void delFavorTest(){
        Integer userId = 2;
        Integer postId = 2;

        boolean success = favorServiceimpl.delFavor(userId, postId);
        if (success) {
            System.out.println("取消收藏成功.");
        } else {
            System.out.println("取消收藏失败.");
        }
    }

    @Test
    void getTagggggsByPostId(){
        List<TagDto> tagList= tagServiceimpl.getTagsByPostId(8);
        for (TagDto tag:tagList){
            System.out.println(tag);
        }
    }

    @Test
    void getCategoryInfoTest(){
        String categoryName = "NBA";
        CategoryInfoDto categoryInfoDto = categoryServiceImpl.getCategoryInfo(categoryName);
        System.out.println(categoryInfoDto);
    }

    @Test
    void addLike(){
        Integer userId = 5;
        Integer postId = 1;

        boolean success = thumbServiceimpl.addLike(userId, postId); // 调用实例方法
        if (success) {
            System.out.println("添加点赞成功.");
        } else {
            System.out.println("添加点赞失败.");
        }
    }

    @Test
    void listLiked(){
        Integer userId = 5;
        List<Postcard> postlist = thumbServiceimpl.listLiked(userId);
        if(postlist == null)
            System.out.println("null");
        for (Postcard post : postlist) {
            System.out.println(post);
        }
    }

    @Test
    void deletePost(){
        Integer postId = 14;
        postcardServiceimpl.deletePostcard(postId);
    }

    @Test
    void getPostById(){
        Integer postId = 1;
        Postcard post = postcardServiceimpl.getPostcardById(postId);
        System.out.println(post);
    }

    @Test
    void getHeatOrderTest(){
        String categoryName = "NBA";
        System.out.println(categoryServiceImpl.getCategoryHeatOrder(categoryName));
    }

    //列出所有评论
    @Test
    void listAllComment(){
        Integer postId = 1;
        List<Comment> list = commentServiceimpl.listAllComments(postId);
        for (Comment commentDto : list) {
            System.out.println(commentDto);
        }
    }
}
