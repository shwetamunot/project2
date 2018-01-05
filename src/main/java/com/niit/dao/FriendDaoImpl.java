package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao{
@Autowired
private SessionFactory sessionfactory;
	public List<User> suggestedUserList(String username) {
		
		Session session=sessionfactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("(select * from users where username in"
			    +"(select username from users where username!=?"
				+"minus"
				+"(select fromId from friendss where ToId=? "
				+"union select ToId from friendss where fromId=? )))");
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> suggestedUser=query.list();
		return suggestedUser;
	}
	public void addFriendRequest(Friend friend) {
		Session session=sessionfactory.getCurrentSession();
		session.save(friend);
	}
	public List<Friend> pendingRequests(String username) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Friend where ToId=? and status=?");
		query.setString(0, username);
		query.setCharacter(1, 'P');
		List<Friend> pendingRequest=query.list();
		return pendingRequest;
	}
	public void updatePendingRequests(Friend friend) {
Session session=sessionfactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend);
		else
			session.delete(friend);
	}
	public List<User> listOfFriends(String username) {
		Session session=sessionfactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("(select * from users where username in"
		+ "(select ToId from friendss where fromId=? and status='A'"
		+ " union "		
		+ " select fromId from friendss where ToId=? and status='A'))");
		query.setString(0,username);
		query.setString(1,username);
		query.addEntity(User.class);
		List<User> friend=query.list();
		return friend;
		
	}
	


}
