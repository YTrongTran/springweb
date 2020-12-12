package com.learncode.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.learncode.Service.CategoryService;
import com.learncode.entity.Category;


@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	//lấy tất cả
	@RequestMapping("/")
//	@PreAuthorize("hasPermission('', 'danhsachcategory')")
	public String viewHomePage(Model model) {
		return findPaginated(1, model);
	}
	
	
	@RequestMapping("/new")
//	@PreAuthorize("hasPermission('', 'themcategory')")
	public String showNewCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category-register";
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute("category")Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "category-register";
		}else {
		categoryService.save(category);
		return "redirect:/category/";
		}
	}
	
	@RequestMapping("/edit/{id}")
	//@PreAuthorize("hasPermission('', 'editcategory')")
	public ModelAndView showEditCategory(@PathVariable(name = "id")Long id) {
		
		ModelAndView mav = new ModelAndView("category-edit");
		Category category  = categoryService.findById(id);
		mav.addObject("category", category);	
		return mav;
		
	}
	@RequestMapping("/delete/{id}")
//	@PreAuthorize("hasPermission('', 'deletecategory')")
	public String deleteCategory(@PathVariable(name = "id")Long id) {
		categoryService.deleteById(id);
		return "redirect:/category/";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Category> page = categoryService.findPaginated(pageNo, pageSize);
		List<Category> listCategory = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("sortField", sortField);
//		model.addAttribute("sortDir", sortDir);
//		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//		List<Category> listCategory = categoryService.findAll();
		model.addAttribute("listCategory", listCategory);
		return "category-view";
	}
	

}
