package com.example.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.front.entites.ConvertListService;
import com.example.front.entites.Pages;
import com.example.front.entites.Rooms;
import com.example.front.entites.Sales;

@Controller
public class PagesController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConvertListService convertListService;
	@Value("${backend.api.url}")
	private String apiUrl;

	/*
	 * @PostMapping("/editPages") public String
	 * savePagesEdit(@ModelAttribute("page") Pages page, Model model) { try { String
	 * url = apiUrl + "/pages/updatePage" + "/" + page.getId();
	 * restTemplate.put(url, page); model.addAttribute("page", page);
	 * model.addAttribute("msgSuccess", "Success"); return "pagesEdit"; } catch
	 * (Exception e) { e.printStackTrace(); return "notExitUrl"; } }
	 */

	@PostMapping("/addPages")
	public String savePages(@ModelAttribute("page") Pages page, Model model) {
		try {
			System.out.println(page.getPageName());
			String url = apiUrl + "/pages/addPage";
			Pages pageAdd = restTemplate.postForObject(url, page, Pages.class);
			if (pageAdd == null) {
				model.addAttribute("page", page);
				model.addAttribute("msgFaile", "Add page faile");
				return "pagesEdit";
			} else {
				model.addAttribute("page", pageAdd);
				model.addAttribute("msgSuccess", "Success");
				return "pagesEdit";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "notExitUrl";
		}
	}

	// -------------------------------------------------------------------------------------
	// --------------------------------Customer---------------------------------------
	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public String homeIndex(Model model) {
		String urlHeader = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Header";
		List<Pages> listContentHeader = restTemplate.getForObject(urlHeader, List.class);

		String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Border";
		List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

		Sales sale = new Sales();
		model.addAttribute("listBorder", listContentBorder);
		model.addAttribute("listHeader", listContentHeader);
		model.addAttribute("sales", sale);
		return "home";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/about")
	public String aboutIndex(Model model) {
		String urlHeader = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=About" + "&location=Header";
		List<Pages> listContentHeader = restTemplate.getForObject(urlHeader, List.class);

		String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=About" + "&location=Border";
		List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

		model.addAttribute("listBorder", listContentBorder);
		model.addAttribute("listHeader", listContentHeader);
		return "about";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/blog")
	public String blogIndex(Model model) {
		String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Blog" + "&location=Border";
		List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

		model.addAttribute("listBorder", listContentBorder);
		return "blog";
	}

	// ---------------------------Admin---------------------
	@SuppressWarnings("unchecked")
	@GetMapping("/homeAdmin")
	public String homeaboutAdmin(Model model) {
		String urlHeader = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Header";
		List<Pages> listContentHeader = restTemplate.getForObject(urlHeader, List.class);

		String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Border";
		List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

		Sales sale = new Sales();
		model.addAttribute("listBorder", listContentBorder);
		model.addAttribute("listHeader", listContentHeader);
		model.addAttribute("sales", sale);
		return "adminHomePage";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/aboutAdmin")
	public String aboutAdmin(Model model) {
		String urlHeader = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=About" + "&location=Header";
		List<Pages> listContentHeader = restTemplate.getForObject(urlHeader, List.class);

		String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=About" + "&location=Border";
		List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

		model.addAttribute("listBorder", listContentBorder);
		model.addAttribute("listHeader", listContentHeader);
		return "adminAboutPage";
	}

	@PostMapping(value = "/updatePage", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String updateAbout(@ModelAttribute("page") Pages page, Model model) {
		try {
			String url = apiUrl + "/pages/updatePage" + "/" + page.getId();
			restTemplate.put(url, page);
			model.addAttribute("page", page);
			model.addAttribute("msgSuccess", "Success");
			return "pagesEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "notExitUrl";
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/blogAdmin")
	public String blogAdmin(Model model) {
		String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Blog" + "&location=Border";
		List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

		model.addAttribute("listBorder", listContentBorder);
		return "adminBlogPage";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/managementbooking")
	public String managementbooking(Model model) {
		try {
			String url = apiUrl + "/rooms/viewAllRoom";
			List<Rooms> listRooms = restTemplate.getForObject(url, List.class);
			if (listRooms != null) {
				List<Rooms> listsSalesShow = convertListService.listShowRooms(listRooms, 0);
				List<Integer> listButton = convertListService.listButton(listRooms, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listRooms", listsSalesShow);
			}
			return "managementBooking";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				model.addAttribute("listRooms", roomsArray);
				return "managementBooking";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}
}
