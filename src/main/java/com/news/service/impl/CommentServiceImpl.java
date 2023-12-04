package com.news.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.ResponseResult;
import com.news.domain.dto.CommentDto;
import com.news.domain.entity.Comment;
import com.news.domain.entity.SysUser;
import com.news.domain.vo.CommentListVo;
import com.news.domain.vo.NewsCommentVo;
import com.news.service.CommentService;
import com.news.mapper.CommentMapper;
import com.news.service.SysUserService;
import com.news.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author ngz
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
    @Autowired
    private SysUserService sysUserService;
    @Override
    public ResponseResult insertOneComment(CommentDto comment) {
        Long loginId = Long.parseLong(StpUtil.getLoginId().toString());

        Comment comment1 = BeanCopyUtils.copyBean(comment, Comment.class);
        comment1.setUserId(loginId);
        comment1.setCreateTime(DateTime.now());
        boolean save = save(comment1);
        if (save == true){
            return ResponseResult.okResult(200,"添加成功");
        }else return ResponseResult.okResult(200,"网络错误");
    }

    @Override
    public ResponseResult queryOneNewsComment(int newsId,int userId) {
//        int loginId = Integer.parseInt(StpUtil.getLoginId().toString());
        if (newsId<=0){
            return ResponseResult.okResult(202,"非法操作");
        }
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getNewsId,newsId).eq(Comment::getDelFlag,0);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> commentList = list(queryWrapper);
//        无评论
        if (commentList.size()==0 && ObjectUtil.isEmpty(commentList)){
            CommentListVo commentListVo = new CommentListVo();
            commentListVo.setCommentList(new ArrayList<>());
            commentListVo.setReadNumber(5);
            return ResponseResult.okResult(commentListVo);
        }
        int commentCount = commentList.size();
        List commentIds = new ArrayList<>();
        for (int i = 0; i < commentCount; i++) {
            commentIds.add(commentList.get(i).getUserId());
        }
        List<SysUser> UserList = sysUserService.listByIds(commentIds);

        List<NewsCommentVo> commentArticleVos = BeanCopyUtils.copyBeanList(commentList, NewsCommentVo.class);
        int UserListSize = UserList.size();

        for (int i = 0; i < commentCount; i++) {
            Comment comment = commentList.get(i);
            NewsCommentVo newsCommentVo = commentArticleVos.get(i);
            if (comment.getLike()!=null) {
                JSONArray objects = JSONUtil.parseArray(comment.getLike());
                if (!ObjectUtil.isEmpty(objects) && objects != null) {
                    int likeNum = 0;
                    for (Object o : objects) {
                        likeNum += 1;
                        if (Integer.parseInt(o.toString()) == userId) {
                            newsCommentVo.setHasLike(true);
                        }
                    }
                    newsCommentVo.setLikeNum(likeNum);

                }
            }
            for (int j = 0; j < UserListSize; j++) {
                SysUser sysUser = UserList.get(j);
                System.out.println("_________________________");
                System.out.println(newsCommentVo.getUserId());
                System.out.println(sysUser.getUserId());
                if (newsCommentVo.getUserId()==sysUser.getUserId()){
                    newsCommentVo.setAvatarUrl(sysUser.getAvatar());
                    newsCommentVo.setNickName(sysUser.getNickName());
                    if (newsCommentVo.getUserId()== userId){
                        newsCommentVo.setOwner(true);
                    }

                }
            }

        }
        CommentListVo commentListVo = new CommentListVo();
        commentListVo.setCommentList(commentArticleVos);
        commentListVo.setReadNumber(5);

        return ResponseResult.okResult(commentListVo);
    }

    @Override
    public ResponseResult deleteComment(int commentId, int userId) {
        boolean b = removeById(commentId);
        if (b){
            return ResponseResult.okResult(200,"删除成功！");
        }
        return ResponseResult.okResult(202,"删除失败");
    }
}




