package com.example.petmanager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
 
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseEntitiy implements Serializable {

	private static final long serialVersionUID = -3147600187166686513L;
	
 

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 	@Column(name="Id")
	private Long id;
	
	 

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdTimeStamp;

  

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp 
	private Date updatedTimeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
 
 

}
