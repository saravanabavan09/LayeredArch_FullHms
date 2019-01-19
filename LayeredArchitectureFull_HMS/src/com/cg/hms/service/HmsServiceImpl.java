package com.cg.hms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.hms.dao.HmsDao;
import com.cg.hms.dao.HmsDaoImpl;
import com.cg.hms.exception.HMSException;
import com.cg.hms.model.Patient;

public class HmsServiceImpl implements HmsService {

	List<String> list = new ArrayList<>();
	HmsDao hmsDao = new HmsDaoImpl();

	@Override
	public boolean validateFields(Patient patient) throws HMSException {
		boolean validateFlag = false;
		if (!checkName(patient.getName())) {
			list.add("Name must starts with the Capital letter and length should be between 4-20\n");
		}

		if (!checkGender(patient.getGender())) {
			list.add("gender length should be between 3-6\n");
		}
		if (!checkPhonenumber(patient.getPhoneNumber())) {
			list.add("Phone number should starts with the exactly 10 digits and should start with 6|7|8|9\\n");
		}
		if (!list.isEmpty()) {
			throw new HMSException(list + "");
		} else {
			validateFlag = true;
		}

		return validateFlag;
	}

	public boolean checkName(String name) {

		String nameRegEx = "[A-Z]{1}[A-Za-z\\s]{4,19}$";
		return Pattern.matches(nameRegEx, name);
	}

	public boolean checkGender(String gender) {
		String genderRegEx = "male|female";
		return Pattern.matches(genderRegEx, gender);

	}

	public boolean checkPhonenumber(Long phoneNumber) {
		String phoneNumberRegEx = "[6|7|8|9]{1}[0-9]{9}$";
		return Pattern.matches(phoneNumberRegEx, String.valueOf(phoneNumber));
	}

	@Override
	public int addPatientDetails(Patient patient) throws HMSException {
		return hmsDao.addPatientDetails(patient);
	}

}
