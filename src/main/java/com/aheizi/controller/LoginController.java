package com.aheizi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aheizi.domain.Blog;
import com.aheizi.service.BlogService;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

@Controller
public class LoginController {
	@Resource
	private BlogService blogService;

	@RequestMapping("/login")
	public String toIndex() {
		return "login";
	}

	@RequestMapping(value = "/handi", method = RequestMethod.GET)
	@ResponseBody
	public List<Blog> test2() {
		Blog blog = new Blog();
		blog.setType("Java");
		blog.setBlogTitle("基本入门");
		blog.setBlogContent("Hello World");
		Blog blog2 = new Blog();
		blog2.setType("MacOs");
		blog2.setBlogTitle("mac下修改root密码");
		blog2.setBlogContent("如何修改");
		Blog blog3 = new Blog();
		blog3.setType("react");
		blog3.setBlogTitle("react+redux简单的demo");
		blog3.setBlogContent("first bll");
		blogService.saveBlog(blog);
		blogService.saveBlog(blog2);
		blogService.saveBlog(blog3);
		return blogService.getAllBlog();
	}

	@RequestMapping(value = "/blog/sort/{type}", method = RequestMethod.GET)
	@ResponseBody
	public List getBlogs(@PathVariable String type) {
		return blogService.getBlogByType(type);
	}

	@RequestMapping(value = "/blogs/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteBlog(@RequestBody String id) {
		return "hello";
	}

	@RequestMapping(value = "/blogs/add")
	@ResponseBody
	public String addBlog(@RequestBody String bodyContent) {
		JSONObject jsonObject = JSONObject.parseObject(bodyContent);
		String blogContent = jsonObject.getString("blogContent");
		String blogTitle = jsonObject.getString("blogTitle");
		String type = jsonObject.getString("type");
		String description = jsonObject.getString("description");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Blog blog = new Blog();
		blog.setBlogContent(blogContent);
		blog.setBlogTitle(blogTitle);
		blog.setCreationtime(df.format(new Date()));
		blog.setType(type);
		blog.setDescription(description);
		blogService.saveBlog(blog);
		return "添加成功";
	}

	@RequestMapping(value = "/blogs/{id}/markdown", method = RequestMethod.GET)
	@ResponseBody
	public Blog getBlogContent(@PathVariable String id) {
		return blogService.getBlogById(id);
	}

	@RequestMapping(value = "/testd", method = RequestMethod.GET)
	@ResponseBody
	public List<String> test() {
		return blogService.getAllBlogType();
	}

	// @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	// @ResponseBody
	// public Blog getBlogById(@PathVariable String id) {
	// return blogService.getBlogById(id);
	// }
}
