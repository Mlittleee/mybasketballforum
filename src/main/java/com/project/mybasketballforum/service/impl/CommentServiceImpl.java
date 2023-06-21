package com.project.mybasketballforum.service.impl;

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
    public boolean addComment(String content, Integer upperId, Integer userId) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUpperId(upperId);
        comment.setUserId(userId);
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
