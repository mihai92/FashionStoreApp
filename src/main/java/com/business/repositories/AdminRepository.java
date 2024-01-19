package com.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>
{
public Admin findByadminEmail(String email);
}
