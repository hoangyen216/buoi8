package com.hoangyen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangyen.model.Category;
import com.hoangyen.model.Product;
import com.hoangyen.repository.CategoryRepository;
import com.hoangyen.repository.ProductRepository;
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository ;
	public  List<Category> GetAll(){
		return categoryRepository.findAll();	}
}
