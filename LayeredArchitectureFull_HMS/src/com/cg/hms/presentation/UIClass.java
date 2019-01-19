package com.cg.hms.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.cg.hms.exception.HMSException;
import com.cg.hms.model.Patient;
import com.cg.hms.service.HmsService;
import com.cg.hms.service.HmsServiceImpl;

public class UIClass {

	static Logger logger = Logger.getLogger(UIClass.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("resources/log4j.properties");
		Scanner scanner = null;
		int choice = 0;
		boolean choiceFlag = false;
		HmsService service = null;

		do {
			scanner = new Scanner(System.in);
			System.out.println("******** Hospital Management System **********");
			System.out.println("1. Book Appointment");
			System.out.println("2. Get Appointment Details");
			System.out.println("3. View all booked appointments");
			System.out.println("4. Exit");

			System.out.println("Enter your choice");

			try {
				choice = scanner.nextInt();
				choiceFlag = true;
				switch (choice) {
				case 1:
					scanner.nextLine();
					System.out.println("Enter name:");
					String name = scanner.nextLine();
					System.out.println("Enter the gender:");
					String gender = scanner.nextLine();
					System.out.println("Enter phone number:");
					long PhoneNo = scanner.nextLong();
					scanner.nextLine();
					System.out.println("Enter the problem:");
					String problem = scanner.nextLine();

					Patient patient = new Patient();
					patient.setName(name);
					patient.setGender(gender);
					patient.setPhoneNumber(PhoneNo);
					patient.setProblem(problem);
					service = new HmsServiceImpl();

					try {
						boolean validateFlag = service.validateFields(patient);
						if(validateFlag) {
							int id = service.addPatientDetails(patient);
							System.out.println("successfully completed"+id);
						}
						else
						{
						System.out.println("error");
						}
						
					} catch (HMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				default:
					choiceFlag = false;
					System.out.println("input should be in the range of (1-4)");
					System.out.println("Enter your input again");
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Enter only digits");
				System.out.println("Enter you input again");
			}
		} while (!choiceFlag);

		scanner.close();

	}
}
