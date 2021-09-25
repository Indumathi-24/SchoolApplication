package com.school.repositoryimpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import com.school.entity.SubjectEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.SubjectNotFoundException;
import com.school.repository.SubjectRepository;
import com.school.util.SubjectMapper;
import com.school.dto.Subject;

@Repository
@Transactional
public class SubjectRepositoryImpl implements SubjectRepository{
	
	static Logger logger = Logger.getLogger("SubjectRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	 public void checkSubjectCode(String code) throws SubjectNotFoundException {
		  logger.debug("In Checking Subject Code...");
		  SubjectEntity subject = new SubjectEntity();
		  Session session=sessionFactory.getCurrentSession();
		  Query<SubjectEntity> query=session.createQuery("from SubjectEntity where code=:code");
	      query.setParameter("code",code);
	      subject = query.uniqueResultOptional().orElse(null);
	      if(subject==null)
	      {
	    	  throw new SubjectNotFoundException("Subject Not Found ,Enter Valid Code");
	      }
	      
	  }
	public String addSubject(Long roomNo,Subject subject) throws DatabaseException
	{
		logger.debug("In Adding Subject Details...");
		Session session=null;
		String subjectCode = null;
		try {
			logger.info("Adding Subject Details...");
			session=sessionFactory.getCurrentSession();
			SubjectEntity subjectDetail = SubjectMapper.mapSubject(subject, roomNo);
			subjectCode = (String) session.save(subjectDetail);
			if(subjectCode!=null)
			{
				logger.info("Adding Subject Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Saving Subject Details");
			throw new DatabaseException(e.getMessage());
		}
		
		return subjectCode;
	}
    public List<SubjectEntity> getAllSubject(Long roomNo) throws DatabaseException
    {
      logger.debug("In Retrieving Subject All Details...");
      List<SubjectEntity> subjectList = new ArrayList<>();
  	  Session session=null;
  	  try
  	  {   
  		  logger.info("Retrieving Subject Details...");
  		  session=sessionFactory.getCurrentSession();
  		  Query<SubjectEntity> query =session.createQuery("from SubjectEntity s where roomNo=:rNo");
  		  query.setParameter("rNo",roomNo);
  		  subjectList=query.list();
  		  if(!subjectList.isEmpty())
  		  {
  			logger.info("Retrieving Subject Details is Completed...");
  		  }
  	  }
  	  catch(HibernateException e)
  	  {
  		logger.error("Error Occured while Retrieving Subject Details");
  		throw new DatabaseException(e.getMessage());
  	  }
  	  return subjectList;
    }
    
    public SubjectEntity getParticularSubject(Long roomNo,String code) throws DatabaseException, NotFoundException{
      logger.debug("In Retrieving Subject Details...");
  	  SubjectEntity subject = new SubjectEntity();
 	  Session session=null;
 	  try
 	  {
 		  logger.info("Retrieving Subject Details...");
 		  checkSubjectCode(code);
 		  session=sessionFactory.getCurrentSession();
 		  Query<SubjectEntity> query = session.createQuery("from SubjectEntity where roomNo=:rNo and code=:code");
 		  query.setParameter("rNo", roomNo);
 		  query.setParameter("code", code);
 		  subject = query.getSingleResult();
 		  if(subject!=null)
 		  {
 			 logger.info("Retrieving Subject Details is Completed");
 		  }
 	  }
 	  catch(HibernateException e )
 	  {
 		 logger.error("Error Occured while Retrieving Subject Details");
 		 throw new DatabaseException(e.getMessage());
 	  }
  		return subject;
    }
    
    public SubjectEntity updateSubject(Long roomNo,String code,Subject subject) throws DatabaseException, NotFoundException{
      logger.debug("In Updating Subject Details...");
	  Session session=null;
	  SubjectEntity subjectDetail;
	  try
	  {
		  logger.info("Updating Subject Details...");
		  checkSubjectCode(code);
		  session=sessionFactory.getCurrentSession();
		  SubjectEntity subjectEntity = SubjectMapper.mapSubject(subject, roomNo);
  		  session.find(SubjectEntity.class,code);
  		  SubjectEntity subjectDetails=session.load(SubjectEntity.class,code);
  		  subjectDetails.setName(subjectEntity.getName());
  		  subjectDetail = (SubjectEntity) session.merge(subjectDetails);
  		  if(subjectDetail!=null)
  		  {
  			logger.info("Updating Subject Details is Completed");
  		  }
	  }
	  catch(HibernateException e)
	  {
		  logger.info("Error Occured while Updating Subject Details");
		  throw new DatabaseException(e.getMessage());
	  }
	  return subjectDetail;
    }
    
    public SubjectEntity deleteSubject(Long roomNo,String code) throws DatabaseException, NotFoundException
    {
      logger.debug("In Deleting Subject Details...");
  	  SubjectEntity subjectDetail;
	  Session session=null;
	  try
	  {
		logger.info("Deleting Subject Details...");
		checkSubjectCode(code);
		session=sessionFactory.getCurrentSession();
  	    session.find(SubjectEntity.class,code);
  		SubjectEntity subjectEntity=session.load(SubjectEntity.class,code);
  		session.delete(subjectEntity);
  	    subjectDetail = session.load(SubjectEntity.class,code);
  	    if(subjectDetail==null)
  	    {
  	    	logger.info("Deleting Subject Details is Completed");
  	    }
	  }
	  catch(HibernateException e)
	  {
		logger.info("Error Occured while Deleting Subject Details");
  		throw new DatabaseException(e.getMessage());
	  }
	  return subjectDetail;
    }
    
}
