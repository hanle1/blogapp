package com.aheizi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aheizi.dao.CommentMapper;
import com.aheizi.domain.Comment;
import com.aheizi.service.CommentService;

@Service("commentService")
public class CommenServiceImpl implements CommentService {
	@Resource
	private CommentMapper commentMapper;

	@Override
	public List<Comment> getCommentByBlogId(String blogId) {
		return commentMapper.getCommentByBlogId(blogId);
	}

	@Override
	public void insertComment(Comment comment) {
		commentMapper.insertComment(comment);
	}

}
