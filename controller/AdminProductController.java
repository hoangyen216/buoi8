package com.hoangyen.controller;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hoangyen.model.Product;
import com.hoangyen.service.CategoryService;
import com.hoangyen.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController  {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("listProduct", productService.GetAll());
		return "admin/index";
	}   
     
	@GetMapping("/create")
	public String createModelForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listproduct", categoryService.GetAll());
		return "admin/add";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("product") Product newProduct, BindingResult result, Model model,
			@RequestParam("image") MultipartFile image) {

//        if (result.hasErrors()) {
//            model.addAttribute("product", newProduct);
//            
//            return "products/add";
//            
//        }
		if (image != null && image.getSize() > 0) {
			try {
//				File saveFile = new ClassPathResource("static/images").getFile();		
//				System.out.println(new ClassPathResource("static/images").getFile().getAbsolutePath());
//				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
				
				String UPLOAD_FOLDER = "C:\\Users\\hnhye\\eclipse-workspace\\WebBaoMat\\src\\main\\resources\\static\\images";
				Path path = Paths.get(UPLOAD_FOLDER + File.separator + image.getOriginalFilename());
				Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				newProduct.setImage(image.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
			}
  
		}

		productService.add(newProduct);
		return "redirect:/admin/index";
	}

//	@GetMapping("/edit/{id}")
//	public String editBookForm(@PathVariable("id") int id, Model model)
//
//	{
//		Product editProduct = productService.getProductByID(id);
//		if (editProduct != null) {
//			model.addAttribute("product", editProduct);
//			return "products/edit";
//		} else {
//			return "not-found";
//		}
//	}
//
//	@PostMapping("/edit")
//	public String editBook(@ModelAttribute("product") Product updateProduct, @RequestParam("image") MultipartFile image)
//
//	{
//
//		if (image != null && image.getSize() > 0) {
//			try {
////				File saveFile = new ClassPathResource("static/images").getFile();
//				String UPLOAD_FOLDER = "C:/Users/hnhye/eclipse-workspace/Buoi6/src/main/resources/static/images";
////				System.out.println(new ClassPathResource("static/images").getFile().getAbsolutePath());
//
////				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
//				Path path = Paths.get(UPLOAD_FOLDER + File.separator + image.getOriginalFilename());
//
//				Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//				updateProduct.setImage(image.getOriginalFilename());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
////		productService.GetAll().stream().filter(product -> product.getId() == updateProduct.getId()).findFirst()
////				.ifPresent(product -> {
////					productService.update(updateProduct);
////				});
//		productService.update(updateProduct);
//		return "redirect:/products";
//	}
//
//	@PostMapping("/delete/{id}")
//	public String deleteBook(@PathVariable("id") int id) {
//		productService.delete(id);
//		return "redirect:/products";
//	}
}
