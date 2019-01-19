package com.cg.hms.Dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.hms.dao.HmsDao;
import com.cg.hms.dao.HmsDaoImpl;
import com.cg.hms.exception.HMSException;
import com.cg.hms.model.Patient;

public class HmsDaoTest {
	HmsDao dao = null;

	@Before
	public void setUp() throws Exception {
		dao = new HmsDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void test() {
		Patient patient = new Patient();
		patient.setName("Saravanan");
		patient.setGender("male");
		patient.setPhoneNumber(9043174050l);
		patient.setProblem("sinus");

		try {
			int id = dao.addPatientDetails(patient);
			assertNull(id);

		} catch (HMSException e) {
			e.printStackTrace();
		}
	}

	public void testaddpatientdetails() {
		Patient patient = new Patient();
		patient.setName("Saravanan");
		patient.setGender("male");
		patient.setPhoneNumber(9043174050l);
		patient.setProblem("sinus");

		try {
			int id = dao.addPatientDetails(patient);
			assertEquals(102, id);

		} catch (HMSException e) {
			e.printStackTrace();

		}
	}
}
