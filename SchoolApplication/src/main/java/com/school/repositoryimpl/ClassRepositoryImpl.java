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
import com.school.dto.Class;
import com.school.entity.ClassEntity;
import com.school.exception.ClassAlreadyExistException;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.repository.ClassRepository;
import com.school.util.ClassMapper;

@Repository
@Transactional
public class ClassRepositoryImpl implements ClassRepository {
	static Logger logger = Logger.getLogger("ClassRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;

	public void checkClassRoomNo(Long roomNo) throws ClassNotFoundException  {
		  logger.debug("In Check Class Room No Details Method");
	      Session session =sessionFactory.getCurrentSession();
	      Query<ClassEntity> query=session.createQuery("from ClassEntity where roomNo=:rNo");
	      query.setParameter("rNo",roomNo);
	      ClassEntity classDetail = new ClassEntity();
	      classDetail= query.uniqueResultOptional().orElse(null);
	      if(classDetail==null)
		  {
		     throw new ClassNotFoundException("Class not Found,Enter the Valid Room No!");
		  }
	  }
	
	public void checkClassStandardSection(String standard,String section) throws ClassAlreadyExistException  {
		  logger.debug("In Check Class Details Method");
	      Session session =sessionFactory.getCurrentSession();
	      Query<ClassEntity> query=session.createQuery("from ClassEntity where standard=:standard and section=:section");
	      query.setParameter("standard",standard);
	      query.setParameter("section",section);
	      ClassEntity classDetail = new ClassEntity();
	      classDetail= query.uniqueResultOptional().orElse(null);
	      if(classDetail!=null)
		  {
		     throw new ClassAlreadyExistException("Standard and Section already exists");
		  }
	  }
	
	public Long addClass(Class classDetail) throws DatabaseException, NotFoundException {
		logger.debug("In Add Class Details Method");
		Session session=null;
		Long roomNo = 0l;
		try
		{
			logger.info("In Add Class Details Method");
			checkClassStandardSection(classDetail.getStandard(),classDetail.getSection());
			session = sessionFactory.getCurrentSession();
			ClassEntity classEntity = ClassMapper.mapClass(classDetail);
			roomNo = (Long) session.save(classEntity);
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
	
	public List<ClassEntity> getAllClass() throws DatabaseException 
    {   
    	logger.debug("In Get All Class Details Method");
    	Session session=null;
		List<ClassEntity> classList =new ArrayList<>();
		try
		{
			logger.info("Retriving Class Details Method");
			session=sessionFactory.getCurrentSession();
			Query<ClassEntity> query=session.createQuery("from ClassEntity c");
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
    
    public ClassEntity getParticularClass(Long roomNo) throws DatabaseException,NotFoundException {
    	logger.debug("In Get Class Details Method");
    	ClassEntity classEntity = new ClassEntity();
    	Session session=null;
    	
		try
		{
			logger.info("Retriving Class Details Method");
			checkClassRoomNo(roomNo);
			session=sessionFactory.getCurrentSession();
			Query<ClassEntity> query=session.createQuery("from ClassEntity where roomNo=:rNo");
			query.setParameter("rNo",roomNo);
			classEntity = query.getSingleResult();
			if(classEntity!=null)
			{
				logger.info("Retrieving Particular Class Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Retrieving Class Details");
			throw new DatabaseException(e.getMessage());
		}
		return classEntity;
    }

    public ClassEntity updateClass(Long roomNo,Class classDetail) throws DatabaseException, NotFoundException{
    	logger.debug("In Updating Class Details Method");
    	Session session=null;
    	ClassEntity updatedClass = null;
    	
		try {
			logger.info("Updating Class Details Method");
			checkClassRoomNo(roomNo);
			checkClassStandardSection(classDetail.getStandard(),classDetail.getSection());
			session=sessionFactory.getCurrentSession();
			ClassEntity classEntity = ClassMapper.mapClass(classDetail);
			ClassEntity classDetails=session.load(ClassEntity.class,roomNo);
			classDetails.setSection(classEntity.getSection());
			classDetails.setStandard(classEntity.getStandard());
			classDetails.setPassPercentage(classEntity.getPassPercentage());
			updatedClass = (ClassEntity) session.merge(classDetails);
			if(updatedClass!=null)
			{
				logger.info("Updating Class Details is Completed");
			}
		} catch (HibernateException e) {
			logger.error("Error Occured while Updating Class Details");
			throw new DatabaseException(e.getMessage());
		}
		return updatedClass;
    }
    
     public Long getRoomNo(String standard,String section) throws DatabaseException, NotFoundException
     {
    	 logger.debug("In Retrieving Class Room No's Method");
    	 Session session = null;
    	 Long roomNo = null;
    	 try {
    		 logger.info("Retrieving Class Room No's ");
    		 session = sessionFactory.getCurrentSession();
    		 Query query = session.createQuery("Select roomNo from ClassEntity where standard=:standard and section=:section");
    		 query.setParameter("standard", standard);
    		 query.setParameter("section", section);
    		 roomNo = (Long) query.uniqueResult();
    		 if(roomNo!=null)
    		 {
    			 logger.info("Retrieving Class Room No's is Completed ");
    		 }
    		 else
    		 {
    			 throw new ClassNotFoundException("No Class Room Assigned!");
    		 }
    	 }
    	 catch(HibernateException e)
    	 {
    		 logger.info("Error Occured while Retrieving Class Room No's ");
    		 throw new DatabaseException(e.getMessage());
    	 }
    	 return roomNo;
     }
    	
     public List<ClassEntity> getClassList(List<Long> roomNoList) throws DatabaseException {
     	logger.debug("In Get Class Details Method...");
     	List<ClassEntity> classEntityList = new ArrayList<ClassEntity>();
     	Session session=null;
 		try
 		{
 			logger.info("Retriving Class Details Method...");
 			session=sessionFactory.getCurrentSession();
 			for(int i=0;i<roomNoList.size();i++)
 			{
 				Query<ClassEntity> query=session.createQuery("from ClassEntity where roomNo=:roomNo");
 				query.setParameter("roomNo",roomNoList.get(i));
 				classEntityList.add(query.getSingleResult());
 			}
 			if(!classEntityList.isEmpty())
 			{
 				logger.info("Retrieving Class Details is Completed");
 			}
 		}
 		catch(HibernateException e)
 		{
 			logger.error("Error Occured while Retrieving Class Details");
 			throw new DatabaseException(e.getMessage());
 		}
 			return classEntityList;
     	}

     public Integer updatePassPercentage(Long roomNo,Double passPercentage) throws NotFoundException, DatabaseException
     {
    	logger.debug("In Updating Pass Percentage Method...");
     	Session session=null;
     	Integer count = null;
     	
 		try {
 			logger.info("Updating Pass Percentage...");
 			checkClassRoomNo(roomNo);
 			session=sessionFactory.getCurrentSession();
 			Query query = session.createQuery("Update ClassEntity set passPercentage=:passPercentage where roomNo=:roomNo");
 			query.setParameter("passPercentage", passPercentage);
 			query.setParameter("roomNo", roomNo);
 			count = query.executeUpdate();
 		}
 		catch(HibernateException e)
 		{
 			logger.error("Error Occured while Updating Class Details");
			throw new DatabaseException(e.getMessage());
 		}
 		return count;
     }
}