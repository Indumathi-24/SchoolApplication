package com.school.repositoryimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.dto.Parent;
import com.school.entity.ParentEntity;
import com.school.entity.StudentEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ParentNotFoundException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ParentRepository;
import com.school.repository.StudentRepository;
import com.school.util.ParentMapper;

@Repository
@Transactional
public class ParentRepositoryImpl implements ParentRepository{
	   static Logger logger = Logger.getLogger("ParentRepository.class");
	   @Autowired
	   private SessionFactory sessionFactory;
	   
	   @Autowired
	   private StudentRepository studentRepository;
	   
	   public void checkParentById(Long id) throws ParentNotFoundException {
		      logger.debug("In Checking Parent Id...");
			  ParentEntity parent = new ParentEntity();
			  Session session=sessionFactory.getCurrentSession();
			  Query<ParentEntity> query=session.createQuery("from ParentEntity where id=:id");
		      query.setParameter("id",id);
		      parent= query.uniqueResultOptional().orElse(null);
		      if(parent==null)
		      {
		    	  throw new ParentNotFoundException("Parent Not Found,Enter Valid Id!");  
		      }
		  }
	   
       public Long addParent(Long rollNo,Parent parent) throws DatabaseException{
    	   logger.debug("In Adding Parent details...");
    	   Session session = null;
    	   Long parentId = null;
    	   try
    	   {
    		   logger.info("Adding Parent Details...");
    		   session = sessionFactory.getCurrentSession();
    		   StudentEntity studentEntity= new StudentEntity();
    		   studentEntity =studentRepository.getStudent(rollNo);
    		   Set<StudentEntity> student=new HashSet<>();
    		   student.add(studentEntity);
    		   ParentEntity parentEntity = ParentMapper.mapParent(parent);
    		   parentEntity.setStudentEntity(student);
    		   parentId = (Long) session.save(parentEntity);
    		   if(parentId!=null)
    		   {
    			   logger.info("Adding Parent Details is Completed");
    		   }
    	   }
    	   catch(HibernateException e)
    	   {
    		    logger.error("Error Occurred while Saving Parent details");
    	        throw new DatabaseException(e.getMessage());
    	   }
    	   return parentId;
       }
       
      public List<ParentEntity> getParent(Long rollNo) throws DatabaseException
       {
    	   logger.debug("In Retrieving Parent Details...");
    	   List<ParentEntity> parent =new ArrayList<>();
    	   Session session=null;
    	   try
    	   {
    		   logger.info("Adding Parent Details");
    		   session=sessionFactory.getCurrentSession();
    			   // Query query=session.createNativeQuery("Select p from Parent p join fetch p.rollNo where p.rollNo.rollNo=:rollNo");
    		   Query query = session.createSQLQuery("Select p.id,p.fatherName,p.motherName,p.contactNo from Parent p join rollNo r on p.id=r.ParentEntity_id where r.studentEntity_rollNo=:rollNo");
    		   query.setParameter("rollNo",rollNo);
    		   List<Object[]> parentDetail =  query.list();
    		   for(Object[] data:parentDetail)
    		   {
    				ParentEntity p = new ParentEntity();
    				p.setId(Long.parseLong(data[0].toString()));
    				p.setFatherName(data[1].toString());
    				p.setMotherName(data[2].toString());
    				p.setContactNo(Long.parseLong(data[3].toString()));
    				parent.add(p);
    			}
    		    if(!parent.isEmpty())
    		    {
    		    	logger.info("Adding Parent Details is Completed");
    		     }
    	   }
    	   catch(HibernateException  e )
    	   {
    		   logger.error("Error Occurred while Saving Parent details");
    		   throw new DatabaseException(e.getMessage());
    	   }
    	   return parent;
       }
       
       /*public Parent getParent(Long id) throws ParentNotFoundException
       {
    	   Parent parent =new Parent();
    	   boolean status=checkParentById(id);
    	   if(!status) {
    		   throw new ParentNotFoundException("Parent Not Found");
    	   }
    	   else
    	   {
    		   Session session=sessionFactory.openSession();
    		   Query query=session.createQuery("from Parent where id=:id");
    		   query.setParameter("id", id);
    		   parent=(Parent) query.getSingleResult();
    		   session.close();
    	   }
    	   return parent;
       }*/
       
       public  ParentEntity updateParent(Long id,Parent parent) throws DatabaseException, NotFoundException{
    	   logger.debug("In Updating Parent Details...");
    	   Session session=null;
    	   ParentEntity parentDetails = null;
    	   try
    	   {
    		   logger.info("Updating Parent Details");  
    		   checkParentById(id);
    		   ParentEntity parentEntity = ParentMapper.mapParent(parent);
    		   session = sessionFactory.getCurrentSession();
    		   session.find(ParentEntity.class, id);
    		   ParentEntity parentDetail =session.load(ParentEntity.class, id);
    		   parentDetail.setFatherName(parentEntity.getFatherName());
    		   parentDetail.setMotherName(parentEntity.getMotherName());
    		   parentDetail.setContactNo(parentEntity.getContactNo());
    		   parentDetails = (ParentEntity) session.merge(parentDetail);
    		   if(parentDetails!=null)
    		   {
    		       logger.info("Updating Parent Details is Completed");  
    		   }
    	   }
    	   catch(HibernateException e)
    	   {
    		   logger.error("Error Occurred while Updating Parent details");
    		   throw new DatabaseException(e.getMessage());
    	   }
    	   return parentDetails;
       }
      public ParentEntity deleteParent(Long id) throws DatabaseException, NotFoundException
      {
       ParentEntity parent = null; 
   	   Session session = null;
       try
       {
    	   logger.info("Deleting Parent Details");
    	   checkParentById(id);
    	   session = sessionFactory.getCurrentSession();
    	   session.find(ParentEntity.class, id);
    	   ParentEntity parentDetail =session.load(ParentEntity.class, id);
    	   session.delete(parentDetail);
    	   parent =session.load(ParentEntity.class, id);
    	   if(parent==null)
    	   {
    		   logger.info("Deleting Parent Details is Completed"); 
    	   }
       }
       catch(HibernateException e)
       {
    	   logger.error("Error Occurred while Deleting Parent details");
    	   throw new DatabaseException(e.getMessage());
       }
   	   return parent;
      }
      
}
