package com.aheizi.dao;

import java.util.List;

import com.aheizi.domain.CommentReply;

public interface CommentReplyMapper {
	CommentReply getCommentReplyByCommentId(String commentId);

	void insertCommentReply(CommentReply commentReply);

	List<CommentReply> getCommentReplyByBlogId(String blogId);
}
