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

import com.school.entity.Parent;
import com.school.entity.Student;
import com.school.exception.DatabaseException;
import com.school.exception.ParentNotFoundException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ParentRepository;
import com.school.repository.StudentRepository;

@Repository
@Transactional
public class ParentRepositoryImpl implements ParentRepository{
	   static Logger logger = Logger.getLogger("ParentRepository.class");
	   @Autowired
	   private SessionFactory sessionFactory;
	   
	   @Autowired
	   private StudentRepository studentRepository;
	   public boolean checkParentById(Long id) throws ParentNotFoundException {
		      logger.debug("In Checking Parent Id...");
			  Parent parent = new Parent();
			  boolean status = false;
			  Session session=sessionFactory.getCurrentSession();
			  Query query=session.createQuery("from Parent where id=:id");
		      query.setParameter("id",id);
		      parent= (Parent) query.uniqueResultOptional().orElse(null);
		      status=(parent!=null);
		      if(!status)
		      {
		    	  throw new ParentNotFoundException("Parent Not Found,Enter Valid Id!");  
		      }
		      return status;
		  }
       public Long addParent(Long rollNo,Parent parent) throws DatabaseException{
    	   logger.debug("In Adding Parent details...");
    	   Session session = null;
    	   Long parentId = null;
    	   try
    	   {
    		   logger.info("Adding Parent Details...");
    		   boolean status = studentRepository.checkStudentRollNo(rollNo);
    		   if(status) 
    		   {
    			   session = sessionFactory.getCurrentSession();
    			   Student studentEntity= new Student();
    			   studentEntity =studentRepository.getStudent(rollNo);
    			   Set<Student> student=new HashSet<>();
    			   student.add(studentEntity);
    			   Parent parentEntity =new Parent();
    			   parentEntity.setId(parent.getId());
    			   parentEntity.setFatherName(parent.getFatherName());
    			   parentEntity.setMotherName(parent.getMotherName());
    			   parentEntity.setContactNo(parent.getContactNo());
    			   parentEntity.setStudentEntity(student);
    			   parentId = (Long) session.save(parentEntity);
    		   }
    		   if(parentId!=null)
    		   {
    			   logger.info("Adding Parent Details is Completed");
    		   }
    	   }
    	   catch(HibernateException | StudentNotFoundException e)
    	   {
    		    logger.error("Error Occurred while Saving Parent details");
    	        throw new DatabaseException(e.getMessage());
    	   }
    	   return parentId;
       }
       
      public List<Parent> getParent(Long rollNo) throws DatabaseException
       {
    	   logger.debug("In Retrieving Parent Details...");
    	   List<Parent> parent =new ArrayList<Parent>();
    	   Session session=null;
    	   try
    	   {
    		   logger.info("Adding Parent Details");
    		   boolean response=studentRepository.checkStudentRollNo(rollNo);
    		   if(response){
    			   session=sessionFactory.getCurrentSession();
    			   // Query query=session.createNativeQuery("Select p from Parent p join fetch p.rollNo where p.rollNo.rollNo=:rollNo");
    			   Query query = session.createSQLQuery("Select p.id,p.fatherName,p.motherName,p.contactNo from Parent p join rollNo r on p.id=r.Parent_id where r.studentEntity_rollNo=:rollNo");
    			   query.setParameter("rollNo",rollNo);
    			   List<Object[]> parentDetail=  query.list();
    			   for(Object[] data:parentDetail)
    			   {
    				   Parent p = new Parent();
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
    	   }
    	   catch(HibernateException | StudentNotFoundException e )
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
       
       public  Parent updateParent(Long id,Parent parent) throws DatabaseException{
    	   logger.debug("In Updating Parent Details...");
    	   Session session=null;
    	   Parent parentDetails = null;
    	   try
    	   {
    		   logger.info("Updating Parent Details");  
    		   boolean status=checkParentById(id);
    		   if(status) {
    			   session = sessionFactory.getCurrentSession();
    			   session.find(Parent.class, id);
    			   Parent parentDetail =session.load(Parent.class, id);
    			   parentDetail.setFatherName(parent.getFatherName());
    			   parentDetail.setMotherName(parent.getMotherName());
    			   parentDetail.setContactNo(parent.getContactNo());
    			   parentDetails = (Parent) session.merge(parentDetail);
    			   if(parentDetails!=null)
    			   {
    				   logger.info("Updating Parent Details is Completed");  
    			   }
    	       }
    	   }
    	   catch(HibernateException | ParentNotFoundException e)
    	   {
    		   logger.error("Error Occurred while Updating Parent details");
    		   throw new DatabaseException(e.getMessage());
    	   }
    	   return parentDetails;
       }
      public Parent deleteParent(Long id) throws DatabaseException
      {
       Parent parent = null; 
   	   Session session=null;
       try
       {
    	   logger.info("Deleting Parent Details");
    	   boolean status=checkParentById(id);
    	   if(status) {
    		   session = sessionFactory.openSession();
    		   session.beginTransaction();
    		   session.find(Parent.class, id);
    		   Parent parentDetail =session.load(Parent.class, id);
    		   session.delete(parentDetail);
    		   parent =session.load(Parent.class, id);
    		   if(parent==null)
    		   {
    			   logger.info("Deleting Parent Details is Completed"); 
    		   }
    	   }
       }
       catch(HibernateException | ParentNotFoundException e)
       {
    	   logger.error("Error Occurred while Deleting Parent details");
    	   throw new DatabaseException(e.getMessage());
       }
   	   return parent;
      }
      
}
