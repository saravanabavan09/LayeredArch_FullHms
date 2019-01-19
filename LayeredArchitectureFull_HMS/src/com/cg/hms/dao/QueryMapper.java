package com.cg.hms.dao;

public interface QueryMapper {
	public static final String insertPatientDetails = "insert into patients_master values(hms_sequence.nextval,?,?,?,sysdate,?)" ;
	public static final String getpatientId = "select hms_sequence.CURRVAL from dual";
}
