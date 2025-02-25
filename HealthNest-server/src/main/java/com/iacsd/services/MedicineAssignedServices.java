package com.iacsd.services;
import static com.iacsd.dtos.WardDataBackinBean.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacsd.custom_exception.NoSuchMedicineExistsException;
import com.iacsd.daos.IDoctorDao;
import com.iacsd.daos.IEmployeeDao;
import com.iacsd.daos.IMedicineAssignedDao;
import com.iacsd.daos.IMedicineDao;
import com.iacsd.daos.IUserDao;
import com.iacsd.daos.IWardDao;
import com.iacsd.dtos.DoctorDataBackinBean;
import com.iacsd.dtos.MedicineAssignedDataBackinBean;
import com.iacsd.dtos.WardDataBackinBean;
import com.iacsd.entities.User;
import com.iacsd.entities.Ward;

@Service @Transactional
public class MedicineAssignedServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IWardDao wardDao;
	@Autowired
	IMedicineAssignedDao medicineAssingedDao;
	@Autowired
	IMedicineDao medicineDao;
	
	public void addMedicineToPatient(MedicineAssignedDataBackinBean medicineData) throws NoSuchMedicineExistsException {
		
			medicineAssingedDao.addIntoMedicineAssigned(medicineData.getPatId(), medicineData.getMedicineId(), medicineData.getMedicinePrescription(), medicineData.getMedicineQty());
		
		
		
	}
	
	public void removeMedicineOfPatient(int medicineAssignId) {
		medicineAssingedDao.deleteById(medicineAssignId);
	}
	
	
	
	

}
