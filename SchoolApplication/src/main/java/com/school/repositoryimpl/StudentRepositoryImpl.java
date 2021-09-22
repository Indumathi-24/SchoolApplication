package com.school.repositoryimpl;


import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpHeaders; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.entity.Class;
import com.school.entity.Student;
import com.school.entity.Teacher;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ClassRepository;
import com.school.repository.StudentRepository;
  
  @Repository
  @Transactional
  public class StudentRepositoryImpl implements StudentRepository{
  
  static Logger logger = Logger.getLogger("StudentRepositoryImpl.class");
  @Autowired 
  private SessionFactory sessionFactory;
  
  @Autowired
  private ClassRepository classRepository;

  public boolean checkStudentRollNo(Long rollNo) throws StudentNotFoundException {
	  logger.debug("In Checking Student Roll No...");
	  Student studentDetail = new Student();
	  boolean status = false;
      Session session=sessionFactory.getCurrentSession();
	  Query query=session.createQuery("from Student where rollNo=:rNo");
	  query.setParameter("rNo",rollNo);
	  studentDetail = (Student) query.uniqueResultOptional().orElse(null);
	  status = (studentDetail!=null);
	  if(!status)
	  {
		  throw new StudentNotFoundException("Student Not Found,Enter Valid Roll No");
	  }
      return status;
  }
  
  public Student getStudent(Long rollNo) {
	  logger.debug("In Retrieving Particular Student Detail");
	  Student student=new Student();
	  Session session=sessionFactory.getCurrentSession();
	  Query query=session.createQuery("from Student where rollNo=:rNo");
	  query.setParameter("rNo",rollNo);
	  student=(Student) query.getSingleResult();
	  return student;
	  
  }
  public Long addStudent(Long roomNo,Student student) throws DatabaseException{ 
	     logger.debug("In Adding Student Details");
		 Session session=null;
		 Long studentId = null;
		 try
		 {
			 logger.info("Adding Student Details...");
			 boolean status = classRepository.checkClassRoomNo(roomNo);
			 if(status)
			 {
				 session=sessionFactory.getCurrentSession();
				 Class classEntity=new Class();
				 classEntity.setRoomNo(roomNo);
				 Student studentEntity=new Student();
				 studentEntity.setName(student.getName());
				 studentEntity.setRollNo(student.getRollNo());
				 studentEntity.setDateOfBirth(student.getDateOfBirth());
				 studentEntity.setGender(student.getGender());
				 studentEntity.setAddress(student.getAddress());
				 studentEntity.setClassEntity(classEntity);
				 studentId = (Long) session.save(studentEntity);
			 }
			 if(studentId!=null)
			 {
				 logger.info("Adding Parent Details is Completed");
			 }
		 }
		 catch(HibernateException | ClassNotFoundException e)
		 {
			 logger.error("Error Occurred While Saving Student Details");
			 throw new DatabaseException(e.getMessage()); 
		 }
		 return studentId;
  }
  
  public List<Student> getAllStudent(Long roomNo) throws DatabaseException{
	  logger.debug("In Retrieving All Particular Student Detail");
	  List<Student> studentList = new ArrayList<Student>();
	  Session session=null;
	 
	  try
	  {
		    logger.info("Retrieving All Particular Student Detail");
		    boolean status = classRepository.checkClassRoomNo(roomNo);
		    if(status)
		    {
		    	session=sessionFactory.getCurrentSession();
		    	Query query =session.createQuery("from Student s where roomNo=:rNo");
		    	query.setParameter("rNo",roomNo);
		    	studentList=query.list();
		    }
		    if(!studentList.isEmpty())
		    {
		    	logger.info("Retrieving All Particular Student Detail is Completed");
		    }
	  }
		catch(HibernateException | ClassNotFoundException e)
		{
			logger.error("Error Occurred While Retrieving Student Details");
			throw new DatabaseException(e.getMessage());
		}
	   return studentList;
   }
		
  
  
  public Student getParticularStudent(Long roomNo,Long rollNo) throws DatabaseException{
	  logger.debug("In Retrieving Particular Student Detail");
	  Student student = new Student();
	  Session session=null;
	  try
	  {
		  logger.info("Retrieving Particular Student Detail");
		  boolean response = checkStudentRollNo(rollNo);
		  boolean status = classRepository.checkClassRoomNo(roomNo);
		  if(response && status) {
			  session=sessionFactory.getCurrentSession();
			  Query query=session.createQuery("from Student where roomNo=:rNo and rollNo=:roNo");
			  query.setParameter("rNo", roomNo);
			  query.setParameter("roNo", rollNo);
			  student=(Student) query.getSingleResult();
		  }
		  if(student!=null)
		  {
			  logger.info("Retrieving Particular Student Detail is Completed");
		  }
	  }
	  catch(HibernateException | ClassNotFoundException | StudentNotFoundException e)
	  {
		  logger.error("Error Occurred While Retrieving Student Details");
		  throw new DatabaseException(e.getMessage());
	  }
		return student;
  }
  
  public Student updateStudent(Long roomNo,Long rollNo,Student student) throws DatabaseException{
	  logger.debug("In Updating Student Detail");
	  Session session=null;
	  Student studentDetail = new Student();
	  try
	  {
		logger.info("Updating Student Detail");
		boolean response=checkStudentRollNo(rollNo);
		boolean status =classRepository.checkClassRoomNo(roomNo);
		if(response && status) {
		    session=sessionFactory.getCurrentSession();
			session.find(Student.class,rollNo);
			Student studentDetails=session.load(Student.class, rollNo);
			studentDetails.setName(student.getName());
			studentDetails.setDateOfBirth(student.getDateOfBirth());
			studentDetails.setGender(student.getGender());
			studentDetails.setAddress(student.getAddress());
			studentDetail = (Student) session.merge(studentDetails);
		}
		if(studentDetail!=null)
		{
			logger.info("Updating Student Detail is Completed");
		}
	  }
	  catch(HibernateException | ClassNotFoundException | StudentNotFoundException e) {
		    logger.error("Error Occurred While Updating Student Details");
		    throw new DatabaseException(e.getMessage());
	  }
	  return studentDetail;
  }
  
  public Student deleteStudent(Long roomNo,Long rollNo) throws DatabaseException
  {
	  logger.debug("In Deleting Student Detail");
	  Session session=null;
	  Student studentDetail = new Student();
	  try
	  {
		  logger.info("Deleting Student Detail");
		  boolean response = checkStudentRollNo(rollNo);
		  boolean status = classRepository.checkClassRoomNo(roomNo);
		  if(response && status) 
		  {
		    session=sessionFactory.getCurrentSession();
			session.find(Student.class,rollNo);
			Student studentEntity=session.load(Student.class, rollNo);
			session.delete(studentEntity);
			studentDetail = session.load(Student.class, rollNo);
		  }
		  if(studentDetail!=null)
		  {
			  logger.info("Deleting Student Detail is Completed");
		  }
	  }
	  catch(HibernateException | ClassNotFoundException | StudentNotFoundException e)
	  {
		logger.error("Error Occurred While Deleting Student Details");
		throw new DatabaseException(e.getMessage());
	  }
	  return studentDetail;
  }
  
}
