package com.school.serviceimpl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;
import com.school.repository.ClassRepository;
import com.school.repository.SubjectRepository;
import com.school.service.SubjectService;

@Service
public class SubjectServicImpl implements SubjectService{

	static Logger logger = Logger.getLogger("SubjectServiceImpl.class");
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Override
	public String addSubject(Long roomNo,Subject subject) throws ServiceException, NotFoundException 
	{
		logger.debug("In Adding Subject Details...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return subjectRepository.addSubject(roomNo,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public 	List<SubjectEntity> getAllSubject(Long roomNo) throws  ServiceException, NotFoundException
	{
		logger.debug("In Retrieving All Subject Details...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return subjectRepository.getAllSubject(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public 	SubjectEntity getParticularSubject(Long roomNo,String code) throws ServiceException, NotFoundException 
	{
		logger.debug("In Retrieving Subject Details...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return subjectRepository.getParticularSubject(roomNo,code);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public SubjectEntity updateSubject(Long roomNo,String code,Subject subject) throws ServiceException, NotFoundException
	{
		logger.debug("In Updating Subject Details...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return subjectRepository.updateSubject(roomNo, code, subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public SubjectEntity deleteSubject(Long roomNo, String code) throws ServiceException, NotFoundException 
	{
		logger.debug("In Deleting Subject Details...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return subjectRepository.deleteSubject(roomNo, code);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
