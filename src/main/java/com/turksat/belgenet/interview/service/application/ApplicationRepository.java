package com.turksat.belgenet.interview.service.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turksat.belgenet.interview.models.ApplicationUser;
import com.turksat.belgenet.interview.models.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long>{
//TODO findBy...
}
