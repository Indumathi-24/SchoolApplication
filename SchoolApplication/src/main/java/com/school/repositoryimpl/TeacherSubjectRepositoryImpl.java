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

import com.school.entity.Subject;
import com.school.entity.Teacher;
import com.school.entity.TeacherSubject;
import com.school.exception.DatabaseException;
import com.school.exception.SubjectNotFoundException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherSubjectRepository;
@Repository
public class TeacherSubjectRepositoryImpl implements TeacherSubjectRepository{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private TeacherRepositoryImpl teacherRepositoryImpl;
	@Autowired
	private SubjectRepositoryImpl subjectRepositoryImpl;
	@Override
	public ResponseEntity<String> assignTeacherSubject(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws DatabaseException  {
		ResponseEntity<String> response=null;
		Session session=null;
		try
		{
			boolean checkTeacher=teacherRepositoryImpl.checkTeacher(teacherId);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+teacherId+"!");
			}
			boolean checkSubject=subjectRepositoryImpl.checkSubjectCode(subjectCode);
			if(!checkSubject)
			{
				throw new SubjectNotFoundException("Subject Not Found With"+" "+subjectCode+"!");
			}
			session=sessionFactory.openSession();
			session.beginTransaction();
			Teacher teacherDetails=new Teacher();
			teacherDetails.setId(teacherId);
			Subject subjectDetails=new Subject();
			subjectDetails.setCode(subjectCode);
			TeacherSubject teacherSubjectAssignDetails=new TeacherSubject();
			teacherSubjectAssignDetails.setTeacher(teacherDetails);
			teacherSubjectAssignDetails.setSubject(subjectDetails);
			session.save(teacherSubjectAssignDetails);
			session.getTransaction().commit();
			session.close();
			response=new ResponseEntity<String>("Teacher is assigned for course successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException |TeacherNotFoundException| SubjectNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
				
		return response;
	}
	@Override
	public ResponseEntity<String> updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws DatabaseException {
		ResponseEntity<String> response=null;
		Session session=null;
		try
		{
			boolean checkTeacher=teacherRepositoryImpl.checkTeacher(teacherId);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+teacherId+"!");
			}
			boolean checkSubject=subjectRepositoryImpl.checkSubjectCode(subjectCode);
			if(!checkSubject)
			{
				throw new SubjectNotFoundException("Subject Not Found With"+" "+subjectCode+"!");
			}
			session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query updateByTeacherId=session.createQuery("UPDATE TeacherSubject SET subjectCode=:code WHERE teacherId=:staffId");
			updateByTeacherId.setParameter("code", subjectCode);
			updateByTeacherId.setParameter("staffId", teacherId);
			long countOfUpdationById=updateByTeacherId.executeUpdate();
			session.getTransaction().commit();
			if(countOfUpdationById>0)
			{
				response=new ResponseEntity<String>("Details Updated Successfully!",new HttpHeaders(),HttpStatus.OK);
			}
		}
		catch(HibernateException |SubjectNotFoundException |TeacherNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return response;
	}
	@Override
	public ResponseEntity<String> deleteTeacherSubjectAssign(Long teacherId, String subjectCode)
			throws DatabaseException {
		ResponseEntity<String> response=null;
		Session session=null;
		try
		{
			boolean checkTeacher=teacherRepositoryImpl.checkTeacher(teacherId);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+teacherId+"!");
			}
			boolean checkSubject=subjectRepositoryImpl.checkSubjectCode(subjectCode);
			if(!checkSubject)
			{
				throw new SubjectNotFoundException("Subject Not Found With"+" "+subjectCode+"!");
			}
			session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query=session.createQuery("DELETE FROM TeacherSubject WHERE teacherId=:staffId AND subjectCode=:code");
			query.setParameter("staffId",teacherId );
			query.setParameter("code",subjectCode);
			long count=query.executeUpdate();
			session.getTransaction().commit();
			if(count>0)
			{
				response=new ResponseEntity<String>("Details Deleted Successfully!",new HttpHeaders(),HttpStatus.OK);
			}
		}
		catch(HibernateException |SubjectNotFoundException |TeacherNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return response;
	}

}
