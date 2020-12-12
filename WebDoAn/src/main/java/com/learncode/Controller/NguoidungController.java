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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learncode.Mahoa.Mahoa;
import com.learncode.Service.NguoidungService;
import com.learncode.entity.Chucnang;
import com.learncode.entity.Nguoidung;
import com.learncode.entity.Vaitro;

@Controller
@RequestMapping("/nguoidung")
public class NguoidungController {

	@Autowired
	NguoidungService nguoiDungService;

	@ModelAttribute("VAITROS")
	public List<Vaitro> getAllVaiTro() {
		return this.nguoiDungService.finAllVaiTro();
	}

	@ModelAttribute("CHUCNANGS")
	public List<Chucnang> getAllChucNang() {
		return this.nguoiDungService.finAllChucNang();
	}
	
	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		Nguoidung nd = new Nguoidung();
		model.addAttribute("NGUOIDUNG", nd);
		return "nguoidung-register";
	}

	@RequestMapping(value = "/saveNguoiDung")
	public String saveNguoiDung(@ModelAttribute("NGUOIDUNG") Nguoidung nd, Principal principal) {
	//	Mahoa.giaiMd5(nd.getPassword());
		nd.setCreateday(new Timestamp(new Date().getTime()));
		nd.setNguoitao(principal.getName());
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setNguoiupdate(principal.getName());
		this.nguoiDungService.insertNguoidung(nd);
		return "redirect:/nguoidung/list";
	}

	@GetMapping(value = "/nguoidung-update")
	@ResponseBody
	public Map<String, String> update(Long id, ModelMap model) {
		Nguoidung nd = this.nguoiDungService.findById1(id);
		List<Long> lsn = this.nguoiDungService.findByIdnhom(id);
		List<Long> lsvt = this.nguoiDungService.findByIdvaitro(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(nd.getId()));
		map.put("manguoidung", nd.getManguoidung());
		map.put("tennguoidung", nd.getTennguoidung());
		map.put("password", nd.getPassword());
		map.put("email", nd.getEmail());
		map.put("gender", String.valueOf(nd.getGender()));
		map.put("phone", nd.getPhone());
		map.put("nhoms", String.valueOf(lsn));
		map.put("vaitros", String.valueOf(lsvt));
		return map;
	}

	@GetMapping("/nguoidung-chitiet")
	public Optional<Nguoidung> ChiTiet(Long id) {
		return this.nguoiDungService.findNguoidungById(id);
	}

	@RequestMapping(value = "/doUpdate")
	public String doUpdate(Nguoidung nd, Principal principal) {
		nd.setIsdelete((Integer) 0);
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setNguoiupdate(principal.getName());
		this.nguoiDungService.updateNguoidung(nd);
		return "redirect:/nguoidung/list";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpSession session, HttpServletRequest request) {
		request.getSession().setAttribute("nguoidunglist", null);
		return "redirect:/nguoidung/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}")
	public String showNguoidungPages(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoidunglist");
		int pageSize = 5;
		List<Nguoidung> list = (List<Nguoidung>) this.nguoiDungService.getAllNguoiDung();
		int sum = list.size();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pageSize);
		} else {
			final int gotoPage = pageNumber - 1;
			if (gotoPage <= pages.getPageCount() && gotoPage >= 0) {
				pages.setPage(gotoPage);
			}
		}

		request.getSession().setAttribute("nguoidunglist", pages);

		int current = pages.getPage() + 1;

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

		model.addAttribute("NGUOIDUNGS", pages);

		return "nguoidung-view";
	}

	@RequestMapping("/list/search/{pageNumber}")
	public String search(ModelMap model, HttpServletRequest request, @RequestParam("keyword") String tennguoidung,
			@PathVariable int pageNumber) {
		if (tennguoidung.equals("") || tennguoidung == null) {
			return "redirect:/nguoidung/list";
		}
		List<Nguoidung> list = this.nguoiDungService.findByTennguoidung(tennguoidung);
		if (list == null) {
			return "redirect:/nguoidung/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoidunglist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("nguoidunglist", pages);

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

		model.addAttribute("NGUOIDUNGS", pages);
		return "nguoidung-view";
	}

	@RequestMapping("/delete")
	public String delete(ModelMap model, @RequestParam("id[]") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			Nguoidung nd = this.nguoiDungService.findNguoidungById(long1).get();
			nd.setUpdateday(new Timestamp(new Date().getTime()));
			nd.setNguoiupdate(principal.getName());
			nd.setIsdelete((Integer) 1);
			this.nguoiDungService.deleteNguoidung(nd);
		}
		return "redirect:/nguoidung/list/";
	}
}
