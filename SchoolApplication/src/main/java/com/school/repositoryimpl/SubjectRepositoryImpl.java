package com.school.repositoryimpl;

import java.util.ArrayList;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.hibernate.HibernateException;
import com.school.entity.Subject;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.exception.SubjectNotFoundException;
import com.school.repository.ClassRepository;
import com.school.repository.SubjectRepository;
import com.school.entity.Class;
import com.school.entity.Student;
import com.school.entity.Subject;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ClassRepository classRepository;
	
	 public boolean checkSubjectCode(String code) {
		  List<Subject> subjectList = new ArrayList<Subject>();
		  boolean status = false;
		  Session session=sessionFactory.openSession();
		  Query query=session.createQuery("from Subject where code=:code");
	      query.setParameter("code",code);
	      subjectList=query.list();
	      status=(!subjectList.isEmpty());
	      session.close();
	      return status;
	  }
	public ResponseEntity<String> addSubject(Long roomNo,Subject subject) throws DatabaseException
	{
		ResponseEntity<String> response=null;
		Session session=null;
		
		try {
			    classRepository.checkClassRoomNo(roomNo);
				session=sessionFactory.openSession();
				session.beginTransaction();
				Class classDetail =new Class();
				classDetail.setRoomNo(roomNo);
				Subject subjectDetail=new Subject();
				subjectDetail.setCode(subject.getCode());
				subjectDetail.setName(subject.getName());
				subjectDetail.setClassEntity(classDetail);
				session.save(subjectDetail);
				session.getTransaction().commit();
				session.close();
				response=new ResponseEntity<String>("Subject Details Added Successfully",new HttpHeaders(),HttpStatus.OK);
			
		}
		catch(HibernateException | ClassNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}
    public ResponseEntity<?> getAllSubject(Long roomNo) throws DatabaseException
    {
      List<Subject> subjectList = new ArrayList<Subject>();
  	  
  	  ResponseEntity<?> response=null;
  	  Session session=null;
  	  try
  	  {         classRepository.checkClassRoomNo(roomNo);
  			  	session=sessionFactory.openSession();
  			  	Query query =session.createQuery("from Subject s where roomNo=:rNo");
  			  	query.setParameter("rNo",roomNo);
  			  	subjectList=query.list();
  			  	session.close();
  			    response=new ResponseEntity<>(subjectList,new HttpHeaders(),HttpStatus.OK);
  		  
  	  }
  	  catch(HibernateException | ClassNotFoundException e)
  	  {
  		throw new DatabaseException(e.getMessage());
  	  }
  	  return response;
    }
    
    public  ResponseEntity<?> getParticularSubject(Long roomNo,String code) throws DatabaseException{
  	  List<Subject> subjectList = new ArrayList<Subject>();
  	  boolean response=checkSubjectCode(code);
  	  ResponseEntity<?> responseBody=null;
 	  Session session=null;
 	  try
 	  {
 		 classRepository.checkClassRoomNo(roomNo);
 		  if(!response) {
  			throw new SubjectNotFoundException("Subject Not Found");
 		  }
 		  else 
 		  {
 			  session=sessionFactory.openSession();
 			  Query query=session.createQuery("from Subject where roomNo=:rNo and code=:code");
 			  query.setParameter("rNo", roomNo);
 			  query.setParameter("code", code);
 			  subjectList=query.list();
 			  session.close();
 			  responseBody=new ResponseEntity<>(subjectList,new HttpHeaders(),HttpStatus.OK);

 		  }
 	  }
 	  catch(HibernateException | ClassNotFoundException | SubjectNotFoundException e )
 	  {
 		  throw new DatabaseException(e.getMessage());
 	  }
  		return responseBody;
    }
    
    public ResponseEntity<String> updateSubject(Long roomNo,String code,Subject subject) throws DatabaseException{
  	  
  	  boolean response=checkSubjectCode(code);
  	  ResponseEntity<String> responseBody=null;
	  Session session=null;
	  try
	  {
		    classRepository.checkClassRoomNo(roomNo);
  			if(!response) {
  				throw new SubjectNotFoundException("Subject Not Found,Enter Valid Room No!");
  			}
  			else {
  				session=sessionFactory.openSession();
  				session.beginTransaction();
  				session.find(Subject.class,code);
  				Subject subjectDetails=session.load(Subject.class,code);
  				subjectDetails.setName(subject.getName());
  				session.merge(subjectDetails);
  				session.flush();
  				session.getTransaction().commit();
  				session.close();
  				responseBody = new ResponseEntity<String>("Subject Details Updated Successfully",new HttpHeaders(),HttpStatus.OK);
  		    }
	  }
	  catch(HibernateException | ClassNotFoundException | SubjectNotFoundException e)
	  {
		  throw new DatabaseException(e.getMessage());
	  }
	  return responseBody;
    }
    
    public ResponseEntity<String> deleteSubject(Long roomNo,String code) throws DatabaseException
    {
  	 
  	  boolean response=checkSubjectCode(code);
  	  ResponseEntity<String> responseBody=null;
	  Session session=null;
	  try
	  {
		classRepository.checkClassRoomNo(roomNo);
  		if(!response) {
  			throw new SubjectNotFoundException("Subject Not Found,Enter valid Roll No");
  		}
  		else {
  				session=sessionFactory.openSession();
  				session.beginTransaction();
  				session.find(Subject.class,code);
  				Subject subjectEntity=session.load(Subject.class,code);
  				session.delete(subjectEntity);
  				session.getTransaction().commit();
  				session.close();
  				responseBody=new ResponseEntity<String>("Subject Details Deleted Successfully",new HttpHeaders(),HttpStatus.OK);
  			}
	  }
	  catch(HibernateException | ClassNotFoundException | SubjectNotFoundException e)
	  {
  		throw new DatabaseException(e.getMessage());
	  }
	  return responseBody;
    }
    
}
