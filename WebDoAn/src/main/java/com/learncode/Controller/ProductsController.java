package com.learncode.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.learncode.Service.CategoryService;
import com.learncode.Service.ProductsService;
import com.learncode.dto.ProductsDTO;
import com.learncode.entity.Category;
import com.learncode.entity.Products;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	ProductsService productsService;


	@Autowired
	CategoryService categoryService;

	
	
	@GetMapping("/list")
	public String list(Model model) {
		return findPaginated(1, model);
		
	}

	@GetMapping("/")
	public String addOrEdit(ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("productlist", null);
		ProductsDTO pr = new ProductsDTO();
		model.addAttribute("PRODUCTSDTO", pr);
		model.addAttribute("ACTION", "/products/saveOrUpdate");
		return "products-register";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("PRODUCTSDTO") ProductsDTO dto) {
		Optional<Products> optionalProducts = productsService.findById(dto.getId());
		Products pro = null;
		String image = "Logo.png";
		Path path = Paths.get("uploads/");
		if (optionalProducts.isPresent()) {
			// save
			if (dto.getImage().isEmpty()) {
				image = optionalProducts.get().getImage();
			} else {
				try {
					InputStream inputStream = dto.getImage().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getImage().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = dto.getImage().getOriginalFilename().toString();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
			
		} else {
			// add
			if (!dto.getImage().isEmpty()) {
				try {
					InputStream inputStream = dto.getImage().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getImage().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = dto.getImage().getOriginalFilename().toString();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		}
		pro = new Products(dto.getId(), dto.getName(), dto.getPrice(), image, dto.getProductsdate(), dto.getQuantity(), dto.getDescription(), dto.getStatus(), new Category(dto.getCategoryid(), "", ""));
		productsService.save(pro);
		//return "redirect:/products/list";
		return "products-register";
	}

	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Long id) {
		Optional<Products> optionalProducts = productsService.findById(id);
		ProductsDTO dto = null;
		if (optionalProducts.isPresent()) {
			Products pro = optionalProducts.get();
			File file = new File("uploads/" + pro.getImage());
			FileInputStream input;
			try {
				input = new FileInputStream(file);
				MultipartFile multiPhoto = new MockMultipartFile("file", file.getName(), "text/plain", org.apache.commons.io.IOUtils.toByteArray(input));
				dto = new ProductsDTO(pro.getId(), pro.getName(), pro.getPrice(), multiPhoto, pro.getProductsdate(), pro.getQuantity(), pro.getDescription(), pro.getStatus(), pro.getCategory().getId());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			model.addAttribute("PRODUCTSDTO", dto);
		} else {
			model.addAttribute("PRODUCTSDTO", new ProductsDTO());
		}
		model.addAttribute("ACTION", "/products/saveOrUpdate");
		return "products-register";
	}

	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Long id) {		
		this.productsService.deleteById(id);
		return "redirect:/products/list";
	}
	
	
	
	@ModelAttribute(name = "CATEGORY")
	public List<Category> getfindCategoryAll() {
		return productsService.findCategoryAll();
	}


	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Products> page = productsService.findPaginated(pageNo, pageSize);
		List<Products> listProducts = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("sortField", sortField);
//		model.addAttribute("sortDir", sortDir);
//		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listProducts", listProducts);
		return "products-view";
	}

}
