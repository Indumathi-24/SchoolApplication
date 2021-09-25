package com.school.repositoryimpl;

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
import com.school.dto.TeacherSubject;
import com.school.entity.SubjectEntity;
import com.school.entity.TeacherEntity;
import com.school.entity.TeacherSubjectEntity;
import com.school.exception.DatabaseException;
import com.school.exception.SubjectNotFoundException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherSubjectRepository;
import com.school.util.TeacherSubjectMapper;

@Repository
@Transactional
public class TeacherSubjectRepositoryImpl implements TeacherSubjectRepository{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long assignTeacherSubject(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws DatabaseException  {
		Session session=null;
		Long id;
		try
		{
			session=sessionFactory.getCurrentSession();
			TeacherSubjectEntity teacherSubjectAssignDetails = TeacherSubjectMapper.mapTeacherSubject(teacherId, subjectCode,teacherSubjectDetails);
			id = (Long) session.save(teacherSubjectAssignDetails);
			if(id!=null)
			{
				
			}
			
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
				
		return id;
	}
	@Override
	public Long updateTeacherSubjectAssign(Long teacherId, String subjectCode,
		TeacherSubject teacherSubjectDetails) throws DatabaseException {
		
		
		Session session=null;
		long countOfUpdation;
		try
		{
			session=sessionFactory.getCurrentSession();
			Query updateByTeacherId=session.createQuery("UPDATE TeacherSubjectEntity SET subjectCode=:code WHERE teacherId=:staffId");
			updateByTeacherId.setParameter("code", subjectCode);
			updateByTeacherId.setParameter("staffId", teacherId);
			countOfUpdation=updateByTeacherId.executeUpdate();
			if(countOfUpdation>0)
			{
			
			}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return countOfUpdation;
	}
	@Override
	public Long deleteTeacherSubjectAssign(Long teacherId, String subjectCode)
			throws DatabaseException {
		Session session=null;
		long count;
		try
		{
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("DELETE FROM TeacherSubject WHERE teacherId=:staffId AND subjectCode=:code");
			query.setParameter("staffId",teacherId );
			query.setParameter("code",subjectCode);
			count=query.executeUpdate();
			if(count>0)
			{
				
			}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

}
