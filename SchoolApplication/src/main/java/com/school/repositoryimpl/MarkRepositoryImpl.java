package com.school.repositoryimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.dto.Mark;
import com.school.entity.SubjectEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.repository.MarkRepository;
import com.school.repository.SubjectRepository;

@Repository
@Transactional
public class MarkRepositoryImpl implements MarkRepository{
      
	@Autowired	
	SessionFactory sessionFactory;
	
	@Autowired
	
	SubjectRepository subjectRepository;
	
	public 	Integer updateMark(String code,Long rollNo,Mark mark) throws DatabaseException, NotFoundException
	{
		Session session = null;
		int count = 0;
		try
		{
			session = sessionFactory.getCurrentSession();
			SubjectEntity subjectEntity = subjectRepository.getParticularSubject(code);
			String subjectName = subjectEntity.getName();
			switch(subjectName)
			{
			case "Tamil":
				{
					Query query = session.createQuery("Update SET m.tamil=:tamil,m.termType=:termType MarKEntity m where m.student.rollNo=:rollNo");
					query.setParameter("tamil",mark.getTamil());
					query.setParameter("termType",mark.getTermType());
					query.setParameter("rollNo", rollNo);
					count = query.executeUpdate();
				}
				
			}
			
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}
}
