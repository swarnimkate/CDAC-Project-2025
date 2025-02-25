package com.iacsd.services;
import static com.iacsd.dtos.DoctorDataBackinBean.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacsd.custom_exception.NoSuchPatientFoundException;
import com.iacsd.daos.IDoctorDao;
import com.iacsd.daos.IEmployeeDao;
import com.iacsd.daos.IPatientDao;
import com.iacsd.daos.IUserDao;
import com.iacsd.dtos.DoctorDataBackinBean;
import com.iacsd.dtos.PatientDataBacking;
import com.iacsd.entities.Doctor;
import com.iacsd.entities.User;

@Service
@Transactional
public class DoctorServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IDoctorDao doctorDao;
	@Autowired
	IPatientDao patientDao;
	
	public List<DoctorDataBackinBean> getAllDoctors() {
		List<Doctor> doctors=doctorDao.findAll();
		List<DoctorDataBackinBean> doctorDetail=createDoctorsList(doctors);
		
		return doctorDetail;
		
	}

	public void updatePatientDetails(PatientDataBacking patientData) throws NoSuchPatientFoundException  {
		int updateCount;
		if(patientDao.existsById(patientData.getPatId()))
		 updateCount=patientDao.updatePatientPrescription(patientData.getPrescription(),patientData.getPatId());
		else
		throw new NoSuchPatientFoundException("patient  with id "+patientData.getPatId()+" does not exists");
	}
	
	

}
