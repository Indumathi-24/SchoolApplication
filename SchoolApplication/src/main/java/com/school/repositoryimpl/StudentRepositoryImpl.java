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
import com.school.dto.Student;
import com.school.entity.StudentEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.StudentRepository;
import com.school.util.StudentMapper;
  
@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository{
  
  static Logger logger = Logger.getLogger("StudentRepositoryImpl.class");
  @Autowired 
  private SessionFactory sessionFactory;
  

  public void checkStudentRollNo(Long rollNo) throws StudentNotFoundException {
	  logger.debug("In Checking Student Roll No...");
	  StudentEntity studentDetail = new StudentEntity();
      Session session=sessionFactory.getCurrentSession();
	  Query<StudentEntity> query = session.createQuery("from StudentEntity where rollNo=:rNo");
	  query.setParameter("rNo",rollNo);
	  studentDetail =  query.uniqueResultOptional().orElse(null);
	  if(studentDetail==null)
	  {
		  throw new StudentNotFoundException("Student Not Found,Enter Valid Roll No");
	  }
  }
  
  public StudentEntity getStudent(Long rollNo) {
	  logger.debug("In Retrieving Particular Student Detail");
	  StudentEntity student=new StudentEntity();
	  Session session=sessionFactory.getCurrentSession();
	  Query<StudentEntity> query = session.createQuery("from StudentEntity where rollNo=:rNo");
	  query.setParameter("rNo",rollNo);
	  student = query.getSingleResult();
	  return student;
	  
  }
  public Long addStudent(Long roomNo, Student student) throws DatabaseException{ 
	     logger.debug("In Adding Student Details");
		 Session session=null;
		 Long studentId = 0l;
		 try
		 {
			 logger.info("Adding Student Details...");
			 session=sessionFactory.getCurrentSession();
			 StudentEntity studentEntity = StudentMapper.mapStudent(student, roomNo);
			 studentId = (Long) session.save(studentEntity);
			 if(studentId!=null)
			 {
				 logger.info("Adding Parent Details is Completed");
			 }
		 }
		 catch(HibernateException e)
		 {
			 logger.error("Error Occurred While Saving Student Details");
			 throw new DatabaseException(e.getMessage()); 
		 }
		 return studentId;
  }
  
  public List<StudentEntity> getAllStudent(Long roomNo) throws DatabaseException, NotFoundException{
	  logger.debug("In Retrieving All Particular Student Detail");
	  List<StudentEntity> studentList = new ArrayList<>();
	  Session session=null;
	  try
	  {
		    logger.info("Retrieving All Particular Student Detail");
		    session=sessionFactory.getCurrentSession();
		    Query<StudentEntity> query = session.createQuery("from StudentEntity s where roomNo=:rNo");
		    query.setParameter("rNo",roomNo);
		    studentList=query.list();
		    if(!studentList.isEmpty())
		    {
		    	logger.info("Retrieving All Particular Student Detail is Completed");
		    }
		    else
		    {
		    	throw new StudentNotFoundException("Students are not assigned for this class!");
		    }
	  }
		catch(HibernateException e)
		{
			logger.error("Error Occurred While Retrieving Student Details");
			throw new DatabaseException(e.getMessage());
		}
	   return studentList;
   }
		
  
  
  public StudentEntity getParticularStudent(Long rollNo) throws DatabaseException, NotFoundException{
	  logger.debug("In Retrieving Particular Student Detail");
	  StudentEntity student = new StudentEntity();
	  Session session=null;
	  try
	  {
		  logger.info("Retrieving Particular Student Detail");
		  checkStudentRollNo(rollNo);
		  session=sessionFactory.getCurrentSession();
		  Query<StudentEntity> query=session.createQuery("from StudentEntity where rollNo=:rollNo");
		  query.setParameter("rollNo", rollNo);
		  student = query.getSingleResult();
		  if(student!=null)
		  {
			  logger.info("Retrieving Particular Student Detail is Completed");
		  }
	  }
	  catch(HibernateException e)
	  {
		  logger.error("Error Occurred While Retrieving Student Details");
		  throw new DatabaseException(e.getMessage());
	  }
		return student;
  }
  
  public StudentEntity updateStudent(Long roomNo,Long rollNo,Student student) throws DatabaseException, NotFoundException{
	  logger.debug("In Updating Student Detail");
	  Session session=null;
	  StudentEntity studentDetail = new StudentEntity();
	  try
	  {
		logger.info("Updating Student Detail");
		checkStudentRollNo(rollNo);
		session=sessionFactory.getCurrentSession();
		StudentEntity studentEntity = StudentMapper.mapStudent(student, roomNo);
		StudentEntity studentDetails=session.load(StudentEntity.class, rollNo);
		studentDetails.setName(studentEntity.getName());
		studentDetails.setDateOfBirth(studentEntity.getDateOfBirth());
		studentDetails.setGender(studentEntity.getGender());
		studentDetails.setAddress(studentEntity.getAddress());
		studentDetails.setPassword(studentEntity.getPassword());
		studentDetail = (StudentEntity) session.merge(studentDetails);
		if(studentDetail!=null)
		{
			logger.info("Updating Student Detail is Completed");
		}
	  }
	  catch(HibernateException e) {
		    logger.error("Error Occurred While Updating Student Details");
		    throw new DatabaseException(e.getMessage());
	  }
	  return studentDetail;
  }
  
  public Integer updateStudentPassword(Long rollNo,String password) throws DatabaseException, NotFoundException{
	  logger.debug("In Updating Student Password Detail");
	  Session session=null;
	  Integer count = 0;
	  try
	  {
		logger.info("Updating Student Detail");
		checkStudentRollNo(rollNo);
		session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("Update StudentEntity set password=:password where rollNo=:rollNo");
		query.setParameter("password", password);
		query.setParameter("rollNo", rollNo);
		count = query.executeUpdate();
		if(count!=null)
		{
			logger.info("Updating Student Password Detail is Completed");
		}
	  }
	  catch(HibernateException e) {
		    logger.error("Error Occurred While Updating Student Details");
		    throw new DatabaseException(e.getMessage());
	  }
	  return count;
  }
  
  public StudentEntity deleteStudent(Long roomNo,Long rollNo) throws DatabaseException, NotFoundException
  {
	  logger.debug("In Deleting Student Detail");
	  Session session=null;
	  StudentEntity studentDetail = new StudentEntity();
	  try
	  {
		logger.info("Deleting Student Detail");
		checkStudentRollNo(rollNo);
	    session=sessionFactory.getCurrentSession();
		session.find(StudentEntity.class,rollNo);
		StudentEntity studentEntity=session.load(StudentEntity.class, rollNo);
		session.delete(studentEntity);
		studentDetail = session.load(StudentEntity.class, rollNo);
	    if(studentDetail==null)
		{
			logger.info("Deleting Student Detail is Completed");
		}
	  }
	  catch(HibernateException e)
	  {
		logger.error("Error Occurred While Deleting Student Details");
		throw new DatabaseException(e.getMessage());
	  }
	  return studentDetail;
  }
  

    public Long getStudentRoomNo(Long rollNo) throws StudentNotFoundException, DatabaseException
    {
    	logger.debug("In Retrieving Student Room No");
    	Session session=null;
    	Long roomNo=null;
    	try {
    	  logger.info("Retrieving Particular Student Detail");
  		  checkStudentRollNo(rollNo);
  		  session=sessionFactory.getCurrentSession();
  		  Query query=session.createQuery("Select classEntity.roomNo from StudentEntity where rollNo=:rollNo");
  		  query.setParameter("rollNo", rollNo);
  		  roomNo=(Long) query.uniqueResult();
    	}
    	catch(HibernateException e){
    		throw new DatabaseException(e.getMessage());
    	}
    	return roomNo;
    }
  
}
