/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnuk.tasks.model;

/**
 *
 * @author Hoang Trung
 */
import java.util.Calendar;

public class Tasks {
	
	private Long id;
	private String description;
	private String complete;
	private Calendar dateOfCompletion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Calendar getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Calendar dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }
}
	
	