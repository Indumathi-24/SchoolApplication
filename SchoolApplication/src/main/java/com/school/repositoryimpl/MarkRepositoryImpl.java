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

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.entity.SubjectEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.repository.MarkRepository;
import com.school.repository.ResultRepository;
import com.school.repository.SubjectRepository;
import com.school.util.MarkMapper;

@Repository
@Transactional
public class MarkRepositoryImpl implements MarkRepository{
      
	static Logger logger = Logger.getLogger("MarkRepositoryImpl.class");
	@Autowired	
	SessionFactory sessionFactory;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	ResultRepository resultRepository;
	
	
	
	public Long addMark(Long rollNo,Mark mark) throws DatabaseException
	{
		logger.debug("In Adding Mark Details...");
		Session session = null;
		Long markId = 0l;
		try
		{
			logger.info("Adding Mark Details...");
			session = sessionFactory.getCurrentSession();
			MarkEntity markEntity = MarkMapper.mapMark(rollNo, mark);
			markId = (Long) session.save(markEntity);
			if(markId!=null)
			{
				logger.info("Adding Mark Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occurred While Adding Mark Details...");
			throw new DatabaseException(e.getMessage());
		}
		return markId;
	}
	
	public Integer updateMark(String code,Long rollNo,Mark mark) throws DatabaseException, NotFoundException
	{
		logger.debug("In Updating Mark Details...");
		Session session = null;
		int count = 0;
		try
		{
			logger.debug("Updating Mark Details...");
			session = sessionFactory.getCurrentSession();
			SubjectEntity subjectEntity = subjectRepository.getParticularSubject(code);
			System.out.println(subjectEntity.getName());
			String subjectName = subjectEntity.getName();
			System.out.println(subjectName);
			System.out.println(mark.getTamil());
			switch(subjectName)
			{
			case "Tamil":
				{
					System.out.println("Inside switch");
					Query query = session.createQuery("Update MarkEntity m  SET m.tamil=:tamil  where m.studentEntity.rollNo=:rollNo and m.termType=:termType");
					query.setParameter("tamil",mark.getTamil());
					query.setParameter("rollNo", rollNo);
					query.setParameter("termType",mark.getTermType());
					count = query.executeUpdate();
					break;
				}
			case "English":
			{
				System.out.println("Inside switch");
				Query query = session.createQuery("Update MarkEntity m  SET m.english=:english  where m.studentEntity.rollNo=:rollNo and m.termType=:termType");
				query.setParameter("english",mark.getEnglish());
				query.setParameter("rollNo", rollNo);
				query.setParameter("termType",mark.getTermType());
				count = query.executeUpdate();
				break;
			}	
			
			case "Maths":
			{
				System.out.println("Inside switch");
				Query query = session.createQuery("Update MarkEntity m  SET m.maths=:maths  where m.studentEntity.rollNo=:rollNo and m.termType=:termType");
				query.setParameter("maths",mark.getMaths());
				query.setParameter("rollNo", rollNo);
				query.setParameter("termType",mark.getTermType());
				count = query.executeUpdate();
				break;
			}
			
			case "Science":
			{
				System.out.println("Inside switch");
				Query query = session.createQuery("Update MarkEntity m  SET m.science=:science  where m.studentEntity.rollNo=:rollNo and m.termType=:termType");
				query.setParameter("science",mark.getScience());
				query.setParameter("rollNo", rollNo);
				query.setParameter("termType",mark.getTermType());
				count = query.executeUpdate();
				break;
			}
			
			case "SocialScience":
			{
				System.out.println("Inside switch");
				Query query = session.createQuery("Update MarkEntity m  SET m.socialScience=:socialScience  where m.studentEntity.rollNo=:rollNo and m.termType=:termType");
				query.setParameter("socialScience",mark.getSocialScience());
				query.setParameter("rollNo", rollNo);
				query.setParameter("termType",mark.getTermType());
				count = query.executeUpdate();
				break;
			}
				
			default:
				logger.warn("Subject Name is not there!!!!");
			}
			
		}
		catch(HibernateException e)
		{
			logger.error("Error Occurred While Updating Mark Details...");
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}
	
	public List<MarkEntity> getMarks(Long rollNo) throws DatabaseException, NotFoundException
	{
		logger.debug("In Retrieving Mark Details...");
		Session session = null;
		List<MarkEntity> markList =new ArrayList<MarkEntity>();
		try
		{
			logger.info("Retrieving Mark Details...");
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from MarkEntity m  where m.studentEntity.rollNo=:rollNo");
			query.setParameter("rollNo", rollNo);
			markList = query.list();
			if(!markList.isEmpty())
			{
				logger.info("Retrieving Mark Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occurred while Retrieving Mark Details...");
			throw new DatabaseException(e.getMessage());
		}
		return markList;
	}
	
	public List<MarkEntity> getAllMarks() throws DatabaseException
	{
		logger.debug("In Retrieving Mark Details...");
		Session session = null;
		List<MarkEntity> markList =new ArrayList<MarkEntity>();
		try
		{
			logger.info("Retrieving Mark Details...");
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from MarkEntity m");
			markList = query.list();
			if(!markList.isEmpty())
			{
				logger.info("Retrieving Mark Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occurred while Retrieving Mark Details...");
			throw new DatabaseException(e.getMessage());
		}
		return markList;
	}
	
	public Integer updateResult(String result,Long rollNo,String termType) throws DatabaseException
	{
		logger.debug("In Updating Result Details...");
		Session session = null;
		List<MarkEntity> markList =new ArrayList<MarkEntity>();
		Integer count = 0;
		try
		{
			logger.info("Updating Result Details...");
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("Update MarkEntity set result=:result where studentEntity.rollNo=:rollNo and termType=:termType");
			query.setParameter("result", result);
			query.setParameter("rollNo", rollNo);
			query.setParameter("termType", termType);
			count = query.executeUpdate();
			if(count!=null)
			{
				logger.info("Updating Result Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Updating Result Details...");
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}
	public List<MarkEntity> getAllTermMarks(Long rollNo) throws DatabaseException
	{
		logger.debug("In Retrieving All Mark Details...");
		Session session = null;
		List<MarkEntity> markList =new ArrayList<MarkEntity>();
		Long totalMarks=0l;
		String termStatus=null;
		try
		{
			logger.info("Retrieving Mark Details...");
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from MarkEntity where studentEntity.rollNo=:rollNo");
			query.setParameter("rollNo", rollNo);
			markList = query.list();
			for(int i=0;i<markList.size();i++)
			{
				System.out.println("inside loop");
				System.out.println(markList.get(i));
				System.out.println(markList.get(i).getTermType());
				if(markList.get(i).getTermType().equals("I") || markList.get(i).getTermType().equals("II") || markList.get(i).getTermType().equals("III")) {
					System.out.println("inside term if");
					if(markList.get(i).getTamil()!=null && markList.get(i).getEnglish()!=null && markList.get(i).getMaths()!=null && markList.get(i).getScience()!=null && markList.get(i).getSocialScience()!=null)
					{
						System.out.println("inside mark if");
						totalMarks = markList.get(i).getTamil()+markList.get(i).getEnglish()+markList.get(i).getMaths()+markList.get(i).getScience()+markList.get(i).getSocialScience();
						System.out.println(totalMarks);
						if(markList.get(i).getTamil()>=35 && markList.get(i).getEnglish()>=35 && markList.get(i).getMaths()>=35 && markList.get(i).getScience()>=35 && markList.get(i).getSocialScience()>=35)
						{
							termStatus="PASS";
							updateResult(termStatus,rollNo,markList.get(i).getTermType());
						}
						else
						{
							termStatus="FAIL";
							updateResult(termStatus,rollNo,markList.get(i).getTermType());
						}
						resultRepository.updateTermMarks(markList.get(i).getTermType(),totalMarks,termStatus, rollNo);
					}
				}
			}
			
			if(!markList.isEmpty())
			{
				logger.info("Retrieving Mark Details is Completed");
			}
		}
		catch(HibernateException e) {
			logger.error("Error Occurred while Retrieving Mark Details...");
			throw new DatabaseException(e.getMessage());
		}
		return markList;
		}
		
}
