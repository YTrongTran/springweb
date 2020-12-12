package com.learncode.Controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.Service.ChucNangService;
import com.learncode.Service.NguoidungService;
import com.learncode.entity.Chucnang;
import com.learncode.entity.Nhomnguoidung;
import com.learncode.entity.Vaitro;

@Controller
@RequestMapping("/chucnang")
public class ChucNangController {

	@Autowired
	ChucNangService chucNangService;

	@Autowired
	NguoidungService nguoiDungService;

	@RequestMapping(value = "/")
	//@PreAuthorize("hasPermission('', 'themcn')")
	public String addOrEdit(ModelMap model) {
		Chucnang cn = new Chucnang();
		model.addAttribute("CHUCNANG", cn);
		return "chucnang-register";
	}

	@RequestMapping(value = "/saveChucNang",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	//@PreAuthorize("hasPermission('', 'themcn')")
	public String doSave(@Valid @ModelAttribute("CHUCNANG") Chucnang cn, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "chucnang-register";
		} else {
			
			cn.setCreateday(new Timestamp(new Date().getTime()));
			cn.setUpdateday(new Timestamp(new Date().getTime()));
			cn.setNguoitao(principal.getName());
			cn.setNguoiupdate(principal.getName());
			cn.setIsdelete((Integer) 0);
			this.chucNangService.insertChucNang(cn);
			return "redirect:/chucnang/list";
		}
	}

	@GetMapping("/chucnang-update")
	@ResponseBody
	//@PreAuthorize("hasPermission('', 'suacn')")
	public Optional<Chucnang> findByChucNangEditId(ModelMap model, Long id) {
		return this.chucNangService.findByChucNangEditId(id);
	}

	@GetMapping("/chucnang-chitiet")
	@ResponseBody
	//@PreAuthorize("hasPermission('', 'xctcn')")
	public Optional<Chucnang> findByChitietChucnang(ModelMap model, Long id) {
		return this.chucNangService.findByChucNangEditId(id);
	}

	@RequestMapping(value="/updateChucNang", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(Chucnang cn, Principal principal) {
		cn.setUpdateday(new Timestamp(new Date().getTime()));
		cn.setNguoiupdate(principal.getName());
		this.chucNangService.updateChucNang(cn);
		return "redirect:/chucnang/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("chucnanglist", null);
		return "redirect:/chucnang/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}")
	//@PreAuthorize("hasPermission('', 'danhsachcn')")
	public String showChucNangsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("chucnanglist");
		int pagesize = 5;
		List<Chucnang> list = (List<Chucnang>) this.chucNangService.getAllChucNang();
		int sum = list.size();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("chucnanglist", pages);

		int current = pages.getPage() + 1;
		System.out.println(current);
		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/list/page/";

		model.addAttribute("sum", sum);
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("CHUCNANGS", pages);

		return "chucnang-view";
	}

	@RequestMapping("/key")
	public List<String> key(ModelMap model) {
		return this.chucNangService.maapi();
	}

	

	@RequestMapping("/list/search/{pageNumber}")
	//@PreAuthorize("hasPermission('', 'danhsachcn')")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String tenchucnang = (String) session.getAttribute("KEYWORK");
		List<Chucnang> list = this.chucNangService.findByTenchucnang(tenchucnang);
		if (list == null) {
			return "redirect:/chucnang/list/";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("chucnanglist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("chucnanglist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/list/page/";

		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("CHUCNANGS", pages);

		return "chucnang-view";
	}

	@RequestMapping("/del")
	//@PreAuthorize("hasPermission('', 'xoacn')")
	public String delete(ModelMap model, @RequestParam("lcn") List<Long> id, Principal principal) {
		for (Long lg : id) {
			Chucnang chucNang = this.chucNangService.findById(lg).get();
			if (chucNang.getParentid() < 0) {
				chucNang.setIsdelete((Integer) 1);
				chucNang.setNguoiupdate(principal.getName());
				this.chucNangService.updateChucNang(chucNang);
			}
		}

		for (Long long1 : id) {
			Chucnang chucNang = this.chucNangService.findById(long1).get();
			if (chucNang.getParentid() > 0 || 0 == this.chucNangService.count(chucNang.getId())) {
				chucNang.setIsdelete((Integer) 1);
				chucNang.setNguoiupdate(principal.getName());
				this.chucNangService.updateChucNang(chucNang);
			}
		}
		return "redirect:/chucnang/list/";
	}

	@ModelAttribute("PARENTID")
	public List<Chucnang> getParent() {
		return this.chucNangService.getAllChucNangParent();
	}

	@ModelAttribute("NHOMS")
	public List<Nhomnguoidung> getAllNhom() {
		return this.nguoiDungService.findAllNhom();
	}

	@ModelAttribute("CHUCNANGS")
	public List<Chucnang> getAllChucNang() {
		return this.nguoiDungService.finAllChucNang();
	}

	@ModelAttribute("VAITROS")
	public List<Vaitro> getAllVaiTro() {
		return this.nguoiDungService.finAllVaiTro();
	}

}
