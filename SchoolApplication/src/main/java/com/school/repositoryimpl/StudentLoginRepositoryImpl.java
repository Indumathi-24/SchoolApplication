package com.school.repositoryimpl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.controller.Response;
import com.school.entity.Student;
import com.school.entity.StudentLogin;
import com.school.exception.DatabaseException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.StudentRepository;
import com.school.repository.StudentLoginRepository;
@Repository
@Transactional
public class StudentLoginRepositoryImpl implements StudentLoginRepository{
	static Logger logger = Logger.getLogger("StudentLoginRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public Long createLogin(Long rollNo,StudentLogin login) throws DatabaseException {
		logger.debug("In Adding Student Login Details");
		Session session=null;
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Long id=null;
		try {
			logger.info("Adding Student Login Details..");
			boolean status=studentRepository.checkStudentRollNo(rollNo);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				Student student=new Student();
				StudentLogin teachLogin=new StudentLogin();
				student.setRollNo(rollNo);
				teachLogin.setStudent(student);
				teachLogin.setPassword(login.getPassword());
				id=(Long) session.save(teachLogin);
			}
			if(id!=null)
			{
				logger.info("Adding Student Login Details is Completed");
			}
		}
		catch(HibernateException | StudentNotFoundException e)
		{
			logger.info("Error Occured while Saving Student Login Details");
			throw new DatabaseException(e.getMessage());
		}
		
		return id;

	}
	@Override
	public StudentLogin getLoginDetails(Long rollNo) throws DatabaseException  {
		logger.debug("In Retrieving Student Login Details");
		StudentLogin student=new StudentLogin();
		Session session=null;
		try {
			logger.info("Retrieving Student Login Details..");
			boolean status=studentRepository.checkStudentRollNo(rollNo);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				Query query=session.createQuery("from StudentLogin t where t.student.rollNo=:rNo");
				query.setParameter("rNo", rollNo);
				student=(StudentLogin) query.getSingleResult();
			}
			if(student!=null)
			{
				logger.info("Retrieving Student Login Details is Completed");
			}
		}
		catch(HibernateException | StudentNotFoundException e)
		{
		    throw new DatabaseException(e.getMessage());
		}
			return student;
	}
	@Override
	public Integer updateLoginDetails(Long rollNo, StudentLogin login)throws DatabaseException{
		logger.debug("In Updating Student Login Details");
		Session session=null;
		int result=0;
		try {
			logger.info("Updating Student Login Details..");
			boolean status=studentRepository.checkStudentRollNo(rollNo);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				Query query=session.createQuery("Update StudentLogin t set t.password=:password where t.student.rollNo=:rNo");
				query.setParameter("rNo",rollNo);
				query.setParameter("password", login.getPassword());
				result=query.executeUpdate();
			}
			if(result==1)
			{
				logger.info("Updating Student Login Details is Completed");
			}
		}
			catch(HibernateException | StudentNotFoundException e)
			{
				throw new DatabaseException(e.getMessage());
			}
			
			return result;
			
	}
}
