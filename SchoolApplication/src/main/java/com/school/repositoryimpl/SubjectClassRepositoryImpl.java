package com.school.repositoryimpl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.dto.SubjectClass;
import com.school.entity.SubjectClassEntity;
import com.school.exception.DatabaseException;
import com.school.repository.SubjectClassRepository;
import com.school.util.SubjectClassMapper;

@Repository
@Transactional
public class SubjectClassRepositoryImpl implements SubjectClassRepository{
	
	static Logger logger = Logger.getLogger("SubjectClassRepositoryImpl.class");
	@Autowired
	SessionFactory sessionFactory;
     
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
}
