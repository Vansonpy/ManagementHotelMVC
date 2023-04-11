package com.example.frontend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.front.entites.ConvertListService;
import com.example.front.entites.Rooms;
import com.example.front.entites.Sales;
import com.example.front.entites.Users;

@Controller
public class SalesController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConvertListService convertListService;
	@Value("${backend.api.url}")
	private String apiUrl;

	@SuppressWarnings("unchecked")
	@GetMapping("/viewAllSales")
	public String getListPages(Model model) {
		try {
			String url = apiUrl + "/sales/viewAllSale";
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales==null) {
				Sales saleNew = new Sales();
				model.addAttribute("sale", saleNew);
				model.addAttribute("sales", listSales);
				model.addAttribute("msg", "Delete sale success");
				return "listSalesPages";
			}
			List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
			List<Integer> listButton = convertListService.listButton(listSales, 0);
			Sales sale = new Sales();
			model.addAttribute("countFields", listButton);
			model.addAttribute("sale", sale);
			model.addAttribute("sales", listsSalesShow);
			return "listSalesPages";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Sales> listSales = null;
				Sales sale = new Sales();
				model.addAttribute("sale", sale);
				model.addAttribute("sales", listSales);
				return "listSalesPages";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/viewAllSalesByButton")
	public String viewAllSalesByButton(Model model, @RequestParam Integer index) {
		try {
			String url = apiUrl + "/sales/viewAllSale";
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales==null) {
				Sales saleNew = new Sales();
				model.addAttribute("sale", saleNew);
				model.addAttribute("sales", listSales);
				model.addAttribute("msg", "Delete sale success");
				return "listSalesPages";
			}
			List<Sales> listsSalesShow = convertListService.listShowSales(listSales, index);
			List<Integer> listButton = convertListService.listButton(listSales, index);
			Sales sale = new Sales();
			model.addAttribute("countFields", listButton);
			model.addAttribute("sale", sale);
			model.addAttribute("sales", listsSalesShow);
			return "listSalesPages";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Sales> listSales = null;
				Sales sale = new Sales();
				model.addAttribute("sale", sale);
				model.addAttribute("sales", listSales);
				return "listSalesPages";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/viewAllSales/status")
	public String viewAllSaleByStatus(Model model, @ModelAttribute("sales") Sales sale,RedirectAttributes redirectAttributes) {
		try {
			redirectAttributes.addFlashAttribute("sales",sale);
			if (sale.getStatus() != 0 && sale.getStatus() != 1 && sale.getStatus() != 2) {
				String url = apiUrl + "/sales/viewAllSale";
				List<Sales> listSales = restTemplate.getForObject(url, List.class);
				if(listSales==null) {
					Sales saleNew = new Sales();
					model.addAttribute("sale", saleNew);
					model.addAttribute("sales", listSales);
					model.addAttribute("msg", "Delete sale success");
					return "listSalesPages";
				}
				List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
				List<Integer> listButton = convertListService.listButton(listSales, 0);
				sale.setStatus(null);
				model.addAttribute("countFields", listButton);
				model.addAttribute("sale", sale);
				model.addAttribute("sales", listsSalesShow);
				return "redirect:listSalesPages";
			}
			String url = apiUrl + "/sales/viewAllSaleByStatus" + "?status=" + sale.getStatus();
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales==null) {
				Sales saleNew = new Sales();
				model.addAttribute("sale", saleNew);
				model.addAttribute("sales", listSales);
				model.addAttribute("msg", "Delete sale success");
				return "listSalesPages";
			}
			List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
			List<Integer> listButton = convertListService.listButton(listSales, 0);
			model.addAttribute("countFields", listButton);
			model.addAttribute("sale", sale);
			model.addAttribute("statusCheck", sale.getStatus());
			model.addAttribute("sales", listsSalesShow);
			return "listSalesPages";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Sales> listSales = null;
				Sales sales = new Sales();
				model.addAttribute("sale", sales);
				model.addAttribute("sales", listSales);
				return "listSalesPages";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/viewAllSalesByButtonAndStatus")
	public String viewAllSalesByButton(Model model, @RequestParam Integer index,@ModelAttribute("sales") Sales sale) {
		try {
			if (sale.getStatus() != 0 && sale.getStatus() != 1 && sale.getStatus() != 2) {
				String url = apiUrl + "/sales/viewAllSale";
				List<Sales> listSales = restTemplate.getForObject(url, List.class);
				if(listSales==null) {
					Sales saleNew = new Sales();
					model.addAttribute("sale", saleNew);
					model.addAttribute("sales", listSales);
					model.addAttribute("msg", "Delete sale success");
					return "listSalesPages";
				}
				List<Sales> listsSalesShow = convertListService.listShowSales(listSales,index);
				List<Integer> listButton = convertListService.listButton(listSales, index);
				sale.setStatus(null);
				model.addAttribute("countFields", listButton);
				model.addAttribute("sale", sale);
				model.addAttribute("sales", listsSalesShow);
				return "listSalesPages";
			}
			String url = apiUrl + "/sales/viewAllSaleByStatus" + "/" + sale.getStatus();
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales==null) {
				Sales saleNew = new Sales();
				model.addAttribute("sale", saleNew);
				model.addAttribute("sales", listSales);
				model.addAttribute("msg", "Delete sale success");
				return "listSalesPages";
			}
			List<Sales> listsSalesShow = convertListService.listShowSales(listSales, index);
			List<Integer> listButton = convertListService.listButton(listSales, index);
			model.addAttribute("countFields", listButton);
			model.addAttribute("sale", sale);
			model.addAttribute("sales", listsSalesShow);
			return "listSalesPages";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Sales> listSales = null;
				Sales sales = new Sales();
				model.addAttribute("sale", sales);
				model.addAttribute("sales", listSales);
				return "listSalesPages";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/cancelSales")
	public String cancelSale(@RequestParam Optional<Integer> id, Model model) {
		try {
			Integer idInput = id.get();
			Sales sale = new Sales();
			sale.setId(idInput);
			sale.setStatus(2);
			String urlUpdate = apiUrl + "/sales/updateSales" + "?id=" + idInput;
			restTemplate.put(urlUpdate, sale, Sales.class);
			String url = apiUrl + "/sales/viewAllSale";
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales==null) {
				Sales saleNew = new Sales();
				model.addAttribute("sale", saleNew);
				model.addAttribute("sales", listSales);
				model.addAttribute("msg", "Delete sale success");
				return "listSalesPages";
			}
			List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
			List<Integer> listButton = convertListService.listButton(listSales, 0);
			model.addAttribute("countFields", listButton);
			Sales saleNew = new Sales();
			model.addAttribute("sale", saleNew);
			model.addAttribute("sales", listsSalesShow);
			model.addAttribute("msg", "Delete sale success");
			return "listSalesPages";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				String url = apiUrl + "/sales/viewAllSale";
				List<Sales> listSales = restTemplate.getForObject(url, List.class);
				List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
				List<Integer> listButton = convertListService.listButton(listSales, 0);
				model.addAttribute("countFields", listButton);
				Sales sale = new Sales();
				model.addAttribute("sale", sale);
				model.addAttribute("sales", listsSalesShow);
				model.addAttribute("msg", "Delete sale faile");
				return "listSalesPages";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}
	@SuppressWarnings("unchecked")
	@GetMapping("/paySale")
	public String paySales(Model model, @RequestParam Optional<Integer> id) {
		try {
			Integer idInput = id.get();
			String url = apiUrl + "/sales/viewById" + "?id=" + idInput;
			Sales sales = restTemplate.getForObject(url, Sales.class);
			model.addAttribute("sale", sales);
			return "payPage";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				String url = apiUrl + "/sales/viewAllSale";
				List<Sales> listSales = restTemplate.getForObject(url, List.class);
				List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
				List<Integer> listButton = convertListService.listButton(listSales, 0);
				model.addAttribute("countFields", listButton);
				Sales sale = new Sales();
				model.addAttribute("sale", sale);
				model.addAttribute("sales", listsSalesShow);
				model.addAttribute("sales", listSales);
				return "listSalesPages";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}

	@PostMapping("/paySales")
	public String paySale(@RequestParam Optional<Integer> id, Model model) {
		try {
			Integer idInput = id.get();
			Sales sale = new Sales();
			sale.setId(idInput);
			sale.setStatus(1);
			String urlUpdate = apiUrl + "/sales/updateSales" + "?id=" + idInput;
			restTemplate.put(urlUpdate, sale, Sales.class);
			String url = apiUrl + "/sales/viewById" + "?id=" + idInput;
			Sales sales = restTemplate.getForObject(url, Sales.class);
			model.addAttribute("sale", sales);
			model.addAttribute("msg", "Pay sale success");
			return "payPage";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				Integer idInput = id.get();
				String url = apiUrl + "/sales/viewById" + "?id=" + idInput;
				Sales sales = restTemplate.getForObject(url, Sales.class);
				model.addAttribute("sale", sales);
				model.addAttribute("msg", "Pay sale faile");
				return "payPage";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/viewSalesUser")
	public String viewSalesUser(Model model, HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute("userSession");
			String userName = user.getUserName();
			String url = apiUrl + "/sales/viewSalesUserAndStatus" + "?userName=" + userName +"&status="+0;
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales!=null) {
				List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
				List<Integer> listButton = convertListService.listButton(listSales, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listSales", listsSalesShow);
			}			
			return "roomUser";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Sales> listSales = null;
				model.addAttribute("listSales", listSales);
				return "roomUser";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/viewAllSalesRoomUser")
	public String viewAllSalesRoomUser(Model model, HttpServletRequest request,@RequestParam Integer index) {
		try {
			Users user = (Users) request.getSession().getAttribute("userSession");
			String userName = user.getUserName();
			String url = apiUrl + "/sales/viewSalesUser" + "?userName=" + userName;
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales!=null) {
				List<Sales> listsSalesShow = convertListService.listShowSales(listSales, index);
				List<Integer> listButton = convertListService.listButton(listSales, index);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listSales", listsSalesShow);
			}			
			return "roomUser";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Sales> listSales = null;
				model.addAttribute("listSales", listSales);
				return "roomUser";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/userCancelSales")
	public String userCancelSale(@RequestParam Optional<Integer> id, Model model, HttpServletRequest request) {
		try {
			Integer idInput = id.get();
			Sales sale = new Sales();
			sale.setId(idInput);
			sale.setStatus(2);
			String urlUpdate = apiUrl + "/sales/updateSales" + "?id=" + idInput;
			restTemplate.put(urlUpdate, sale, Sales.class);
			Users user = (Users) request.getSession().getAttribute("userSession");
			String userName = user.getUserName();
			String url = apiUrl + "/sales/viewSalesUserAndStatus" + "?userName=" + userName+"&status="+0;
			List<Sales> listSales = restTemplate.getForObject(url, List.class);
			if(listSales.isEmpty()==true) {
				Sales saleNew = new Sales();
				model.addAttribute("sale", saleNew);
				model.addAttribute("sales", listSales);
				model.addAttribute("msg", "Delete sale success");
				return "listSalesPages";
			}
			List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
			List<Integer> listButton = convertListService.listButton(listSales, 0);
			model.addAttribute("countFields", listButton);
			model.addAttribute("listSales", listsSalesShow);
			model.addAttribute("msg", "Delete sale success");
			return "roomUser";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				Users user = (Users) request.getSession().getAttribute("userSession");
				String userName = user.getUserName();
				String url = apiUrl + "/sales/viewSalesUser" + "?userName=" + userName+"&status="+0;
				List<Sales> listSales = restTemplate.getForObject(url, List.class);
				if(listSales.isEmpty()==true) {
					Sales saleNew = new Sales();
					model.addAttribute("sale", saleNew);
					model.addAttribute("sales", listSales);
					model.addAttribute("msg", "Delete sale success");
					return "listSalesPages";
				}
				List<Sales> listsSalesShow = convertListService.listShowSales(listSales, 0);
				List<Integer> listButton = convertListService.listButton(listSales, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listSales", listsSalesShow);
				model.addAttribute("msg", "Delete sale faile");
				return "roomUser";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}
}
