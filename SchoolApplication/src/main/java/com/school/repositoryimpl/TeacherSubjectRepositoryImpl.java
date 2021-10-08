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
import com.school.dto.TeacherSubject;
import com.school.entity.SubjectClassEntity;
import com.school.entity.TeacherSubjectEntity;
import com.school.exception.DatabaseException;
import com.school.exception.TeacherAlreadyExistException;
import com.school.repository.TeacherSubjectRepository;
import com.school.util.TeacherSubjectMapper;

@Repository
@Transactional
public class TeacherSubjectRepositoryImpl implements TeacherSubjectRepository{
	
	static Logger logger = Logger.getLogger("TeacherSubjectRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long assignTeacherSubject(TeacherSubject teacherSubjectDetails) throws DatabaseException, TeacherAlreadyExistException  {
		logger.debug("In Adding Teacher Subject Details");
		Session session=null;
		Long id;
		try
		{
			logger.info("Adding Teacher Subject Details");
			session=sessionFactory.getCurrentSession();
			TeacherSubjectEntity teacherSubjectAssignDetails = TeacherSubjectMapper.mapTeacherSubject(teacherSubjectDetails);
			id = (Long) session.save(teacherSubjectAssignDetails);
			if(id!=null)
			{
				logger.info("Adding Teacher Subject Details is Completed");
			}
			else
			{
				throw new TeacherAlreadyExistException("Teacher Already Assigned to this Subject");
			}
			
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Adding TeacherSubject Details");
			throw new DatabaseException(e.getMessage());
		}
				
		return id;
	}
	@Override
	public Integer updateTeacherSubjectAssign(Long teacherId, String subjectCode,
		TeacherSubject teacherSubjectDetails) throws DatabaseException {
		
		logger.debug("In Updating Teacher Subject Details");
		Session session=null;
		Integer countOfUpdation;
		try
		{
			logger.info("Updating Teacher Subject Details");
			session=sessionFactory.getCurrentSession();
			Query updateByTeacherId=session.createQuery("UPDATE TeacherSubjectEntity SET subjectCode=:code WHERE teacherId=:staffId");
			updateByTeacherId.setParameter("code", subjectCode);
			updateByTeacherId.setParameter("staffId", teacherId);
			countOfUpdation=updateByTeacherId.executeUpdate();
			if(countOfUpdation>0)
			{
				logger.info("Updating Teacher Subject Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Updating TeacherSubject Details");
			throw new DatabaseException(e.getMessage());
		}
		return countOfUpdation;
	}
	@Override
	public Integer deleteTeacherSubjectAssign(Long teacherId, String subjectCode)
			throws DatabaseException {
		logger.debug("In Deleting Teacher Subject Details");
		Session session=null;
		Integer count;
		try
		{
			logger.info("Deleting Teacher Subject Details");
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("DELETE FROM TeacherSubjectEntity WHERE teacherId=:staffId AND subjectCode=:code");
			query.setParameter("staffId",teacherId );
			query.setParameter("code",subjectCode);
			count=query.executeUpdate();
			if(count>0)
			{
				logger.debug("Deleting Teacher Subject Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Deleting TeacherSubject Details");
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}
	
	@Override
	public List<Long> getSubjectClassId(Long teacherId) throws DatabaseException
	{
		logger.debug("In Retrieving Subject Class Id...");
		Session session=null;
		List<Long> subjectClassAssignIdList = null;
		try
		{
			logger.info("Retrieving Subject Class Id...");
			session=sessionFactory.getCurrentSession();
			Query query = session.createQuery("Select subjectClassEntity.id from TeacherSubjectEntity where teacher.id=:teacherId");
			query.setParameter("teacherId", teacherId);
			subjectClassAssignIdList = query.list();
			if(!subjectClassAssignIdList.isEmpty())
			{
				logger.info("Retrieving Subject Class Id is Completed");
			}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return subjectClassAssignIdList;
			
	}

}
