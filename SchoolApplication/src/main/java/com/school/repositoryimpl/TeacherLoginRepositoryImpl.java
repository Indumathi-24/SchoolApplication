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
import com.school.entity.Teacher;
import com.school.entity.TeacherLogin;
import com.school.exception.DatabaseException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherLoginRepository;
import com.school.repository.TeacherRepository;

@Repository
@Transactional
public class TeacherLoginRepositoryImpl implements TeacherLoginRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TeacherRepository teacherRepository;
	@Override
	public ResponseEntity<String> createLogin(Long id,TeacherLogin login) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.openSession();
			session.beginTransaction();
			Teacher teacher=new Teacher();
			TeacherLogin teachLogin=new TeacherLogin();
			
			teacher.setId(id);
			teachLogin.setUserId(teacher);
			teachLogin.setPassword(login.getPassword());
			
			session.save(teachLogin);
			session.getTransaction().commit();
			session.close();
			response=new ResponseEntity<String>("Login Details created Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
			
		return response;

	}
	@Override
	public ResponseEntity<?> getLoginDetails(Long id) throws DatabaseException {
		TeacherLogin teacher=new TeacherLogin();
		Session session=null;
		ResponseEntity<?> response=null;
		boolean status=teacherRepository.checkTeacher(id);
		try {
			if(!status)
			{
				throw new TeacherNotFoundException("Teacher Not Found,Enter valid Id");
			}
			else
			{
				session=sessionFactory.openSession();
				Query query=session.createQuery("from TeacherLogin t where t.userid.id=:staffId");
				query.setParameter("staffId", id);
				teacher=(TeacherLogin) query.getSingleResult();	
				session.close();
				response=new ResponseEntity<>(teacher,new HttpHeaders(),HttpStatus.OK);
			}
		  }
		 catch(HibernateException | TeacherNotFoundException e)
		{
			 throw new DatabaseException(e.getMessage());
		}
			return response;
	}
	@Override
	public ResponseEntity<String> updateLoginDetails(Long id, TeacherLogin login) throws DatabaseException  {
		Session session=null;
		ResponseEntity<String> response=null;
		boolean status=teacherRepository.checkTeacher(id);
		try {
			if(!status)
			{
				throw new TeacherNotFoundException("Teacher Not Found,Enter valid Id");
			}
			else
			{
				session=sessionFactory.openSession();
				session.beginTransaction();
			    //session.find(TeacherLogin.class,id);
			    //TeacherLogin teachLogin=session.load(TeacherLogin.class,id);
			    //teachLogin.setPassword(login.getPassword());
			    //session.merge(teachLogin);
			    //session.flush();
				Query query=session.createQuery("Update TeacherLogin t set t.password=:password where t.userId.id=:staffId");
				query.setParameter("staffId", id);
				query.setParameter("password", login.getPassword());
				int result=query.executeUpdate();
				session.getTransaction().commit();
				session.close();
				response=new ResponseEntity<String>(result+"Login Details updated Successfully!",new HttpHeaders(),HttpStatus.OK);
			  }
		    }
			catch(HibernateException | TeacherNotFoundException e)
			{
				throw new DatabaseException(e.getMessage());
			}
			return response;
			
	}
	

	
}
