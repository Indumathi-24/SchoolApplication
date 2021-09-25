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
import com.school.dto.StudentLogin;
import com.school.entity.StudentEntity;
import com.school.entity.StudentLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.StudentRepository;
import com.school.util.StudentLoginMapper;
import com.school.repository.StudentLoginRepository;
@Repository
@Transactional
public class StudentLoginRepositoryImpl implements StudentLoginRepository{
	static Logger logger = Logger.getLogger("StudentLoginRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long createLogin(Long rollNo,StudentLogin login) throws DatabaseException {
		logger.debug("In Adding Student Login Details");
		Session session=null;
		Long id=null;
		try {
			logger.info("Adding Student Login Details..");
			session=sessionFactory.getCurrentSession();
			StudentLoginEntity teachLogin=StudentLoginMapper.mapStudentLogin(login, rollNo);
			id=(Long) session.save(teachLogin);
			if(id!=null)
			{
				logger.info("Adding Student Login Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.info("Error Occured while Saving Student Login Details");
			throw new DatabaseException(e.getMessage());
		}
		
		return id;

	}
	@Override
	public StudentLoginEntity getLoginDetails(Long rollNo) throws DatabaseException  {
		logger.debug("In Retrieving Student Login Details");
		StudentLoginEntity student=new StudentLoginEntity();
		Session session=null;
		try {
			logger.info("Retrieving Student Login Details..");
		    session=sessionFactory.getCurrentSession();
			Query<StudentLoginEntity> query=session.createQuery("from StudentLoginEntity t where t.student.rollNo=:rNo");
			query.setParameter("rNo", rollNo);
			student = query.getSingleResult();
			if(student!=null)
			{
				logger.info("Retrieving Student Login Details is Completed");
			}
		}
		catch(HibernateException e)
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
			session=sessionFactory.getCurrentSession();
			Query<StudentLoginEntity> query=session.createQuery("Update StudentLoginEntity t set t.password=:password where t.student.rollNo=:rNo");
			query.setParameter("rNo",rollNo);
			query.setParameter("password", login.getPassword());
			result=query.executeUpdate();
			if(result==1)
			{
				logger.info("Updating Student Login Details is Completed");
			}
		}
			catch(HibernateException e)
			{
				throw new DatabaseException(e.getMessage());
			}
			
			return result;
			
	}
}
