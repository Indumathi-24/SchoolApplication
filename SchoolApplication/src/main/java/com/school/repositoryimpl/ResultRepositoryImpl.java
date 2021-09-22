package com.school.repositoryimpl;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import com.school.entity.Result;
import com.school.entity.Student;
import com.school.exception.DatabaseException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ResultRepository;
import com.school.repository.StudentRepository;

@Repository
@Transactional
public class ResultRepositoryImpl implements ResultRepository {
  
	static Logger logger = Logger.getLogger("ResultRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Long addResult(Long rollNo,Result result) throws DatabaseException
	{
		logger.debug("In Adding Student's Result...");
		Session session = null;
		Long resultId = null;
        try
        {
        	logger.info("Adding Student Results");
			boolean status=studentRepository.checkStudentRollNo(rollNo);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				Student student=new Student();
				student.setRollNo(rollNo);
				Result resultDetail = new Result();
				resultDetail.setTerm1(result.getTerm1());
				resultDetail.setTerm2(result.getTerm2());
				resultDetail.setTerm3(result.getTerm3());
				resultDetail.setResult(result.getResult());
				resultDetail.setStudent(student);
				resultId = (Long) session.save(resultDetail);
		   }
			if(resultId!=null) {
				logger.info("Adding Student Results is Completed");
			}
         }
			catch(HibernateException | StudentNotFoundException e)
			{
				logger.error("Error Occured While Saving Student Results");
				throw new DatabaseException(e.getMessage());
			}
           return resultId;
		}
	
	 public Result getResult(Long rollNo) throws DatabaseException
	 {
		    logger.debug("In Retrieving Student Results...");
	        Result result=new Result();
		    Session session=null;
	        try
	        {
	        	logger.info("Retrieving Student Results");
	        	boolean status=studentRepository.checkStudentRollNo(rollNo);
			    if(status)
			    {
					session=sessionFactory.getCurrentSession();
					Query query =session.createQuery("from Result r where r.student.rollNo=:rollNo");
					query.setParameter("rollNo", rollNo);
					result=(Result) query.getSingleResult();
   			  }
			    if(result!=null)
			    {
			    	logger.info("Retrieving Student Results is Completed");
			    }
	        }
			catch(HibernateException | StudentNotFoundException e)
			{
				logger.error("Error Occured While Retrieving Student Results");
				throw new DatabaseException(e.getMessage());
			}
	        return result;
	 }
	 
	 public Result updateResult(Long rollNo,Long resultId,Result result) throws DatabaseException
	 {
		    logger.debug("In Updating Student Results...");
		    Result resultDetail = new Result();
		    Session session=null;
	        try
	        {
	        	logger.info("Updating Student Results");
	        	boolean status=studentRepository.checkStudentRollNo(rollNo);
			    if(status)
			    {
			    	session=sessionFactory.getCurrentSession();
			        session.find(Result.class, resultId);
			        Result resultDetails = new Result();
			        resultDetails = session.load(Result.class, resultId);
			        resultDetails.setTerm1(result.getTerm1());
			        resultDetails.setTerm2(result.getTerm2());
			        resultDetails.setTerm3(result.getTerm3());
			    	resultDetail = (Result) session.merge(resultDetails);
			    }
			    if(resultDetail!=null)
			    {
			    	logger.info("Updating Student Results is Completed");
			    }
	        }
	        catch(HibernateException | StudentNotFoundException e)
	        {
	        	logger.error("Error Occured While Updating Student Results");
	        	throw new DatabaseException(e.getMessage());
	        }
	        return resultDetail;
	 }
}
