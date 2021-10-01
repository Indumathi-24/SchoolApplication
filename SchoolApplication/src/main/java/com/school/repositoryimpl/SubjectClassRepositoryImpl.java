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

import com.school.dto.SubjectClass;
import com.school.entity.StudentEntity;
import com.school.entity.SubjectClassEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.exception.SubjectAssignIdNotFoundException;
import com.school.repository.SubjectClassRepository;
import com.school.util.SubjectClassMapper;

@Repository
@Transactional
public class SubjectClassRepositoryImpl implements SubjectClassRepository{
	
	static Logger logger = Logger.getLogger("SubjectClassRepositoryImpl.class");
	@Autowired
	SessionFactory sessionFactory;
     
	
	 public void checkSubjectAssignId(Long id) throws SubjectAssignIdNotFoundException  {
		  logger.debug("In Checking Student Roll No...");
		  SubjectClassEntity studentDetail = new SubjectClassEntity();
	      Session session=sessionFactory.getCurrentSession();
		  Query<SubjectClassEntity> query = session.createQuery("from SubjectClassEntity where id=:id");
		  query.setParameter("id",id);
		  studentDetail =  query.uniqueResultOptional().orElse(null);
		  if(studentDetail==null)
		  {
			  throw new SubjectAssignIdNotFoundException("Subject Assign Id Not Found,Enter Valid Id");
		  }
	 }
	@Override
	public Long assignSubjectClass(SubjectClass subjectClass) throws DatabaseException
	{
		logger.debug("In Assigning Subject to Class...");
		Session session = null;
		Long assignId =0l;
		try {
			logger.info("Assigning Subject to Class...");
			session =sessionFactory.getCurrentSession();
			SubjectClassEntity subjectClassEntity = SubjectClassMapper.mapSubjectClass(subjectClass);
			assignId = (Long) session.save(subjectClassEntity);
			if(assignId!=null)
			{
				logger.debug("Assigning Subject to Class is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.debug("Error Occurred while assigning Subject to class");
			throw new DatabaseException(e.getMessage());
		}
		return assignId;
	}
	
	@Override
	public List<String> viewSubjectClass(Long roomNo) throws DatabaseException
	{
		logger.debug("In Retrieving SubjectClass...");
		Session session = null;
		List<String> subjectClassList = new ArrayList<>();
		try {
			logger.info("Retrieving SubjectClass...");
			session =sessionFactory.getCurrentSession();
			Query query = session.createQuery("Select subjectEntity.code from SubjectClassEntity where classEntity.roomNo=:roomNo");
			query.setParameter("roomNo", roomNo);
			subjectClassList = query.list();
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return subjectClassList;
	}
	
	@Override
	public Long getSubjectClassAssignId(String code,Long roomNo) throws DatabaseException
	{
		logger.debug("In Retrieving SubjectClass...");
		Session session = null;
		Long subjectClassAssignId = null;
		try {
			logger.info("Retrieving SubjectClass...");
			session =sessionFactory.getCurrentSession();
			Query query = session.createQuery("Select id from SubjectClassEntity where subjectEntity.code=:code and classEntity.roomNo=:roomNo");
			query.setParameter("code", code);
			query.setParameter("roomNo", roomNo);
			subjectClassAssignId = (Long) query.uniqueResult();
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return subjectClassAssignId;
	}
	
	@Override
	public Long getRoomNoForAssignId(Long id) throws DatabaseException, NotFoundException
	{
		logger.debug("In Retrieving SubjectClass...");
		Session session = null;
		Long roomNo = null;
		try {
			logger.info("Retrieving SubjectClass...");
			checkSubjectAssignId(id);
			session =sessionFactory.getCurrentSession();
			Query query = session.createQuery("Select classEntity.roomNo from SubjectClassEntity where id=:subjectAssignId");
			query.setParameter("subjectAssignId", id);
			roomNo =  (Long) query.uniqueResult();
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return roomNo;
	}
	
	@Override
	public String getSubjectCode(Long roomNo,Long id) throws NotFoundException, DatabaseException
	{
		logger.debug("In Retrieving SubjectCode...");
		Session session = null;
		String subjectCode = null;
		try {
			logger.info("Retrieving SubjectCode...");
			checkSubjectAssignId(id);
			session =sessionFactory.getCurrentSession();
			Query query = session.createQuery("Select subjectEntity.code from SubjectClassEntity where id=:id and classEntity.roomNo=:roomNo");
			query.setParameter("id", id);
			query.setParameter("roomNo", roomNo);
			subjectCode = (String) query.uniqueResult();
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return subjectCode;
	}
}
