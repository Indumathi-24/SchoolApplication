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
import com.school.entity.Class;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.repository.ClassRepository;

@Repository
@Transactional
public class ClassRepositoryImpl implements ClassRepository {
	static Logger logger = Logger.getLogger("ClassRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;

	public void checkClassRoomNo(Long roomNo) throws ClassNotFoundException  {
		  logger.debug("In Check Class Room No Details Method");
		  Class classDetail = new Class();
	      Session session =sessionFactory.getCurrentSession();
	      Query query=session.createQuery("from Class where roomNo=:rNo");
	      query.setParameter("rNo",roomNo);
	      classDetail= (Class) query.uniqueResultOptional().orElse(null);
	      if(classDetail==null)
		  {
		     throw new ClassNotFoundException("Class not Found,Enter the Valid Room No!");
		  }
	  }
	
	public Long addClass(Class classDetail) throws DatabaseException {
		logger.debug("In Add Class Details Method");
		Session session=null;
		Long roomNo=null;
		try
		{
			logger.info("In Add Class Details Method");
			session = sessionFactory.getCurrentSession();
			roomNo = (Long) session.save(classDetail);
			if(roomNo!=null)
			{
				logger.info(" Adding Class Details is Completed");
			}
			
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Saving Class details");
			throw new DatabaseException(e.getMessage());
		}
		return roomNo;
	}
	
    public List<Class> getAllClass() throws DatabaseException 
    {   
    	logger.debug("In Get All Class Details Method");
    	Session session=null;
		List<Class> classList =new ArrayList<Class>();
		try
		{
			logger.info("Retriving Class Details Method");
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Class c");
			classList=query.list();
		    if(!classList.isEmpty())
		    {
		    	logger.info("Retrieving Class Details is Completed");
		    }
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Retrieving Class Details");
			throw new DatabaseException(e.getMessage());
		}
		return classList;
    }
    
    public Class getParticularClass(Long roomNo) throws DatabaseException {
    	Class classEntity = new Class();
    	Session session=null;
    	logger.debug("In Get Class Details Method");
		try
		{
			logger.info("Retriving Class Details Method");
			checkClassRoomNo(roomNo);
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Class  where roomNo=:rNo");
			query.setParameter("rNo",roomNo);
			classEntity= (Class) query.getSingleResult();
			if(classEntity!=null)
			{
				logger.info("Retrieving Particular Class Details is Completed");
			}
		}
		catch(HibernateException | ClassNotFoundException e)
		{
			logger.error("Error Occured while Retrieving Class Details");
			throw new DatabaseException(e.getMessage());
		}
		return classEntity;
    }

    public Class updateClass(Long roomNo,Class classEntity) throws DatabaseException{
    	
    	Session session=null;
    	Class updatedClass = null;
    	logger.debug("In Updating Class Details Method");
		try {
			logger.info("Updating Class Details Method");
			checkClassRoomNo(roomNo);
			session=sessionFactory.getCurrentSession();
			session.find(Class.class,roomNo);
			Class classDetails=session.load(Class.class,roomNo);
			classDetails.setSection(classEntity.getSection());
			classDetails.setStandard(classEntity.getStandard());
			updatedClass = (Class) session.merge(classDetails);
			if(updatedClass!=null)
			{
				logger.info("Updating Class Details is Completed");
			}
		} catch (HibernateException | ClassNotFoundException e) {
			logger.error("Error Occured while Updating Class Details");
			throw new DatabaseException(e.getMessage());
		}
		return updatedClass;
    }
}
