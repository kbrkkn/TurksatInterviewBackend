package com.turksat.belgenet.interview.service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turksat.belgenet.interview.models.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long>{

	ApplicationUser findByUserName(String username);
}
