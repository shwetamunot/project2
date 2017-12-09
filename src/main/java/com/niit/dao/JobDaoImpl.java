package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;
@Repository
@Transactional
public class JobDaoImpl implements JobDao {
@Autowired
	private SessionFactory sessionfactory;
	public void saveJob(Job job) {
		Session session=sessionfactory.getCurrentSession();
		session.save(job);
		
	}
	public List<Job> getAllJobs() {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Job");
				return query.list();
	}
	public Job getJob(int id) {
		System.out.println("shows");
		Session session=sessionfactory.getCurrentSession();
		System.out.println("detail");
		Job job=session.get(Job.class,id);
		System.out.println("detls");
		return job;
	}

}
