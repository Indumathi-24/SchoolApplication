package com.school.repositoryimpl;

import org.apache.log4j.Logger;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.school.dto.HeadMasterLogin;
import com.school.entity.HeadMasterLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.HeadMasterLoginNotFoundException;
import com.school.exception.NotFoundException;
import com.school.repository.HeadMasterLoginRepository;
import com.school.util.HeadMasterLoginMapper;

@Repository
@Transactional
public class HeadMasterLoginRepositoryImpl implements HeadMasterLoginRepository{
	static Logger logger = Logger.getLogger("HeadMasterLoginRepository.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	public void checkHeadMasterLogin(Long id) throws HeadMasterLoginNotFoundException  {
		logger.debug("In Checking HeadMaster Id...");
		Session session = sessionFactory.getCurrentSession();
		Query<HeadMasterLoginEntity> query = session.createQuery("FROM HeadMasterLoginEntity WHERE autoId=:headMasterId");
		query.setParameter("headMasterId", id);
		HeadMasterLoginEntity headMasterLoginDetail = query.uniqueResultOptional().orElse(null);
		if (headMasterLoginDetail==null) {
			throw new HeadMasterLoginNotFoundException("HeadMaster Login Not Found with"+" "+id+"!");
		}	
	}
	
	public Long createLogin(Long id,HeadMasterLogin loginDetail) throws DatabaseException {
		logger.debug("In Adding HeadMaster Login Details...");
		Session session=null;
		Long headMasterLogin = 0l;
		try {
			logger.info("Adding HeadMaster Login Details...");
			session=sessionFactory.getCurrentSession();
			HeadMasterLoginEntity login= HeadMasterLoginMapper.mapHeadMasterLogin(loginDetail, id);
			headMasterLogin=(Long) session.save(login);
			if(headMasterLogin !=null)
			{
				logger.info(" Adding HeadMaster Login Details is Completed...");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while saving headMaster Login Details");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterLogin;

	}
	
	@Override
	public HeadMasterLoginEntity getLoginDetails(Long id) throws DatabaseException {
		logger.debug("In Retrieving HeadMaster Login Details...");
		HeadMasterLoginEntity headMaster=new HeadMasterLoginEntity();
		Session session=null;
		try {
			logger.info("Retrieving HeadMaster Login Details...");
			session=sessionFactory.getCurrentSession();
			Query<HeadMasterLoginEntity> query = session.createQuery("from HeadMasterLoginEntity t where t.headMaster.id=:staffId");
			query.setParameter("staffId", id);
			headMaster = query.getSingleResult();
			if(headMaster!=null)
			{
				logger.info("Retrieving HeadMaster Login Details is Completed");
			}
		}
		catch(HibernateException e)
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
		int count=0;
		try {
			logger.info("Updating HeadMaster Login Details...");
			session=sessionFactory.getCurrentSession();
			Query<HeadMasterLoginEntity> query = session.createQuery("Update HeadMasterLoginEntity t set t.password=:password where t.headMaster.id=:hMId");
			query.setParameter("hMId", id);
			query.setParameter("password", login.getPassword());
			count = query.executeUpdate();
			if(count==1)
			{
				logger.info("Updating HeadMaster Login Details is Completed");
			}
			
		   }
			catch(HibernateException e)
			{
				logger.error("Error Occured while Updating headMaster Login Details,Enter Valid Id");
				throw new DatabaseException(e.getMessage());
			}
	
			return count;
			
	}
	@Override
	public Long getParticularLoginDetails(Long autoId) throws DatabaseException, NotFoundException {
		logger.debug("In Retrieving HeadMaster Login Id...");
		Session session=null;
		Long headMasterId =0l;
		try {
			logger.info("Retrieving HeadMaster Login Id...");
			checkHeadMasterLogin(autoId);
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("Select headMaster.id from HeadMasterLoginEntity  where autoId=:staffId");
			query.setParameter("staffId", autoId);
			headMasterId= (Long) query.uniqueResult();
			if(headMasterId!=null)
			{
				logger.info("Retrieving HeadMaster Login Id is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured while Retrieving headMaster Login Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterId;
	}
}
