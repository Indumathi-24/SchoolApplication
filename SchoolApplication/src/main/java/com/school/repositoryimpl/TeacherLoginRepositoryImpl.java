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

import com.school.dto.TeacherLogin;
import com.school.entity.StudentLoginEntity;
import com.school.entity.TeacherEntity;
import com.school.entity.TeacherLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.StudentLoginNotFoundException;
import com.school.exception.TeacherLoginNotFoundException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherLoginRepository;
import com.school.repository.TeacherRepository;
import com.school.util.TeacherLoginMapper;

@Repository
@Transactional
public class TeacherLoginRepositoryImpl implements TeacherLoginRepository{

	static Logger logger = Logger.getLogger("TeacherLoginRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Long createLogin(Long id,TeacherLogin login) throws DatabaseException {
		logger.debug("In Adding Teacher Login details...");
		Session session=null;
		Long loginId = null;
		try {
			logger.info("Adding Teacher Login details...");
			session=sessionFactory.getCurrentSession();
			TeacherLoginEntity teachLogin = TeacherLoginMapper.mapTeacherLogin(id,login);
			loginId = (Long) session.save(teachLogin);
			if(loginId!=null)
			{
				logger.info("Adding Teacher Login details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occurred while Saving Login Details");
			throw new DatabaseException(e.getMessage());
		}
			
		return loginId;

	}
	@Override
	public TeacherLoginEntity getLoginDetails(Long id) throws DatabaseException {
		logger.debug("In Retrieving Teacher Login details...");
		TeacherLoginEntity teacher=new TeacherLoginEntity();
		Session session=null;
		try {
	         logger.info("Retrieving Teacher Login details...");
		     session=sessionFactory.getCurrentSession();
			 Query<TeacherLoginEntity> query=session.createQuery("from TeacherLoginEntity t where t.userId.id=:staffId");
			 query.setParameter("staffId", id);
			 teacher = query.getSingleResult();	
			 if(teacher!=null)
			 {
				 logger.info("Retrieving Teacher Login details is Completed");
			 }
		  }
		 catch(HibernateException e)
		{
			 logger.error("Error Occurred while Retrieving Login Details");
			 throw new DatabaseException(e.getMessage());
		}
			return teacher;
	}
	@Override
	public Integer updateLoginDetails(Long id, TeacherLogin login) throws DatabaseException  {
		logger.debug("In Updating Teacher Login details...");
		Session session=null;
		int result;
		try {
			    logger.info("Updating Teacher Login details...");
				session=sessionFactory.getCurrentSession();
				Query query=session.createQuery("Update TeacherLoginEntity t set t.password=:password where t.userId.id=:staffId");
				query.setParameter("staffId", id);
				query.setParameter("password", login.getPassword());
				result=query.executeUpdate();
				if(result==1)
				{
					logger.info("Updating Teacher Login details is Completed");
				}
		    }
			catch(HibernateException e)
			{
				logger.error("Error Occurred while Updating Login Details");
				throw new DatabaseException(e.getMessage());
			}
			return result;
			
	}
	
	@Override
	public Long getParticularLoginDetails(Long autoId) throws DatabaseException, NotFoundException {
		logger.debug("In Retrieving Teacher Login Id...");
		Session session=null;
		Long teacherId =0l;
		try {
			logger.info("Retrieving Teacher Login Id...");
			checkTeacherLogin(autoId);
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("Select userId.id from TeacherLoginEntity where autoId=:staffId");
			query.setParameter("staffId", autoId);
			teacherId= (Long) query.uniqueResult();
			if(teacherId!=null)
			{
				logger.info("Retrieving HeadMaster Login Id is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Retrieving headMaster Login Id");
			throw new DatabaseException(e.getMessage());
		}
		return teacherId;
	}
	public void checkTeacherLogin(Long autoId) throws NotFoundException {
		logger.debug("In Checking Student Login Id...");
		Session session = sessionFactory.getCurrentSession();
		Query<TeacherLoginEntity> query = session.createQuery("FROM TeacherLoginEntity WHERE autoId=:teacherId");
		query.setParameter("teacherId", autoId);
		TeacherLoginEntity teacherLoginDetail = query.uniqueResultOptional().orElse(null);
		if (teacherLoginDetail==null) {
			throw new TeacherLoginNotFoundException("Teacher Login Not Found with"+" "+autoId+"!");
		}	
		
	}
	
}
