package com.niit.configuration;

import java.util.Properties;




import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;
import com.niit.model.Job;
import com.niit.model.Notification;
import com.niit.model.ProfilePicture;
import com.niit.model.User;


@Configuration
@EnableTransactionManagement
public class DBConfiguration {

	@Bean
	
public DataSource getDataSource()
{
	 System.out.println("Hibernate initiated..");
	 DriverManagerDataSource dm=new DriverManagerDataSource();
	 dm.setDriverClassName("oracle.jdbc.OracleDriver");
	 dm.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	 dm.setUsername("mr");
	 dm.setPassword("mr");
  System.out.println("Connection Established");
 return dm;
 }

	
 @Bean
 public SessionFactory sessionFactory()
 {
	 LocalSessionFactoryBuilder lsfb=new LocalSessionFactoryBuilder(getDataSource());
	 Properties p=new Properties();
	 System.out.println("start");
	 p.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
     p.setProperty("hibernate.hbm2ddl.auto","update");
	 p.setProperty("hibernate.show_sql","true");
	
lsfb.addProperties(p);


Class classes[]=new Class[]{User.class,Job.class,BlogPost.class,Notification.class,BlogPostLikes.class,BlogComment.class,ProfilePicture.class};
return	lsfb.addAnnotatedClasses(classes).buildSessionFactory();	
 
 }

 @Bean
public HibernateTransactionManager getTransaction(SessionFactory sessionfactory) 
{
	 HibernateTransactionManager tm=new HibernateTransactionManager(sessionfactory);
	
	 return tm;			 
}

}
