package com.iacsd.services;

import static com.iacsd.dtos.EmployeeUserDataBacking.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacsd.daos.IEmployeeDao;
import com.iacsd.daos.IUserDao;
import com.iacsd.dtos.EmployeeUserDataBacking;
import com.iacsd.entities.Employee;
import com.iacsd.entities.User;

@Service
@Transactional
public class UserServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;

	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	public User findUserById(int userId) {
		return userDao.getById(userId);
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);

	}

	// ****************get user by email and password*********************
	public EmployeeUserDataBacking getUserByEmailAndPassword(EmployeeUserDataBacking userData) {
		EmployeeUserDataBacking userCreated = new EmployeeUserDataBacking();
		User validUser = userDao.findByEmail(userData.getEmail());
		if (validUser != null)
			return userCreated = createUser(validUser);
		return null;

	}

	// *********************check if email is unique************
	public Boolean checkIfEmailExists(EmployeeUserDataBacking userData) {
		return userDao.existsByEmail(userData.getEmail());
	}

	public Boolean checkByEmailAndSecurity(EmployeeUserDataBacking useData) {
		User user = userDao.findByEmail(useData.getEmail());
		if (user.getSecurityAnswer().equals(useData.getSecurityAnswer())) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean updatePassword(EmployeeUserDataBacking useData) {
		User user = userDao.findByEmail(useData.getEmail());
		if (user.getSecurityAnswer().equals(useData.getSecurityAnswer())) {
			user.setPassword(useData.getPassword());
			User updatedReturnUser = userDao.save(user);
			if (updatedReturnUser != null)
				return true;

		}
		return false;

	}

}
