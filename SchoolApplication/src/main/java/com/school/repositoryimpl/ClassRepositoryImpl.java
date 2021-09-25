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
	
	public Long addClass(Class classDetail) throws DatabaseException {
		logger.debug("In Add Class Details Method");
		Session session=null;
		Long roomNo = 0l;
		try
		{
			logger.info("In Add Class Details Method");
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
    	ClassEntity classEntity = new ClassEntity();
    	Session session=null;
    	logger.debug("In Get Class Details Method");
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
    	
    	Session session=null;
    	ClassEntity updatedClass = null;
    	logger.debug("In Updating Class Details Method");
		try {
			logger.info("Updating Class Details Method");
			checkClassRoomNo(roomNo);
			session=sessionFactory.getCurrentSession();
			ClassEntity classEntity = ClassMapper.mapClass(classDetail);
			session.find(ClassEntity.class,roomNo);
			ClassEntity classDetails=session.load(ClassEntity.class,roomNo);
			classDetails.setSection(classEntity.getSection());
			classDetails.setStandard(classEntity.getStandard());
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
}
