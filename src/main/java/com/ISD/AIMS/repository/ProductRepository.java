package com.ISD.AIMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISD.AIMS.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{}