package com.aheizi.service;

import java.util.List;

import com.aheizi.domain.Comment;

public interface CommentService {
	public List<Comment> getCommentByBlogId(String blogId);

	public void insertComment(Comment comment);
}
