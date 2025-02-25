package com.iacsd.services;
import static com.iacsd.dtos.MedicineAssignedDataBackinBean.*;
import static com.iacsd.dtos.WardDataBackinBean.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacsd.daos.IDoctorDao;
import com.iacsd.daos.IEmployeeDao;
import com.iacsd.daos.IMedicineAssignedDao;
import com.iacsd.daos.IMedicineDao;
import com.iacsd.daos.IUserDao;
import com.iacsd.daos.IWardDao;
import com.iacsd.dtos.DoctorDataBackinBean;
import com.iacsd.dtos.MedicineAssignedDataBackinBean;
import com.iacsd.dtos.WardDataBackinBean;
import com.iacsd.entities.Medicine;
import com.iacsd.entities.User;
import com.iacsd.entities.Ward;

@Service @Transactional
public class MedicineServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IWardDao wardDao;
	@Autowired
	IMedicineDao medicineDao;
	@Autowired
	IMedicineAssignedDao medicineAssingedDao;
	
	public List<MedicineAssignedDataBackinBean> getAllMedicines(){
		List<Medicine> medicine=medicineDao.findAll();
		List<MedicineAssignedDataBackinBean> medicinesTosend=createAllMedicineList(medicine);
		return medicinesTosend;
		
	}

	public int addMedicine(MedicineAssignedDataBackinBean medicineData) {
		return  medicineDao.insertIntoMedicineTable(0, medicineData.getMedicineName(), medicineData.getMedicinePrice());
		
	}

	public void removeMedicine(int medicineId) {
		medicineDao.deleteById(medicineId);
		
	}
	
	
	
	
	

}
