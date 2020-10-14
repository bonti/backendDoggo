package com.example.petmanager.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

 
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
 
 
	public List<T> findByIdIn(Collection<ID> ids);

 	public Long countByIdIn(Collection<ID> ids);
 
	
	

}
