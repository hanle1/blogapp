package com.aheizi.dao;

import java.util.List;

import com.aheizi.domain.Comment;

public interface CommentMapper {
	public List<Comment> getCommentByBlogId(String blogId);

	public void insertComment(Comment comment);
}
