package com.hoangyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangyen.model.Product;
import com.hoangyen.model.User;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
