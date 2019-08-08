package com.complaint.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.complaint.model.Complaint;


@Component
public class ComplaintModelListener extends AbstractMongoEventListener<Complaint> {

    private SequenceGenerator sequenceGenerator;

    @Autowired
    public ComplaintModelListener(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Complaint> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Complaint.SEQUENCE_NAME));
        }
    }


}