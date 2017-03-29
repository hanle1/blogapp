package com.aheizi.dao;

import java.util.List;

import com.aheizi.domain.Blog;

public interface BlogMapper {
	List<Blog> getAllBlog();

	int insertBlog(Blog blog);

	List<Blog> selectByType(String type);

	List<String> getAllType();

	Blog getBlogById(String id);
}
