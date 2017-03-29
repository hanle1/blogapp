package com.aheizi.service;

import java.util.List;

import com.aheizi.domain.Blog;

public interface BlogService {
	public List<Blog> getAllBlog();

	public void saveBlog(Blog blog);

	public List<Blog> getBlogByType(String type);

	public List<String> getAllBlogType();
	
	public Blog getBlogById(String id);
}
