package com.niit.dao;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Notification;


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


	public void updateBlogPost(BlogPost blogPost,String rejectionReason) 
	{
		Session session=sessionfactory.getCurrentSession();
		Notification notify=new Notification();
		notify.setBlogTitle(blogPost.getBlogTitle());
		notify.setUsername(blogPost.getPostedBy().getUsername());//author who posted blogs
		
		if(blogPost.isApproved()){//true admin approve blogPost
			session.update(blogPost);//update blogPost set approved=1 where id=?
			notify.setApprovalStatus("Approved");
			session.save(notify);//insert into notification values
		}
		else
		{
			notify.setRejectionReason(rejectionReason);
			notify.setApprovalStatus("Rejected");
			session.save(notify);
			session.delete(blogPost);
	    }
	}

	public void addComment(BlogComment blogComment) {
		Session session=sessionfactory.getCurrentSession();
		session.save(blogComment);
		
	}
}
