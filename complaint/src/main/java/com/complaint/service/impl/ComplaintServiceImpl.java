package com.complaint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complaint.dao.ComplaintDaoRepository;
import com.complaint.model.Complaint;
import com.complaint.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService{

	@Autowired
	private ComplaintDaoRepository complaintDaoRepository;
	
	@Override
	public Complaint saveComplaint(Complaint complaint) {
		return complaintDaoRepository.save(complaint);
	}

	@Override
	public List<Complaint> getAllComplaint() {
		return complaintDaoRepository.findAll();
	}

	@Override
	public Complaint getComplaintbyId(int comId) {
		return complaintDaoRepository.findById(comId).get();
	}

	@Override
	public String test() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "test";
	}

}
