package com.aheizi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aheizi.dao.CommentReplyMapper;
import com.aheizi.domain.CommentReply;
import com.aheizi.service.CommentReplySevice;

@Service("commentReplyService")
public class CommentReplyServiceImpl implements CommentReplySevice {
	@Resource
	CommentReplyMapper commentReplyMapper;

	@Override
	public CommentReply getCommentReplyByCommentId(String commentId) {
		return commentReplyMapper.getCommentReplyByCommentId(commentId);
	}

	@Override
	public void insertCommentReply(CommentReply commentReply) {
		commentReplyMapper.insertCommentReply(commentReply);
	}

	@Override
	public List<CommentReply> getCommentReplyByBlogId(String blogId) {
		return commentReplyMapper.getCommentReplyByBlogId(blogId);
	}

}
