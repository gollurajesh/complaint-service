package com.complaint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.complaint.model.Complaint;
import com.complaint.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	
	@GetMapping("/")
    public String index() {
        return "Spring Boot Complaint Example";
    }
	
	@PostMapping( path="/saveComplaint" , consumes="application/json", produces="application/json")
	public @ResponseBody Complaint saveComplaint(@RequestBody Complaint complaint) {
		return complaintService.saveComplaint(complaint);
	}

	@GetMapping(path = "/getAllComplaint", produces="application/json")
	public @ResponseBody List<Complaint> getAllComplaint() {
		return complaintService.getAllComplaint();
	}

	@PostMapping(path="/getComplaint", consumes="application/json", produces="application/json")
	public @ResponseBody Complaint getComplaintbyId(@RequestBody int empId) {
		return complaintService.getComplaintbyId(empId);
	}
	
	@GetMapping(path = "/test")
	public @ResponseBody String test() {
		return complaintService.test();
	}
}
