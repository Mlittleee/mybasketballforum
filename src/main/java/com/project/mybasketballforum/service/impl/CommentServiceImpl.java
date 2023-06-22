package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.pojo.Comment;
import com.project.mybasketballforum.mapper.CommentMapper;
import com.project.mybasketballforum.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(String content, Integer postId, String userName) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPostId(postId);
        comment.setUserName(userName);
        int year = java.time.LocalDate.now().getYear();
        int month = java.time.LocalDate.now().getMonthValue();
        String monthStr = month < 10 ? "0" + month : "" + month;
        int day = java.time.LocalDate.now().getDayOfMonth();
        String dayStr = day < 10 ? "0" + day : "" + day;
        String date = year + "-" + monthStr + "-" + dayStr;
        comment.setCreateTime(date);
        return(this.save(comment));
    }

    @Override
    public boolean deleteComment(Integer commentId) {
        // 调用MyBatis-Plus的removeById方法删除评论
        return removeById(commentId);
    }

    @Override
    public boolean deleteComments(String commentIds) {
        String[] ids = commentIds.split(",");
        for (String id : ids) {
            Integer commentId = Integer.parseInt(id);
            boolean flag = removeById(commentId);
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    //列出所有评论
    @Override
    public List<Comment> listAllComments(Integer postId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getPostId, postId);
        return commentMapper.selectList(wrapper);
    }

    //修改评论内容
    @Override
    public boolean updateComment(Integer commentId, String content) {
        Comment comment = getById(commentId);
        comment.setContent(content);
        return updateById(comment);
    }

    /**
     * 构建评论树
     * @param list
     * @return
     */
   /* public static List<Comment> processComments(List<Comment> list) {
        Map<Long, Comment> map = new HashMap<>();   // (id, Comment)
        List<Comment> result = new ArrayList<>();
        // 将所有根评论加入 map
        for(Comment comment : list) {
            if(comment.getParentId() == null)
                result.add(comment);
            map.put(comment.getId(), comment);
        }
        // 子评论加入到父评论的 child 中
        for(Comment comment : list) {
            Long id = comment.getParentId();
            if(id != null) {   // 当前评论为子评论
                Comment p = map.get(id);
                if(p.getChild() == null)    // child 为空，则创建
                    p.setChild(new ArrayList<>());
                p.getChild().add(comment);
            }
        }
        return result;*/

}
