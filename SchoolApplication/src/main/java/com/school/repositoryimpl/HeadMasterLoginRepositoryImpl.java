package com.school.repositoryimpl;

import org.apache.log4j.Logger;
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

import com.school.entity.HeadMaster;
import com.school.entity.HeadMasterLogin;
import com.school.exception.DatabaseException;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.ServiceException;
import com.school.repository.HeadMasterLoginRepository;
import com.school.repository.HeadMasterRepository;
@Repository
@Transactional
public class HeadMasterLoginRepositoryImpl implements HeadMasterLoginRepository{
	static Logger logger = Logger.getLogger("HeadMasterLoginRepository.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private HeadMasterRepository headMasterRepository;
	@Override
	public Long createLogin(Long id,HeadMasterLogin loginDetail) throws DatabaseException {
		logger.debug("In Adding HeadMaster Login Details...");
		Session session=null;
		Long headMasterLogin = null;
		try {
			logger.info("Adding HeadMaster Login Details...");
			boolean status=headMasterRepository.checkHeadMaster(id);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				HeadMaster headMaster=new HeadMaster();
				HeadMasterLogin login=new HeadMasterLogin();
				headMaster.setId(id);
				login.setHeadMaster(headMaster);
				login.setPassword(loginDetail.getPassword());
				headMasterLogin=(Long) session.save(login);
				if(headMasterLogin !=null)
				{
					logger.info(" Adding HeadMaster Login Details is Completed...");
				}
			}
		}
		catch(HibernateException | HeadMasterNotFoundException e)
		{
			logger.error("Error Occured while saving headMaster Login Details");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterLogin;

	}
	@Override
	public HeadMasterLogin getLoginDetails(Long id) throws DatabaseException {
		logger.debug("In Retrieving HeadMaster Login Details...");
		HeadMasterLogin headMaster=new HeadMasterLogin();
		Session session=null;
		try {
			logger.info("Retrieving HeadMaster Login Details...");
			boolean status=headMasterRepository.checkHeadMaster(id);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				Query query=session.createQuery("from HeadMasterLogin t where t.headMaster.id=:staffId");
				query.setParameter("staffId", id);
				headMaster=(HeadMasterLogin) query.getSingleResult();
			}
			if(headMaster!=null)
			{
				logger.info("Retrieving HeadMaster Login Details is Completed");
			}
		}
		catch(HibernateException | HeadMasterNotFoundException e)
		{
			logger.error("Error Occured while Retrieving headMaster Login Details,Enter Valid Id");
			throw new DatabaseException(e.getMessage());
		}
			return headMaster;
	}
	@Override
	public Integer updateLoginDetails(Long id,HeadMasterLogin login) throws DatabaseException{
		logger.debug("In Updating HeadMaster Login Details...");
		Session session=null;
		int result=0;
		try {
			logger.info("Updating HeadMaster Login Details...");
			boolean status=headMasterRepository.checkHeadMaster(id);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				Query query=session.createQuery("Update HeadMasterLogin t set t.password=:password where t.headMaster.id=:hMId");
				query.setParameter("hMId", id);
				query.setParameter("password", login.getPassword());
				result=query.executeUpdate();
				if(result==1)
				{
					logger.info("Updating HeadMaster Login Details is Completed");
				}
			}
		   }
			catch(HibernateException | HeadMasterNotFoundException e)
			{
				logger.error("Error Occured while Updating headMaster Login Details,Enter Valid Id");
				throw new DatabaseException(e.getMessage());
			}
	
			return result;
			
	}
	@Override
	public Long getParticularLoginDetails(Long autoId) throws DatabaseException {
		logger.debug("In Retrieving HeadMaster Login Id...");
		Session session=null;
		Long headMaster;
		try {
			logger.info("Retrieving HeadMaster Login Id...");
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("Select t.headMaster.id from HeadMasterLogin t where t.autoId=:staffId");
			query.setParameter("staffId", autoId);
			headMaster=(Long) query.uniqueResult();
			if(headMaster!=null)
			{
				logger.info("Retrieving HeadMaster Login Id is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Retrieving headMaster Login Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMaster;
	}
}
