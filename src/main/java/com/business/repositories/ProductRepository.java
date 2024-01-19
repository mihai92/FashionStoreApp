package com.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	public Product findByPname(String name);

}
