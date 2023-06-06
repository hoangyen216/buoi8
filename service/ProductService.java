package com.hoangyen.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangyen.model.Product;
import com.hoangyen.repository.ProductRepository;


@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository ;
	public  List<Product> GetAll(){
		return productRepository.findAll();	}
	public void add(Product newProduct) {
		productRepository.save(newProduct);
	}
}
