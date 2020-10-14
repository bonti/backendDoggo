package com.example.petmanager.repository;
 
 

import org.springframework.stereotype.Repository;

import com.example.petmanager.repository.BaseRepository;
import com.example.petmanager.entity.User;;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{ 
	
	public User findByUsernameAndPassword(String username, String password);
}

  