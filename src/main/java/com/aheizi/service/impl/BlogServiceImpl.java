package com.aheizi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aheizi.dao.BlogMapper;
import com.aheizi.domain.Blog;
import com.aheizi.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
	@Resource
	private BlogMapper blogMapper;

	@Override
	public List<Blog> getAllBlog() {
		return blogMapper.getAllBlog();
	}

	@Override
	public void saveBlog(Blog blog) {
		int id = blogMapper.insertBlog(blog);
		System.out.println(id);
	}

	@Override
	public List<Blog> getBlogByType(String type) {
		return blogMapper.selectByType(type);
	}

	@Override
	public List<String> getAllBlogType() {
		return blogMapper.getAllType();
	}

	@Override
	public Blog getBlogById(String id) {
		return blogMapper.getBlogById(id);
	}

}
