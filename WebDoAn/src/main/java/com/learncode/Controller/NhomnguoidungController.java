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

import com.learncode.Service.NhomnguoidungService;
import com.learncode.entity.Chucnang;
import com.learncode.entity.Nhomnguoidung;

@Controller
@RequestMapping("/nhom")
public class NhomnguoidungController {
	@Autowired
	NhomnguoidungService nhomNguoiDungService;

	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		Nhomnguoidung nnd = new Nhomnguoidung();
		model.addAttribute("NHOM", nnd);
		return "NhomNguoiDung-register";
	}

	@RequestMapping(value = "/saveNhomNguoiDung")
	//@PreAuthorize("hasPermission('', 'tmn')")
	public String doSave(@Valid @ModelAttribute("NHOM") Nhomnguoidung ndd, BindingResult bindingResult,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			return "NhomNguoiDung-register";
		} else {
//		ndd.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000")));
			ndd.setCreateday(new Timestamp(new Date().getTime()));
			ndd.setUpdateday(new Timestamp(new Date().getTime()));
			ndd.setNguoitao(principal.getName());
			ndd.setNguoiupdate(principal.getName());
			this.nhomNguoiDungService.insertNhomNguoiDung(ndd);
			return "redirect:/nhom/list";
		}
	}

	@GetMapping(value = "/nhom-update", produces = "application/json")
	@ResponseBody
//	@PreAuthorize("hasPermission('', 'cnn')")
	public Map<String, String> update(Long id) {
		Nhomnguoidung nnd = this.nhomNguoiDungService.findByLongId(id).get();
		List<Long> ncn = this.nhomNguoiDungService.findChucnangNhom(nnd.getId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(nnd.getId()));
		map.put("manhom", nnd.getManhom());
		map.put("tennhom", nnd.getTennhom());
		map.put("chucnangs", String.valueOf(ncn));
		return map;
	}

	@ResponseBody
	@GetMapping(value = "/nhom-chitiet", produces = "application/json; charset=UTF-8")
	//@PreAuthorize("hasPermission('', 'xctn')")
	public Optional<Nhomnguoidung> chiTiet(Long id) {
		return nhomNguoiDungService.findByLongId(id);

	}

	@RequestMapping(value = "/doUpdate", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String doUpdate(ModelMap model, Nhomnguoidung nnd, Principal principal) {
		nnd.setNguoiupdate(principal.getName());
		nnd.setUpdateday(new Timestamp(new Date().getTime()));
		this.nhomNguoiDungService.updateNhomNguoiDung(nnd);
		model.addAttribute("NHOMS", this.nhomNguoiDungService.findAllNhomNguoiDung());
		return "redirect:/nhom/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("nhomlist", null);
		return "redirect:/nhom/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
//	@PreAuthorize("hasPermission('', 'xdsn')")
	public String showNhomsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nhomlist");
		int pagesize = 5;
		List<Nhomnguoidung> list = (List<Nhomnguoidung>) this.nhomNguoiDungService.findAllNhomNguoiDung();
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
		request.getSession().setAttribute("nhomlist", pages);

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

		model.addAttribute("NHOMS", pages);

		return "NhomNguoiDung-view";
	}



	@RequestMapping("/list/search/{pageNumber}")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String tennhom = (String) session.getAttribute("NAMN");
		List<Nhomnguoidung> list = this.nhomNguoiDungService.findByTennhom(tennhom);
		if (list == null) {
			return "redirect:/nhom/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nhomlist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("nhomlist", pages);

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

		model.addAttribute("NHOMS", pages);

		return "NhomNguoiDung-view";
	}

	@ModelAttribute(name = "CHUCNANGS")
	public List<Chucnang> getAllChucNang() {
		return nhomNguoiDungService.findAllChucNang();
	}

	@RequestMapping("/delete")
	//@PreAuthorize("hasPermission('', 'xn')")
	public String delete(@RequestParam("ndd") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			Nhomnguoidung nhomNguoiDung = this.nhomNguoiDungService.findByLongId(long1).get();
			nhomNguoiDung.setIsdelete((Integer) 1);
			nhomNguoiDung.setNguoiupdate(principal.getName());
			this.nhomNguoiDungService.deleteNhomNguoiDung(nhomNguoiDung);
		}
		return "redirect:/nhom/list/";
	}
}
