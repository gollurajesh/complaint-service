package com.complaint.service;

import java.util.List;

import com.complaint.model.Complaint;

public interface ComplaintService {

	public Complaint saveComplaint(Complaint complaint);
	
	public List<Complaint> getAllComplaint();
	
	public Complaint getComplaintbyId(int comId);
	
	public String test();
}
