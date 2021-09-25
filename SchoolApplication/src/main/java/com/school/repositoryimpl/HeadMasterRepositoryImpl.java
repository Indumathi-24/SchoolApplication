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

import com.school.dto.HeadMaster;
import com.school.entity.HeadMasterEntity;
import com.school.exception.DatabaseException;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.NotFoundException;
import com.school.repository.HeadMasterRepository;
import com.school.util.HeadMasterMapper;

@Repository
@Transactional
public class HeadMasterRepositoryImpl implements HeadMasterRepository{
	static Logger logger = Logger.getLogger("HeadMasterRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	public void checkHeadMaster(Long id) throws HeadMasterNotFoundException {
		logger.debug("In Checking HeadMaster Id...");
		Session session = sessionFactory.getCurrentSession();
		Query<HeadMasterEntity> query = session.createQuery("FROM HeadMasterEntity WHERE id=:headMasterId");
		query.setParameter("headMasterId", id);
		HeadMasterEntity headMasterDetail = query.uniqueResultOptional().orElse(null);
		if (headMasterDetail==null) {
			throw new HeadMasterNotFoundException("HeadMaster Not Found with"+" "+id+"!");
		}	
	}
	
	@Override
	public Long addHeadMasterDetails(HeadMaster headMaster) throws DatabaseException {
		logger.debug("In Adding HeadMaster Details...");
		Session session=null;
		Long headMasterId =null;
		try
		{
			logger.info("Adding HeadMaster Details...");
			session=sessionFactory.getCurrentSession();
			HeadMasterEntity headMasterEntity = HeadMasterMapper.mapHeadMaster(headMaster);
			headMasterId = (Long) session.save(headMasterEntity);
			if(headMasterId!=null)
			{
				logger.info("Adding HeadMaster Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.debug("Error Occured While Saving Details ");
			throw new DatabaseException(e.getMessage());	
		}			
		return headMasterId;
	}
	
	@Override
	public List<HeadMasterEntity> getAllHeadMasterDetails() throws DatabaseException {
		logger.debug("In Retrieving HeadMaster Details...");
		Session session=null;
		List<HeadMasterEntity> headMasterDetailsList=new ArrayList<>();
		try
		{
			logger.info("Retrieving HeadMaster Details...");
			session=sessionFactory.getCurrentSession();
			Query<HeadMasterEntity> query=session.createQuery("FROM HeadMasterEntity t");
			headMasterDetailsList=query.list();
			if(!headMasterDetailsList.isEmpty())
			{
				logger.info("Retrieving HeadMaster Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured While Retrieving Details");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterDetailsList;
	}
	
	@Override
	public HeadMasterEntity updateHeadMasterDetails(Long id, HeadMaster headMasterDetails) throws DatabaseException, NotFoundException {
		logger.debug("In Updating HeadMaster Details...");
		Session session=null;
		HeadMasterEntity headMaster = null;
		try {

			logger.info("Updating HeadMaster Details...");
		    checkHeadMaster(id);
		    session=sessionFactory.getCurrentSession();
		    HeadMasterEntity headMasterEntity = HeadMasterMapper.mapHeadMaster(headMasterDetails);
		    session.find(HeadMasterEntity.class, id);
		    HeadMasterEntity newHeadMasterDetails=session.load(HeadMasterEntity.class, id);
		    newHeadMasterDetails.setName(headMasterEntity.getName());
		    newHeadMasterDetails.setDateOfBirth(headMasterEntity.getDateOfBirth());
		    newHeadMasterDetails.setGender(headMasterEntity.getGender());
		    newHeadMasterDetails.setQualification(headMasterEntity.getQualification());
		    newHeadMasterDetails.setEmail(headMasterEntity.getEmail());
		    newHeadMasterDetails.setContactNo(headMasterEntity.getContactNo());
		    newHeadMasterDetails.setAddress(headMasterEntity.getAddress());
		    headMaster = (HeadMasterEntity) session.merge(newHeadMasterDetails);
		    if(headMaster!=null)
			{
				logger.info("Updating HeadMasterDetails is Completed...");
			}
		} catch (HibernateException e) {

			logger.error("Error Occured While Updating Details,Enter Valid Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMaster;
	}
	
	@Override
	public HeadMasterEntity deleteHeadMasterDetails(Long id) throws DatabaseException, NotFoundException {
		logger.debug("In Deleting HeadMaster Details...");
		Session session=null;
		HeadMasterEntity headMasterEntity =null;
		try
		{
			logger.info("Deleting HeadMaster Details...");
			checkHeadMaster(id);
			session=sessionFactory.getCurrentSession();
			session.find(HeadMasterEntity.class, id);
			HeadMasterEntity headMasterDetails=session.load(HeadMasterEntity.class, id);
			session.delete(headMasterDetails);
			headMasterEntity = session.load(HeadMasterEntity.class, id);
			if(headMasterEntity==null)
			{
				logger.info("Deleting HeadMaster Details is Completed");
			}
			
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured While Deleting Details,Enter Valid Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterEntity;
	}
	
	@Override
	public HeadMasterEntity getParticularHeadMasterDetails(Long id) throws DatabaseException, NotFoundException {
		logger.debug("In Retrieving HeadMaster Details...");
		Session session=null;
		HeadMasterEntity headMasterDetails=new HeadMasterEntity();
		try
		{
			logger.info("Retrieving HeadMaster Details...");
			checkHeadMaster(id);
			session=sessionFactory.getCurrentSession();
			Query<HeadMasterEntity> query=session.createQuery("FROM HeadMasterEntity WHERE id=:headMasterId");
			query.setParameter("headMasterId", id);
			headMasterDetails =  query.getSingleResult();
			if(headMasterDetails!=null)
			{
				logger.info("Retrieving HeadMaster Details is Completed");
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error Occured While Retrieving Details,Enter Valid Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterDetails;
	}

}
