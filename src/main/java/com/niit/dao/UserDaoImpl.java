package com.niit.dao;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
	@Autowired
private SessionFactory sessionfactory;
	public void registerUser(User user) {
		
		Session session=sessionfactory.getCurrentSession();
		System.out.println("savess");
		session.save(user);
	}
	
	public boolean isEmailValid(String email) {
		System.out.println("emailvalid"+email);
	    Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from User where email='"+ email +"'");
System.out.println("emails");	
	User user=(User)query.uniqueResult();
		if(user==null)	
			return true;
		else
		   return false;
	}
	
	public boolean isUsernameValid(String username) {
Session session=sessionfactory.getCurrentSession();
System.out.println("ghjk");
User user=(User)session.get(User.class,username);
System.out.println("useresss");

if(user!=null)
{
	System.out.println("false");
	return false;
}
	else
	{
		System.out.println("true");
		return true;
	}
	}
}
