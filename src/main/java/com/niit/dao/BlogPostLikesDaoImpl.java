package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;
import com.niit.model.User;


@Transactional
@Repository
public class BlogPostLikesDaoImpl implements BlogPostLikesDao{

@Autowired
	private SessionFactory sessionfactory;
	public BlogPostLikes userLikes(BlogPost blogPost, User user) {
		Session session=sessionfactory.getCurrentSession();
		//select * from blogpostlikess where..
		Query query=session.createQuery("from BlogPostLikes where blogPost.id=? and user.username=?");
		System.out.println(blogPost.getId());
		System.out.println(user.getUsername());
	
		query.setInteger(0,blogPost.getId());
		query.setString(1, user.getUsername());
		
		//blogPostLikes=null/1 object
		BlogPostLikes blogPostLikes=(BlogPostLikes) query.uniqueResult();
		System.out.println(blogPostLikes);
		return blogPostLikes;
	}
	
	public BlogPost updateLikes(BlogPost blogPost, User user) {
	Session session=sessionfactory.getCurrentSession();
	BlogPostLikes blogPostLikes=userLikes(blogPost,user);
	if(blogPostLikes==null){
		BlogPostLikes insertLikes=new BlogPostLikes();
		insertLikes.setBlogPost(blogPost);//fk
		insertLikes.setUser(user);//fk
		session.save(insertLikes);//insert into ..
		blogPost.setLikes(blogPost.getLikes()+1);//increment
		session.update(blogPost);//update where id=?
	}else
	{
		session.delete(blogPostLikes);//delete from ..
		blogPost.setLikes(blogPost.getLikes()-1);//decrement
		session.merge(blogPost);//update where id=? 
	}
		
		return blogPost;
	}
	
	}


