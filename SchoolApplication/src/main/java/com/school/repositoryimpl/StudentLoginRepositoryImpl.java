package com.school.repositoryimpl;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.school.dto.StudentLogin;
import com.school.entity.StudentLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.StudentLoginNotFoundException;
import com.school.util.StudentLoginMapper;
import com.school.repository.StudentLoginRepository;
@Repository
@Transactional
public class StudentLoginRepositoryImpl implements StudentLoginRepository{
	static Logger logger = Logger.getLogger("StudentLoginRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	public void checkStudentLogin(Long id) throws StudentLoginNotFoundException  {
		logger.debug("In Checking Student Login Id...");
		Session session = sessionFactory.getCurrentSession();
		Query<StudentLoginEntity> query = session.createQuery("FROM StudentLoginEntity WHERE autoId=:studentId");
		query.setParameter("studentId", id);
		StudentLoginEntity studentLoginDetail = query.uniqueResultOptional().orElse(null);
		if (studentLoginDetail==null) {
			throw new StudentLoginNotFoundException("Student Login Not Found with"+" "+id+"!");
		}	
	}

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
	
	@Override
	public Long getParticularLoginDetails(Long autoId) throws DatabaseException, NotFoundException {
		logger.debug("In Retrieving Student Login Id...");
		Session session=null;
		Long headMasterId =0l;
		try {
			logger.info("Retrieving Student Login Id...");
			checkStudentLogin(autoId);
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("Select student.rollNo from StudentLoginEntity  where autoId=:staffId");
			query.setParameter("staffId", autoId);
			headMasterId= (Long) query.uniqueResult();
			if(headMasterId!=null)
			{
				logger.info("Retrieving HeadMaster Login Id is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Retrieving headMaster Login Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterId;
	}
}
