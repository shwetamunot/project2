package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogPost;


@Transactional
@Repository
public class BlogPostDaoImpl implements BlogPostDao{

	@Autowired
		private SessionFactory sessionfactory;

	public void saveBlogPost(BlogPost blogPost) {
	Session session=sessionfactory.getCurrentSession();
		session.save(blogPost);
	}

	public List<BlogPost> getBlogs(int approved) {
	Session session=sessionfactory.getCurrentSession();
	Query query=session.createQuery("from BlogPost where approved="+ approved);
		return query.list();
	}

	public BlogPost getBlogById(int id) {
	Session session=sessionfactory.getCurrentSession();
BlogPost blogPost=(BlogPost) session.get(BlogPost.class,id);	
		return blogPost;
	}

}
