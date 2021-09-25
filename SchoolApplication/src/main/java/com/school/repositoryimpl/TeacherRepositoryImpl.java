package com.school.repositoryimpl;

import java.util.ArrayList;


import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.school.dto.Teacher;
import com.school.entity.TeacherEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherRepository;
import com.school.util.TeacherMapper;

@Repository
@Transactional
public class TeacherRepositoryImpl implements TeacherRepository{
	static Logger logger = Logger.getLogger("TeacherRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Long addTeacherDetails(Teacher teacherDetails) throws DatabaseException {
		logger.debug("In Adding Teacher Details");
		Session session=null;
		Long teacherId = null;
		try
		{
			logger.info("In Adding Teacher Details");
			session=sessionFactory.getCurrentSession();
			TeacherEntity teacherEntity = TeacherMapper.mapTeacher(teacherDetails);
			teacherId = (Long) session.save(teacherEntity);
			if(teacherId!=null)
			{
				logger.info("Adding Teacher Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Saving Teacher details");
			throw new DatabaseException(e.getMessage()); 
		}	
		return teacherId;
	}
	@Override
	public List<TeacherEntity> getAllTeacherDetails() throws DatabaseException {
		logger.debug("In Retrieving All Teacher Details");
		Session session=null;
		List<TeacherEntity> teacherDetailsList=new ArrayList<>();
		try
		{
			logger.info("Retrieving All Teacher Details");
			session=sessionFactory.getCurrentSession();
			Query<TeacherEntity> query=session.createQuery("FROM TeacherEntity t");
			teacherDetailsList=query.list();
			if(!teacherDetailsList.isEmpty())
			{
				logger.info("Retrieving All Teacher Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Retrieving All Teacher details");
			throw new DatabaseException(e.getMessage());
		}
		return teacherDetailsList;
	}
	public void checkTeacher(Long id) throws TeacherNotFoundException {
		logger.debug("In Checking Teacher Id...");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TeacherEntity WHERE id=:teacherId");
		query.setParameter("teacherId", id);
		TeacherEntity teacher = (TeacherEntity) query.uniqueResultOptional().orElse(null);
		if (teacher==null) {
			throw new TeacherNotFoundException("Teacher Not Found with"+" "+id+"!");
		}
	}
	@Override
	public TeacherEntity updateTeacherDetails(Long id, Teacher teacherDetails) throws DatabaseException, NotFoundException {
		logger.debug("In Updating Teacher Details");
		TeacherEntity teacher=null;
		Session session=null;
		try
		{
			logger.info("Updating Teacher Details");
			checkTeacher(id);
			session=sessionFactory.getCurrentSession();
			TeacherEntity teacherEntity = TeacherMapper.mapTeacher(teacherDetails);
			session.find(TeacherEntity.class, id);
			TeacherEntity newTeacherDetails=session.load(TeacherEntity.class, id);
			newTeacherDetails.setName(teacherEntity.getName());
			newTeacherDetails.setDateOfBirth(teacherEntity.getDateOfBirth());
			newTeacherDetails.setGender(teacherEntity.getGender());
			newTeacherDetails.setQualification(teacherEntity.getQualification());
			newTeacherDetails.setEmail(teacherEntity.getEmail());
			newTeacherDetails.setContactNo(teacherEntity.getContactNo());
			newTeacherDetails.setAddress(teacherEntity.getAddress());
			teacher = (TeacherEntity) session.merge(newTeacherDetails);
			if(teacher!=null)
			{
				logger.info("Updating Teacher Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Updating Teacher details");
			throw new DatabaseException(e.getMessage());
		}
		return teacher;
	}
	@Override
	public TeacherEntity deleteTeacherDetails(Long id) throws DatabaseException, NotFoundException  {
		logger.debug("In Deleting Teacher Details");
		TeacherEntity teacher = null;
		Session session=null;
		try
		{
			logger.info("Deleting Teacher Details");
			checkTeacher(id);
			session=sessionFactory.getCurrentSession();
			session.find(TeacherEntity.class, id);
			TeacherEntity teacherDetails=session.load(TeacherEntity.class, id);
			session.delete(teacherDetails);
			teacher = session.load(TeacherEntity.class, id);
			if(teacher == null)
			{
				logger.info("Deleting Teacher Details is Completed");
			}
		    
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Deleting Teacher details");
			throw new DatabaseException(e.getMessage());
		}		
		return teacher;
	}
	@Override
	public TeacherEntity getParticularTeacherDetails(Long id) throws DatabaseException, NotFoundException  {
		logger.debug("In Retrieving Teacher Details");
		Session session=null;
		TeacherEntity teacherDetails=new TeacherEntity();
		try
		{
			logger.info("Retrieving Teacher Details");
			checkTeacher(id);
			session=sessionFactory.getCurrentSession();
			Query<TeacherEntity> query=session.createQuery("FROM TeacherEntity WHERE id=:teacherId");
			query.setParameter("teacherId", id);
			teacherDetails= query.getSingleResult();
			if(teacherDetails==null)
			{
				logger.info("Retrieving Teacher Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.debug("Error Occured while Retrieving Teacher details");
			throw new DatabaseException(e.getMessage());
		}	
		return teacherDetails;
	}

}
