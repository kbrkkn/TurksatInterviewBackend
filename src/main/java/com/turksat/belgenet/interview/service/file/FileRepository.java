package com.turksat.belgenet.interview.service.file;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turksat.belgenet.interview.models.ApplicationUser;
import com.turksat.belgenet.interview.models.File;

@Repository
public interface FileRepository extends JpaRepository<File,Long>{

	Optional<File> findByFileLink(String fileLink);

	
	
}
