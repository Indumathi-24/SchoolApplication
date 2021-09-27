package com.school.repositoryimpl;

import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.school.dto.Result;
import com.school.entity.ClassEntity;
import com.school.entity.ResultEntity;
import com.school.entity.StudentEntity;
import com.school.exception.DatabaseException;
import com.school.repository.ResultRepository;
import com.school.util.ResultMapper;

@Repository
@Transactional
public class ResultRepositoryImpl implements ResultRepository {
  
	static Logger logger = Logger.getLogger("ResultRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Long addResult(Long rollNo,Result result) throws DatabaseException
	{
		logger.debug("In Adding Student's Result...");
		Session session = null;
		Long resultId = 0l;
        try
        {
        	logger.info("Adding Student Results");
			session=sessionFactory.getCurrentSession();
			ResultEntity resultDetail = ResultMapper.mapResult(result, rollNo);
			resultId = (Long) session.save(resultDetail);
			if(resultId!=null) {
				logger.info("Adding Student Results is Completed");
			}
         }
			catch(HibernateException e)
			{
				logger.error("Error Occured While Saving Student Results");
				throw new DatabaseException(e.getMessage());
			}
           return resultId;
		}
	
	 public ResultEntity getResult(Long rollNo) throws DatabaseException
	 {
		    logger.debug("In Retrieving Student Results...");
	        ResultEntity result=new ResultEntity();
		    Session session=null;
	        try
	        {
	        	logger.info("Retrieving Student Results");
			    session=sessionFactory.getCurrentSession();
				Query<ResultEntity> query =session.createQuery("from ResultEntity r where r.student.rollNo=:rollNo");
				query.setParameter("rollNo", rollNo);
				result= query.getSingleResult();
			    if(result!=null)
			    {
			    	logger.info("Retrieving Student Results is Completed");
			    }
	        }
			catch(HibernateException e)
			{
				logger.error("Error Occured While Retrieving Student Results");
				throw new DatabaseException(e.getMessage());
			}
	        return result;
	 }
	 
	 public ResultEntity updateResult(Long rollNo,Long resultId,Result result) throws DatabaseException
	 {
		    logger.debug("In Updating Student Results...");
		    ResultEntity resultDetail = new ResultEntity();
		    Session session=null;
	        try
	        {
	        	logger.info("Updating Student Results");
			    session=sessionFactory.getCurrentSession();
			    ResultEntity resultEntity = ResultMapper.mapResult(result, rollNo);
			    session.find(ResultEntity.class, resultId);
			    ResultEntity resultDetails = session.load(ResultEntity.class, resultId);
			    resultDetails.setTerm1(resultEntity.getTerm1());
			    resultDetails.setTerm2(resultEntity.getTerm2());
			    resultDetails.setTerm3(resultEntity.getTerm3());
			    resultDetail = (ResultEntity) session.merge(resultDetails);
			    if(resultDetail!=null)
			    {
			    	logger.info("Updating Student Results is Completed");
			    }
	        }
	        catch(HibernateException e)
	        {
	        	logger.error("Error Occured While Updating Student Results");
	        	throw new DatabaseException(e.getMessage());
	        }
	        return resultDetail;
	 }
	 
	 public List<ResultEntity> getResultByClass(Long roomNo) throws DatabaseException
	 {
		    logger.debug("In Retrieving Student Results By Class");
	        List<ResultEntity> resultList=new ArrayList<ResultEntity>();
		    Session session=null;
	        try
	        {
	        	logger.info("Retrieving Student Results By Class");
	        	session=sessionFactory.getCurrentSession();
 			   // Query query=session.createNativeQuery("Select p from Parent p join fetch p.rollNo where p.rollNo.rollNo=:rollNo");
	        	Query query = session.createSQLQuery("Select r.rollNo,r.term1,r.term2,r.term3,r.result,s.roomNo from Result r join Student s on s.rollNo=r.rollNo where roomNo=:roomNo");
 		   		query.setParameter("roomNo",roomNo);
 		   		List<Object[]> resultDetail =  query.list();
 		   		for(Object[] data:resultDetail)
 		   		{
 		   			ResultEntity result = new ResultEntity();
 		   			StudentEntity student = new StudentEntity();
 		   			ClassEntity classEntity = new ClassEntity();
 		   			classEntity.setRoomNo(Long.parseLong(data[5].toString()));
 		   			student.setRollNo(Long.parseLong(data[0].toString()));
 		   			student.setClassEntity(classEntity);
 		   		    result.setStudent(student);
 		   		    result.setTerm1(Long.parseLong(data[1].toString()));
 		   		    result.setTerm2(Long.parseLong(data[2].toString()));
 		   		    result.setTerm3(Long.parseLong(data[3].toString()));
 		   		    result.setResult(data[4].toString());
 		   			resultList.add(result);
 		   		}
 		    if(!resultList.isEmpty())
 		    {
 		    	logger.info("Retrieving  Student Results By Class is Completed");
 		    }
	      }
	        catch(HibernateException e)
	        {
	        	throw new DatabaseException(e.getMessage());
	        }
	    return resultList;
	 }
}
