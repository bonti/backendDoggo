package com.example.petmanager.repository;

import java.util.List;
import org.springframework.stereotype.Repository; 
import com.example.petmanager.entity.Dog; 

@Repository
public interface DogRepository extends BaseRepository<Dog, Long> {
	public List<Dog> findByUserId(long userId);

}
