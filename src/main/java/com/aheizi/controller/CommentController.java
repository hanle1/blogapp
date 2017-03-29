package com.aheizi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aheizi.domain.Comment;
import com.aheizi.domain.CommentReply;
import com.aheizi.service.CommentReplySevice;
import com.aheizi.service.CommentService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Resource
	private CommentService commentService;
	@Resource
	private CommentReplySevice commentReplySevice;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(@RequestBody String message) {
		JSONObject json = JSONObject.parseObject(message);
		Comment comment = new Comment();
		comment.setBlogId(json.getString("blogId"));
		comment.setContent(json.getString("content"));
		commentService.insertComment(comment);
		return "添加成功";
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	@ResponseBody
	public String addReplyComment(@RequestBody String message) {
		JSONObject json = JSONObject.parseObject(message);
		CommentReply commentReply = new CommentReply();
		commentReply.setReplyId(json.getString("replyId"));
		commentReply.setCommentId(json.getString("commentId"));
		commentReply.setBlogId(json.getString("blogId"));
		commentReply.setContent(json.getString("content"));
		commentReplySevice.insertCommentReply(commentReply);
		return "回复成功";
	}

	@RequestMapping(value = "/{blogId}/comments", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentJsonObject> getAllComments(@PathVariable String blogId) {
		List<Comment> comments = commentService.getCommentByBlogId(blogId);
		List<CommentReply> commentReplys = commentReplySevice
				.getCommentReplyByBlogId(blogId);
		Map<String, CommentJsonObject> map = new HashMap<String, CommentJsonObject>();
		for (CommentReply commentReply : commentReplys) {
			if (!map.containsKey(commentReply.getCommentId())) {
				map.put(commentReply.getCommentId(), new CommentJsonObject());
			}
			map.get(commentReply.getCommentId()).insertCommentReply(
					commentReply);
		}
		for (Comment comment : comments) {
			CommentJsonObject commentJsonObject = map.get(comment.getId());
			if (commentJsonObject == null) {
				commentJsonObject = new CommentJsonObject();
				commentJsonObject.setComment(comment);
				map.put(comment.getId(), commentJsonObject);
			} else {
				map.get(comment.getId()).setComment(comment);
			}
		}
		List<CommentJsonObject> list = new ArrayList<CommentJsonObject>(
				map.values());
		return list;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "hello";
	}

	private static class CommentJsonObject {
		private Comment comment;
		private List<CommentReply> commentReplies = new ArrayList<CommentReply>();

		public Comment getComment() {
			return comment;
		}

		public void setComment(Comment comment) {
			this.comment = comment;
		}

		public List<CommentReply> getCommentReplies() {
			return commentReplies;
		}

		public void setCommentReplies(List<CommentReply> commentReplies) {
			this.commentReplies = commentReplies;
		}

		public void insertCommentReply(CommentReply commentReply) {
			commentReplies.add(commentReply);
		}
	}
}
