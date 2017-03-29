package com.aheizi.service;

import java.util.List;

import com.aheizi.domain.CommentReply;

public interface CommentReplySevice {
	CommentReply getCommentReplyByCommentId(String commentId);

	List<CommentReply> getCommentReplyByBlogId(String blogId);

	void insertCommentReply(CommentReply commentReply);
}
