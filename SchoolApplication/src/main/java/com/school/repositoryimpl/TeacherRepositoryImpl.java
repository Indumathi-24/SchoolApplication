package com.school.repositoryimpl;

import java.util.ArrayList;


import java.util.List;

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

import com.school.entity.Teacher;
import com.school.exception.DatabaseException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherRepository;
@Repository
@Transactional
public class TeacherRepositoryImpl implements TeacherRepository{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Long addTeacherDetails(Teacher teacherDetails) {
		Session session=null;
		Long teacherId = null;
		try
		{
			session=sessionFactory.getCurrentSession();
			teacherId = (Long) session.save(teacherDetails);
			if(teacherId!=null)
			{
				
			}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage()); 
		}	
		return teacherId;
	}
	@Override
	public List<Teacher> getAllTeacherDetails() {
		Session session=null;
		List<Teacher> teacherDetailsList=new ArrayList<>();
		try
		{
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("FROM Teacher t");
			teacherDetailsList=query.list();
			if(!teacherDetailsList.isEmpty())
			{
				
			}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return teacherDetailsList;
	}
	public boolean checkTeacher(Long id) throws TeacherNotFoundException {
		boolean status=false;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Teacher WHERE id=:teacherId");
		query.setParameter("teacherId", id);
		Teacher teacher = (Teacher) query.uniqueResultOptional().orElse(null);
		status=(teacher!=null);
		if (!status) {
			throw new TeacherNotFoundException("Teacher Not Found with"+" "+id+"!");
		}
		return status;
	}
	@Override
	public Teacher updateTeacherDetails(Long id, Teacher teacherDetails) throws DatabaseException {
		Teacher teacher=null;
		Session session=null;
		try
		{
			boolean checkTeacher=checkTeacher(id);
			if(checkTeacher)
			{
				session=sessionFactory.getCurrentSession();
				session.find(Teacher.class, id);
				Teacher newTeacherDetails=session.load(Teacher.class, id);
				newTeacherDetails.setName(teacherDetails.getName());
				newTeacherDetails.setDateOfBirth(teacherDetails.getDateOfBirth());
				newTeacherDetails.setGender(teacherDetails.getGender());
				newTeacherDetails.setQualification(teacherDetails.getQualification());
				newTeacherDetails.setEmail(teacherDetails.getEmail());
				newTeacherDetails.setContactNo(teacherDetails.getContactNo());
				newTeacherDetails.setAddress(teacherDetails.getAddress());
				teacher = (Teacher) session.merge(newTeacherDetails);
			}
		}
		catch(HibernateException | TeacherNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return teacher;
	}
	@Override
	public Teacher deleteTeacherDetails(Long id) throws DatabaseException  {
		Teacher teacher = null;
		Session session=null;
		try
		{
			boolean checkTeacher=checkTeacher(id);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+id+"!");
			}
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.find(Teacher.class, id);
			Teacher teacherDetails=session.load(Teacher.class, id);
			session.delete(teacherDetails);
			teacher = session.load(Teacher.class, id);
			if(teacher == null)
			{
				
			}
		    
		}
		catch(HibernateException | TeacherNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}		
		return teacher;
	}
	@Override
	public Teacher getParticularTeacherDetails(Long id) throws DatabaseException  {
		Session session=null;
		Teacher teacherDetails=new Teacher();
		try
		{
			boolean checkTeacher=checkTeacher(id);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+id+"!");
			}
			session=sessionFactory.openSession();
			Query query=session.createQuery("FROM Teacher WHERE id=:teacherId");
			query.setParameter("teacherId", id);
			teacherDetails=(Teacher) query.getSingleResult();
		}
		catch(HibernateException | TeacherNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}	
		return teacherDetails;
	}

}
