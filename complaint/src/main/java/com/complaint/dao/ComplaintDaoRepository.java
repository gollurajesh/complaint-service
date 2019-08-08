package com.complaint.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.complaint.model.Complaint;

public interface ComplaintDaoRepository extends MongoRepository<Complaint, Integer>{
	
}
