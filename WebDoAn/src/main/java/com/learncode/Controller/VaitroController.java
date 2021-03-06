package com.learncode.Controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.learncode.Service.VaitroService;
import com.learncode.entity.Chucnang;
import com.learncode.entity.Vaitro;

@Controller
@RequestMapping("/vaitro")
public class VaitroController {

	@Autowired
	VaitroService vaiTroService;

	@ModelAttribute("CHUCNANGS")
	public List<Chucnang> getAllChucNang() {
		return this.vaiTroService.finAllChucNang();
	}

	@RequestMapping("/")
	@PreAuthorize("hasPermission('', 'themvt')")
	public String addOrEdit(ModelMap model) {
		Vaitro vt = new Vaitro();
		model.addAttribute("VAITRO", vt);
		return "Vaitro-register";
	}

	@RequestMapping(value = "/saveVaitro", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize("hasPermission('', 'tmvt')")
	public String saveVaiTro(ModelMap model, @Valid @ModelAttribute("VAITRO") Vaitro vt, BindingResult bindingResult,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			return "Vaitro-register";
		} else {

			vt.setCreateday(new Timestamp(new Date().getTime()));
			vt.setUpdateday(new Timestamp(new Date().getTime()));
			vt.setNguoitao(principal.getName());
			vt.setNguoiupdate(principal.getName());
			this.vaiTroService.insertVaitro(vt);
			return "redirect:/vaitro/list";
		}
	}

	@GetMapping(value = "/update", produces = "application/json")
	@ResponseBody
	@PreAuthorize("hasPermission('', 'cnvt')")
	public Map<String, String> update(Long id) {
		Vaitro vt = this.vaiTroService.findByVaitroId(id).get();
		List<Long> lscn = this.vaiTroService.findChucnangVaitro(vt.getId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(vt.getId()));
		map.put("mavaitro", vt.getMavaitro());
		map.put("tenvaitro", vt.getTenvaitro());
		map.put("chucnangs", String.valueOf(lscn));
		return map;
	}

	@GetMapping("/vaitro-chitiet")
	@PreAuthorize("hasPermission('', 'xctvt')")
	public Optional<Vaitro> chitiet(Long id) {
		return this.vaiTroService.findByVaitroId(id);
	}

	@RequestMapping(value = "/updateVaiTro")
	public String doUpdate(ModelMap model, Vaitro vt, Principal principal) {
		vt.setIsdelete((Integer) 0);
		vt.setUpdateday(new Timestamp(new Date().getTime()));
		vt.setNguoiupdate(principal.getName());
		this.vaiTroService.updateVaitro(vt);
		return "redirect:/vaitro/list";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("vaitrolist", null);
		return "redirect:/vaitro/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('', 'xdsvt')")
	public String showVaiTrosPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages1 = (PagedListHolder<?>) request.getSession().getAttribute("vaitrolist");
		int pagesize = 5;
		List<Vaitro> list = (List<Vaitro>) this.vaiTroService.listVaiTro();
		System.out.println("vaitro11111" + list);
		int sum = list.size();
		if (pages1 == null) {
			pages1 = new PagedListHolder<>(list);
			pages1.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages1.getPageCount() && goToPage >= 0) {
				pages1.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("vaitrolist", pages1);
		int current = pages1.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages1.getPageCount());
		int totalPageCount = pages1.getPageCount();
		String baseUrl = "/list/page/";
		model.addAttribute("sum", sum);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("VAITROS", pages1);
		return "Vaitro-view";
	}

	

	@RequestMapping("/list/search/{numberPage}")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int numberPage,
			HttpSession session) {
		String tenvaitro = (String) session.getAttribute("NAMEVT");
		
		List<Vaitro> list = this.vaiTroService.findByTenvaitro(tenvaitro);
		
		if (list == null) {
			return "redirect:/vaitro/list";
		}

		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("vaitrolist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPage(pagesize);
		final int goToPage = numberPage - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("vaitrolist", pages);
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
		model.addAttribute("VAITROS", pages);
		return "Vaitro-view";
	}

	@RequestMapping("/del")
	@PreAuthorize("hasPermission('', 'xvt')")
	public String delete(ModelMap model, @RequestParam("lvt") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			Vaitro vaiTro = this.vaiTroService.findByVaitroId(long1).get();
			vaiTro.setUpdateday(new Timestamp(new Date().getTime()));
			vaiTro.setNguoiupdate(principal.getName());
			vaiTro.setIsdelete((Integer) 1);
			this.vaiTroService.updateVaitro(vaiTro);
		}
		return "redirect:/vaitro/list";
	}
		
}
