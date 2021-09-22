package com.school.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

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
import com.school.exception.DatabaseException;
import com.school.exception.HeadMasterNotFoundException;
import com.school.repository.HeadMasterRepository;

@Repository
@Transactional
public class HeadMasterRepositoryImpl implements HeadMasterRepository{
	static Logger logger = Logger.getLogger("HeadMasterRepositoryImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	public void checkHeadMaster(Long id) throws HeadMasterNotFoundException {
		logger.debug("In Checking HeadMaster Id...");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM HeadMaster WHERE id=:headMasterId");
		session=null;
		session.beginTransaction();
		query.setParameter("headMasterId", id);
		HeadMaster headMasterDetail = (HeadMaster) query.uniqueResultOptional().orElse(null);
		if (headMasterDetail==null) {
			throw new HeadMasterNotFoundException("HeadMaster Not Found with"+" "+id+"!");
		}	
	}
	
	@Override
	public HeadMaster addHeadMasterDetails(HeadMaster headMasterDetails) throws DatabaseException {
		logger.debug("In Adding HeadMaster Details...");
		Session session=null;
		HeadMaster headMaster =null;
		try
		{
			logger.info("Adding HeadMaster Details...");
			session=sessionFactory.getCurrentSession();
			Long count = (Long) session.save(headMasterDetails);
			if(count>0)
			{
				headMaster = headMasterDetails;
			}
		}
		catch(HibernateException e)
		{
			logger.debug("Error Occured While Saving Details ");
			throw new DatabaseException(e.getMessage());	
		}			
		return headMaster;
	}
	@Override
	public List<HeadMaster> getAllHeadMasterDetails() throws DatabaseException {
		logger.debug("In Retrieving HeadMaster Details...");
		Session session=null;
		List<HeadMaster> headMasterDetailsList=new ArrayList<>();
		try
		{
			logger.info("Retrieving HeadMaster Details...");
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("FROM HeadMaster t");
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
	public HeadMaster updateHeadMasterDetails(Long id, HeadMaster headMasterDetails) throws DatabaseException{
		logger.debug("In Updating HeadMaster Details...");
		Session session=null;
		HeadMaster headMaster = null;
		try {

			logger.info("Updating HeadMaster Details...");
		    boolean status =checkHeadMaster(id);
		    session=sessionFactory.getCurrentSession();
		    session.find(HeadMaster.class, id);
		    	HeadMaster newHeadMasterDetails=session.load(HeadMaster.class, id);
		    	newHeadMasterDetails.setName(headMasterDetails.getName());
		    	newHeadMasterDetails.setDateOfBirth(headMasterDetails.getDateOfBirth());
		    	newHeadMasterDetails.setGender(headMasterDetails.getGender());
		    	newHeadMasterDetails.setQualification(headMasterDetails.getQualification());
		    	newHeadMasterDetails.setEmail(headMasterDetails.getEmail());
		    	newHeadMasterDetails.setContactNo(headMasterDetails.getContactNo());
		    	newHeadMasterDetails.setAddress(headMasterDetails.getAddress());
		    	headMaster = (HeadMaster) session.merge(newHeadMasterDetails);
		    if(headMaster!=null)
			{
				logger.info("Updating HeadMasterDetails is Completed...");
			}
		} catch (HibernateException | HeadMasterNotFoundException e) {

			logger.error("Error Occured While Updating Details,Enter Valid Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMaster;
	}
	@Override
	public String deleteHeadMasterDetails(Long id) throws DatabaseException {
		logger.debug("In Deleting HeadMaster Details...");
		Session session=null;
		String status = null;
		try
		{
			logger.info("Deleting HeadMaster Details...");
			boolean statusMsg =checkHeadMaster(id);
			if(statusMsg)
			{
				session=sessionFactory.getCurrentSession();
				session.find(HeadMaster.class, id);
				HeadMaster headMasterDetails=session.load(HeadMaster.class, id);
				session.delete(headMasterDetails);
				status = "Deleted Successfully";
			}
		}
		catch(HibernateException | HeadMasterNotFoundException e)
		{
			logger.error("Error Occured While Deleting Details,Enter Valid Id");
			throw new DatabaseException(e.getMessage());
		}
		return status;
	}
	@Override
	public HeadMaster getParticularHeadMasterDetails(Long id) throws DatabaseException {
		logger.debug("In Retrieving HeadMaster Details...");
		Session session=null;
		HeadMaster headMasterDetails=new HeadMaster();
		try
		{
			logger.info("Retrieving HeadMaster Details...");
			boolean status=checkHeadMaster(id);
			if(status)
			{
				session=sessionFactory.getCurrentSession();
				Query query=session.createQuery("FROM HeadMaster WHERE id=:headMasterId");
				query.setParameter("headMasterId", id);
				headMasterDetails = (HeadMaster) query.getSingleResult();
			}
			if(headMasterDetails!=null)
			{
				logger.info("Retrieving HeadMaster Details is Completed");
			}
		}
		catch(HibernateException | HeadMasterNotFoundException e)
		{
			logger.error("Error Occured While Retrieving Details,Enter Valid Id");
			throw new DatabaseException(e.getMessage());
		}
		return headMasterDetails;
	}

}
